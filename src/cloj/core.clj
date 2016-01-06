(ns cloj.core
  (:gen-class)
  (:require [clojure.data.json :as json])
  (:require [cloj.customer :as customer]))


 (def data (json/read-json (slurp "resources/test.json")))

;(def eye-colours ["brown", "blue", "green"])
;(println "Most popular eye-colour is:" (customer/most-popular-eye-colour eye-colours data))
;(println)
;
;(println "Customer email addresses in ascending order..")
;(def email-list (customer/sorted-email-list data))
;(apply print (for [email email-list] (str email "\n")))
;(println)


;(def barking "51.536563,0.075766")
;(println (customer/lookup-address barking))

;(println ((nth data 0) :latitude))
;
(doseq [customer data]
  (let [lat (customer :latitude) lng (customer :longitude)]
    (let [address (customer/lookup-address (str lat "," lng))]
      (assoc customer :address address))))

;(println
;  (for [customer (sort-by :email data)
;    :let [lat (customer :latitude)
;          lng (customer :longitude)]] {:lat lat :lng lng}))





