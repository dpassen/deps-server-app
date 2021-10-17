(ns {{raw-name/ns}}.web
  (:require
   [bidi.ring]
   [{{raw-name/ns}}.web.handlers :as handlers]
   [{{raw-name/ns}}.web.middleware.errors :as errors]
   [{{raw-name/ns}}.web.middleware.logging :as logging]
   [{{raw-name/ns}}.web.routes :as routes]
   [muuntaja.middleware]))

(def app
  (-> routes/routes
      (bidi.ring/make-handler handlers/route-handlers)
      muuntaja.middleware/wrap-format
      errors/wrap-errors
      logging/wrap-logging))
