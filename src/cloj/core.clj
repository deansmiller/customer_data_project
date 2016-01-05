(ns cloj.core
  (:gen-class)
  (:require [clojure.data.json :as json])
  (:require [cloj.customer :as customer]))


(def data (json/read-json (slurp "resources/data.json")))

;(def eye-colours ["brown", "blue", "green"])
;(println "Most popular eye-colour is:" (customer/most-popular-eye-colour eye-colours data))
;(println)
;
;(println "Customer email addresses in ascending order..")
;(def email-list (customer/sorted-email-list data))
;(apply print (for [email email-list] (str email "\n")))
;(println)

;(customer/lookup-address "40.714224,-73.961452")
;(println (customer/lookup-address "40.714224,-73.961452"))




