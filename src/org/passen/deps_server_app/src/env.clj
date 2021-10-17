(ns {{raw-name/ns}}.env
  "Human language predicates for config and env vars."
  (:require
   [clojure.java.io :as io]
   [clojure.tools.logging :as log]
   [omniconf.core :as omniconf]))

(def lookup omniconf/get)

(def ^:private omniconf-config
  {:env-key         {:description "environment key"
                     :type        :keyword
                     :required    true}
   :scm-rev         {:description "scm rev"
                     :type        :keyword
                     :required    true}})

(defn init!
  []
  (omniconf/set-logging-fn (fn [& args] (apply #(log/info %) args)))
  (omniconf/define omniconf-config)
  (omniconf/populate-from-file (io/resource "local.edn"))
  (omniconf/populate-from-env)
  (omniconf/verify :silent true))
