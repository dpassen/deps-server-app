((nil
  (compile-command . "just build"))
 (clojure-mode
  (cider-clojure-cli-aliases . ":dev:test")
  (cider-log-framework-name . "Logback")
  (cider-ns-refresh-before-fn . "user/stop")
  (cider-ns-refresh-after-fn . "user/go*")))
