(ns cloj.core
  (:gen-class)
  (:require [cloj.customers :as customers])
  (:require [clojure.data.json :as json])
  (:require [clojure.core.async :refer [chan >! <! go <!!]]))


(def customers-list (json/read-json (slurp "resources/single.json")))

;(def eye-colours ["brown", "blue", "green"])
;(println "Most popular eye-colour is:" (customers/most-popular-eye-colour eye-colours customers-list))
;(println)
;
;(println "Customer email addresses in ascending order..")
;(def email-list (customers/sorted-email-list customers-list))
;(apply print (for [email email-list] (str email "\n")))
;(println)
;

;(spit "resources/output.json" (customers/customers-address-list customers-list))

;(prn (count customers-list))


(println (customers/customers-address-list customers-list))

