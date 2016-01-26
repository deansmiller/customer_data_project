(ns cloj.utils)

;http://rosettacode.org/wiki/Haversine_formula#Clojure
(defn haversine
  [{lon1 :longitude lat1 :latitude} {lon2 :longitude lat2 :latitude}]
  (let [R 6372.8 ; kilometers
        dlat (Math/toRadians (- lat2 lat1))
        dlon (Math/toRadians (- lon2 lon1))
        lat1 (Math/toRadians lat1)
        lat2 (Math/toRadians lat2)
        a (+ (* (Math/sin (/ dlat 2)) (Math/sin (/ dlat 2))) (* (Math/sin (/ dlon 2)) (Math/sin (/ dlon 2)) (Math/cos lat1) (Math/cos lat2)))]
    (* R 2 (Math/asin (Math/sqrt a)))))
