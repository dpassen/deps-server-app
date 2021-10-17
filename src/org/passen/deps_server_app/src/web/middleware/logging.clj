(ns {{raw-name/ns}}.web.middleware.logging
  (:require
   [clojure.tools.logging :as log]
   [ring.util.http-predicates :as http-predicates]))

(defn log-request
  [data]
  (let [details (select-keys data [:request-method :uri])]
    (log/info "API request" details)))

(defn log-response
  [{:keys [request-method uri]} {:keys [status] :as response}]
  (let [details {:request-method request-method :uri uri :status status}]
    (cond
      (http-predicates/client-error? response)
      (log/warn "API client error response" details)

      (http-predicates/server-error? response)
      (log/error "API server error response" details)

      :else
      (log/info "API success response" details))))

(defn wrap-logging
  "Logging Ring HTTP requests and responses."
  [handler]
  (fn [request]
    (log-request request)
    (let [response (handler request)]
      (log-response request response)
      response)))
