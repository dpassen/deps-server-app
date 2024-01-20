(ns user
  (:require
   [clojure.tools.namespace.repl :refer [refresh set-refresh-dirs]]
   [{{raw-name/ns}}.states :as states]
   [redelay.core :as redelay]))

(defn init
  []
  (set-refresh-dirs "dev" "src" "test"))

(defn start
  []
  (states/start))

(defn stop
  []
  (redelay/stop))

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
  ;;
  )
