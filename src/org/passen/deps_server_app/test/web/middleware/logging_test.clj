(ns {{raw-name/ns}}.web.middleware.logging-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [{{raw-name/ns}}.web.middleware.logging :as logging]
   [spy.core :as spy]))

(deftest wrap-logging-tests
  (let [test-request    {:request-method ::request-method
                         :uri            ::uri}
        test-response   {:status ::status}
        handler         (spy/stub test-response)
        logging-handler (logging/wrap-logging handler)]
    (with-redefs [logging/log-request  (spy/stub)
                  logging/log-response (spy/stub)]
      (let [response (logging-handler test-request)]
        (testing "wrap-logging calls the handler-fn"
          (is (spy/called-once-with? handler test-request)))
        (testing "calls log-request"
          (is (spy/called-once-with? logging/log-request test-request)))
        (testing "calls log-response"
          (is (spy/called-once-with? logging/log-response test-request test-response)))
        (testing "returns the response"
          (is (= test-response response)))))))
