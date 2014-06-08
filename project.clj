(defproject chatsample-clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [io.vertx/clojure-api "1.0.2"]]
  :plugins [[lein-vertx "0.3.0-SNAPSHOT"]]
  :vertx {:main chatsample-clj.core/init
          :author "FIXME: Your name"
          :keywords ["FIXME: keywords"]
          :developers ["FIXME: other developers"]})
