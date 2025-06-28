(ns {{raw-name/ns}}.web
  (:require
   [bidi.ring]
   [{{raw-name/ns}}.web.handlers :as handlers]
   [{{raw-name/ns}}.web.routes :as routes]
   [muuntaja.middleware]
   [ring.logger]))

(def app
  (-> routes/routes
      (bidi.ring/make-handler handlers/route-handlers)
      (muuntaja.middleware/wrap-format)
      (ring.logger/wrap-with-logger)))
