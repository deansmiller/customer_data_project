(ns cloj.core
  (:gen-class :main true)
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
     (seq (customers/sorted-email-list customers-list)))
  (doseq [email email-list] (println email))
  (println)

  (println "Reverse Geocoded addresses (entire output stored in resources/output.json)")
  (println "-------------------------------------------------------------------------")
  (def customers-with-addresses
     (seq (doall (customers/customers-address-list customers-list))))
  ; save customers-with-addresses data
  (spit "resources/output.json"
     (json/write-str customers-with-addresses))

  ; print addresses
  (doseq [customer customers-with-addresses] (println (customer :address)))
  (println)

  (println "Closest customers")
  (doseq [customer customers-list]
    (println (customer :name) (customers/get-closest-customer customer customers-list)))

  ; shutdown thread pools
  (shutdown-agents))
