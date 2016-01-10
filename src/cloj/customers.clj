(ns cloj.customers
  (:require [clojure.data.json :as json]
            [clj-http.client :as http]))

(def api-key "AIzaSyCIYs-qaMVQSmRU5qt9Nc_7vhSj8zWnmro")
(def geo-api-url "https://maps.googleapis.com/maps/api/geocode/json")

(defn- get-by-eye-colour
  "Return customers with a specified eye-colour"
  [colour data]
  (filter #(= (:eyeColor %) colour) data))

(defn- eye-colour-map
  "Determine which eye-colour (brown, blue or green) is the most popular"
  [colours data]
  (into {}
    (for [colour colours
          :let [colour-map {(keyword colour) (count(get-by-eye-colour colour data))}]]
      colour-map)))


(defn most-popular-eye-colour
  [colours data]
  (name (key (apply max-key val (eye-colour-map colours data)))))


(defn sorted-email-list
  [data]
  (into []
    (for [customer (sort-by :email data)
          :let [email-list (customer :email)]] email-list)))


(defn- lookup-address [lat-long]
    (def response (future (http/get geo-api-url {:query-params {:latlng lat-long :api-key api-key}})))
    (let [{:keys [body error]} @response]
        (println body)))



(defn- get-address [customer]
    (lookup-address (str (customer :latitude) "," (customer :longitude))))



(defn customers-address-list [customers]
    (for [customer customers
          :let [address (get-address customer)]]
      (println address)))
