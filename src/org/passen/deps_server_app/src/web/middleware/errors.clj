(ns {{raw-name/ns}}.web.middleware.errors
  (:require
   [clojure.tools.logging :as log]
   [ring.util.http-response :as http-response])
  (:import
   (java.util UUID)))

(defn wrap-errors
  [handler]
  (fn [request]
    (try
      (handler request)
      (catch Throwable t
        (let [error-log-id (UUID/randomUUID)]
          (log/error t "Unhandled server error" {:error-log-id error-log-id})
          (http-response/internal-server-error
           {:message      "Unhandled server error."
            :error-log-id error-log-id}))))))
