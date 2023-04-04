(ns {{raw-name/ns}}.integrant.jetty
  (:require
   [{{raw-name/ns}}.web :as web]
   [integrant.core :as integrant]
   [ring.adapter.jetty9 :as ring-jetty]))

(defmethod integrant/init-key :{{raw-name/ns}}.integrant/jetty
  [_ opts]
  (ring-jetty/run-jetty web/app opts))

(defmethod integrant/halt-key! :{{raw-name/ns}}.integrant/jetty
  [_ jetty-adapter]
  (ring-jetty/stop-server jetty-adapter))
