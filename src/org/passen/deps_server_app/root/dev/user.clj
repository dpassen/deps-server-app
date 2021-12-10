(ns user
  (:require
   [clojure.java.io :as io]
   [clojure.tools.namespace.repl :refer [refresh set-refresh-dirs]]
   [{{raw-name/ns}}.env :as env]
   [integrant.core :as integrant]))

(def system nil)

(defn init
  []
  (set-refresh-dirs "dev" "src" "test")
  (env/init!)
  (alter-var-root
   #'system
   (fn [_system]
     (integrant/read-string (slurp (io/resource "system.edn"))))))

(defn start
  []
  (integrant/load-namespaces system)
  (alter-var-root #'system integrant/init))

(defn stop
  []
  (alter-var-root
   #'system
   (fn [system]
     (when system
       (integrant/halt! system)))))

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
  ,)
