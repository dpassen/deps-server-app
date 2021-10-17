(ns {{raw-name/ns}}.web.middleware.errors-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [{{raw-name/ns}}.web.middleware.errors :as errors]
   [ring.util.http-predicates :as http-predicates]
   [ring.util.http-status :as http-status]
   [spy.core :as spy]))

(deftest wrap-errors-test
  (let [catching-handler (errors/wrap-errors (spy/stub {:status http-status/ok}))
        response         (catching-handler ::request)]
    (testing "returns ring response from handler when it doesn't throw"
      (is (map? response))
      (is (http-predicates/ok? response))))
  (let [catching-handler (errors/wrap-errors
                          (spy/mock (fn [_] (throw (ex-info "test error" {})))))
        response         (catching-handler ::request)]
    (testing "returns error ring response from handler when it throws"
      (is (http-predicates/internal-server-error? response)))
    (testing "reponse contains uuid for the error log"
      (is (uuid? (get-in response [:body :error-log-id]))))))
