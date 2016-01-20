(ns cloj.mock)

(def addresses
  ["59 Earlsdown House, Barking, Essex, IG11 7EL"
    "149 St Marys, Barking, Essex, IG11 7TF"
    "37 Malvern Road, East Ham, E6 1LS"
    "23 Dean Street, Hackney, E9 3FD"])


(defn- lookup-address [lat-long]
    (rand-nth addresses))



(defn- get-address [customer]
  (lookup-address (str (customer :latitude) "," (customer :longitude))))



(defn customers-address-list [customers]
  (for [customer customers
        :let [address (get-address customer)]]
    (assoc customer :address address)))
