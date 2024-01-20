(ns {{raw-name/ns}}.app
  (:require
   [clojure.tools.logging :as log]
   [{{raw-name/ns}}.states :as states])
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
  (log/info "Starting app")
  (states/start)
  (log/info "App started"))
