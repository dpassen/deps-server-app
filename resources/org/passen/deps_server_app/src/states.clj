(ns {{raw-name/ns}}.states
  (:require
   [{{raw-name/ns}}.env :as env]
   [{{raw-name/ns}}.jetty :as jetty]))

(def states
  [env/config jetty/jetty])

(defn start
  []
  (doall
   (for [state states]
     (doto state force))))
