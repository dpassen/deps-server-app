(ns {{raw-name/ns}}.web.routes-test
  (:require
   [bidi.bidi :as bidi]
   [clojure.test :refer [deftest is testing]]
   [{{raw-name/ns}}.web.routes :as routes]))

(defn- get-handler
  [route]
  (:handler (bidi/match-route routes/routes route :request-method :get)))

(deftest invalid-routes-test
  (testing "undefined routes are 404s"
    (is (= ::routes/not-found (get-handler "/")))
    (is (= ::routes/not-found (get-handler "/foo")))
    (is (= ::routes/not-found (get-handler "/api/foo")))))
