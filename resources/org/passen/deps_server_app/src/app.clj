(ns {{raw-name/ns}}.app
  (:require
   [clojure.tools.logging :as log]
   [{{raw-name/ns}}.states :as states])
  (:gen-class))

(defn- set-uncaught-exception-handler!
  []
  (Thread/setDefaultUncaughtExceptionHandler
   (fn [thread ex]
     (log/fatal ex "Uncaught exception on" (Thread/.getName thread)))))

(defn -main
  [& _args]
  (set-uncaught-exception-handler!)
  (log/info "Starting app")
  (states/start)
  (log/info "App started"))
