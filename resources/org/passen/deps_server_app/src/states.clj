(ns {{raw-name/ns}}.states
  (:require
   [{{raw-name/ns}}.env :as env]
   [{{raw-name/ns}}.jetty :as jetty]))

(def states
  [env/config jetty/jetty])

(defn start
  []
  (run! force states))
