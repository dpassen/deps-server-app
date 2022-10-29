(ns {{raw-name/ns}}.web.routes)

(def routes
  ["/"
   [["info" {:get ::info-handler}]
    [true ::not-found]]])
