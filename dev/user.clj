 (ns user
  (:require [clojure.tools.namespace.repl :refer [refresh]]))

 (defn start
   "Start the app"
   [])

 (defn stop
   "Stop the app"
   [])

 (defn reset []
   (stop)
   (refresh :after 'user/start))
