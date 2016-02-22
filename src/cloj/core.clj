(ns cloj.core
  (:gen-class)
  (:require [cloj.customers :as customers]
            [clojure.data.json :as json]
            [cloj.utils :as utils]))

(import '(java.util.concurrent Executors))

(def customers-list (json/read-json (slurp "resources/data.json")))



(defn -main []

    (def c1 (nth customers-list 0))
    (def c2 (nth customers-list 1))
    (def first-two [c1])
    
    (def lat-long (str (c1 :latitude) "," (c1 :longitude)))

    (defn task []
    	(def location (customers/address-lookup c1))
    	(println (type location))
    	(location))


 	(def pool (Executors/newFixedThreadPool 10))


 	(def ftr (.submit pool task))

 	(println "Is future done?" (.isDone ftr))
 	(def result (.get ftr))
 	(println "Is future done?" (.isDone ftr))
 	(println "result: " result)


    ; (let [{:keys [body]} (.get ftr)]
    ;     (let [result (first ((json/read-json body) :results))]
    ;         (if result
    ;             (result :formatted_address))))

	; (def f (future (task)))
	; (println)
	; (println @f)


 	(.shutdown pool)


 )

    ; (doseq [customer customers-list]
    ;     (println (customer :name) (customers/get-closest-customer customer customers-list)))



; (defn -main []

;     (def eye-colours ["brown", "blue", "green"])

;     (println)
;     (println "Most popular eye-colour is:"
;         (customers/most-popular-eye-colour eye-colours customers-list))
;     (println)

;     (println "Customer email addresses in ascending order")
;     (println "---------------------------------------------")
;     (def email-list
;         (seq (customers/sorted-email-list customers-list)))
;     (doseq [email email-list] (println email))
;     (println)

;     (println "Reverse Geocoded addresses (entire output stored in resources/output.json")
;     (println "-------------------------------------------------------------------------")
;     (def customers-with-addresses
;         (seq (doall (customers/customers-address-list customers-list))))
;     ; save customers-with-addresses data
;     (spit "resources/output.json"
;         (json/write-str customers-with-addresses))

;     ; print addresses
;     (doseq [customer customers-with-addresses] (println (customer :address)))



;     ;shutdown thread pools
;     (shutdown-agents))
