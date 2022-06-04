(ns {{raw-name/ns}}.app
  (:require
   [clojure.java.io :as io]
   [clojure.tools.logging :as log]
   [{{raw-name/ns}}.env :as env]
   [integrant.core :as integrant])
  (:gen-class))

(defn- set-uncaught-exception-handler!
  []
  (Thread/setDefaultUncaughtExceptionHandler
   (reify Thread$UncaughtExceptionHandler
     (uncaughtException [_this thread ex]
       (log/fatal ex "Uncaught exception on" (.getName thread))))))

(defn -main
  [& _args]
  (set-uncaught-exception-handler!)
  (env/init!)
  (log/info "Starting app")
  (let [system (integrant/read-string (slurp (io/resource "system.edn")))]
    (integrant/load-namespaces system)
    (integrant/init system))
  (log/info "App started"))
