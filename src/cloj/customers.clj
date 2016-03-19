(ns cloj.customers
  (:require [clojure.data.json :as json]
            [clj-http.client :as http]
            [clojure.edn :as edn]
            [cloj.utils :as utils]))


(def api-config (edn/read-string (slurp "resources/google-geo-api-config.edn")))
(def api-url (api-config :api-url))
(def api-key (api-config :api-key))


(defn- lookup-address [lat-long]
  "Given latitude and longitude request location info from google api and extract address"
  ;; Because the Geocoding API limit is 10 req per second
  ;; sometimes running the app exceded that
  ;; removing this sleep and the request returns a "OVER_QUERY_LIMIT" status
  (Thread/sleep 100)
  (def response (future (http/get api-url {:query-params {:latlng lat-long :api-key api-key}})))
  (let [{:keys [body]} @response]
      (let [result (first ((json/read-json body) :results))]
          (if result
              (result :formatted_address)))))



(defn- get-address [customer]
  "Given a customer, lookup customer's address"
  (lookup-address (str (customer :latitude) "," (customer :longitude))))



(defn- get-by-eye-colour
  "Return customers with a specified eye-colour"
  [colour customers-list]
  (filter #(= (:eyeColor %) colour) customers-list))



(defn- eye-colour-map
  "Determine which eye-colour (brown, blue or green) is the most popular"
  [colours customers-list]
  (into {}
    (for [colour colours
          :let [colour-map {(keyword colour) (count(get-by-eye-colour colour customers-list))}]]
      colour-map)))


; public methods
(defn most-popular-eye-colour
  [colours customers-list]
  (name (key (apply max-key val (eye-colour-map colours customers-list)))))




(defn sorted-email-list
  [customers-list]
    (for [customer (sort-by :email customers-list)
          :let [email-list (customer :email)]] email-list))



(defn customers-address-list [customers]
  (for [customer customers
        :let [address (get-address customer)]]
    (assoc customer :address address)))



(defn- get-location
  "Given a customer, returns the customers lat and long data"
    [customer]
    {:long (customer :longitude) :lat (customer :latitude)})




(defn get-customer-distances
  "Given a customer and a list of customers. Returns a lazy sequence of distances between each customer"
    [customer customers]
    (for [target-customer customers
          :let [location (get-location target-customer)] :when (not= (target-customer :_id) (customer :_id))]
      {:name (target-customer :name) :distance (utils/haversine (get-location customer) location)}))



(defn get-closest-customer
  [customer customers]
  (first (sort-by #(% :distance) (get-customer-distances customer customers))))


