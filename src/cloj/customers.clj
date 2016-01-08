(ns cloj.customers
  (:require [clojure.data.json :as json]
            [org.httpkit.client :as http]))

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


; TODO error handling?!
(defn- lookup-address [lat-long]
  (let [{:keys [body error]} @(http/get geo-api-url {:query-params {:latlng lat-long :api-key api-key}})]
    (if error
      (println "fuck up")
      (let [result ((json/read-json body) :results)]
          ((first result) :formatted_address)
        ))))

(defn- get-address [customer]
  (if customer
    (lookup-address (str (customer :latitude) "," (customer :longitude)))
    (println "fuck upx"))
  )



(defn customers-address-list [customers]
  (json/write-str
    (for [customer customers
          :let [address (get-address customer)]]
      (assoc customer :address address))))





