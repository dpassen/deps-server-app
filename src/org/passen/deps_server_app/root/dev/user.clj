(ns user
  (:require
   [clojure.java.io :as io]
   [clojure.tools.namespace.repl :refer [refresh set-refresh-dirs]]
   [{{raw-name/ns}}.env :as env]
   [integrant.core :as integrant]))

(def system (volatile! nil))

(defn init
  []
  (set-refresh-dirs "dev" "src" "test")
  (env/init!)
  (vreset! system (integrant/read-string (slurp (io/resource "system.edn")))))

(defn start
  []
  (integrant/load-namespaces @system)
  (vswap! system integrant/init))

(defn stop
  []
  (vswap! system (fn [system] (when system (integrant/halt! system)))))

(defn reset
  []
  (vreset! system nil))

(defn- go*
  []
  (init)
  (start))

(defn go
  []
  (stop)
  (refresh :after 'user/go*))

(comment
  (go)
  (stop)
  (reset)
  ,)
