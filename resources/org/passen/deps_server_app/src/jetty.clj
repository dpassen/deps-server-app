(ns {{raw-name/ns}}.jetty
  (:require
   [{{raw-name/ns}}.env :as env]
   [{{raw-name/ns}}.web :as web]
   [redelay.core :refer [defstate]]
   [ring.adapter.jetty9 :as ring-jetty]))

(defstate jetty
  :start
  (ring-jetty/run-jetty web/app {:port  (env/lookup :http-port)
                                 :join? false})
  :stop
  (ring-jetty/stop-server this))
