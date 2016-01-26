(ns cloj.core
  (:gen-class)
  (:require [cloj.customers :as customers]
            [clojure.data.json :as json]
            [cloj.utils :as utils]))

(def customers-list (json/read-json (slurp "resources/data.json")))


(defn -main []

   (def eye-colours ["brown", "blue", "green"])

   (println)
   (println "Most popular eye-colour is:"
       (customers/most-popular-eye-colour eye-colours customers-list))
   (println)

   (println "Customer email addresses in ascending order")
   (println "---------------------------------------------")
   (def email-list
       (customers/sorted-email-list customers-list))
   (apply print
       (for [email email-list]
           (str email "\n")))
   (println)

   (println "Reverse Geocoded addresses (entire output stored in resources/output.json")
   (println "-------------------------------------------------------------------------")
   (def customers-with-addresses
       (doall (customers/customers-address-list customers-list)))
   ; save customers-with-addresses data
   (spit "resources/output.json"
       (json/write-str customers-with-addresses))

   ; print addresses
   (doall
       (map #(println (% :address)) customers-with-addresses))



   ;shutdown thread pools
   (shutdown-agents))
