(ns {{raw-name/ns}}.web.handlers
  (:require
   [{{raw-name/ns}}.env :as env]
   [{{raw-name/ns}}.web.routes :as routes]
   [ring.util.http-response :as http-response]))

(defn not-found-handler
  [_request]
  (http-response/not-found))

(defn info-handler
  "Return application information."
  [_request]
  (http-response/ok
   {:app :{{artifact/id}}
    :env (env/lookup :env-key)
    :scm (env/lookup :scm-rev)}))

(def route-handlers
  {::routes/info-handler info-handler
   ::routes/not-found    not-found-handler})
