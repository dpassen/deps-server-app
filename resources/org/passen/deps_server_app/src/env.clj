(ns {{raw-name/ns}}.env
  "Human language predicates for config and env vars."
  (:require
   [clojure.java.io :as io]
   [org.passen.malapropism.core :as malapropism]
   [redelay.core :refer [defstate]]))

(def ^:private config-schema
  [:map
   [:env-key
    {:title "environment key"}
    :keyword]
   [:http-port
    {:title   "http port"
     :default 8080}
    :int]
   [:scm-rev
    {:title "scm rev"}
    :string]])

(defstate config
  (-> (malapropism/with-schema config-schema)
      (malapropism/with-values-from-file
        (io/resource "local.edn"))
      (malapropism/with-values-from-env)
      (malapropism/verify!)))

(defn lookup
  [& ks]
  (get-in @config ks))
