(ns {{raw-name/ns}}.env
  "Human language predicates for config and env vars."
  (:require
   [clojure.java.io :as io]
   [org.passen.malapropism.core :as malapropism]))

(def ^:private config-schema
  [:map
   [:env-key
    {:title "environment key"}
    :keyword]
   [:scm-rev
    {:title "scm rev"}
    :string]])

(defonce ^:private config (atom {}))

(defn lookup
  [& ks]
  (get-in @config ks))

(defn init!
  []
  (reset! config (-> (malapropism/with-schema config-schema)
                     (malapropism/with-values-from-file
                       (io/resource "local.edn"))
                     (malapropism/with-values-from-env)
                     (malapropism/verify!))))
