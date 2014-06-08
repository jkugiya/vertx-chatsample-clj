(ns chatsample-clj.core
  (:require [vertx.core :as vertx]
            [vertx.net :as net]
            [vertx.stream :as stream]
            [vertx.eventbus :as eb]))
(def ^:dynamic **broadcast-address** "broadcast-address")
(defn -connect-handler [sock]
  (let [message-handler (fn [event] (stream/write sock event))
        remote-addr (net/remote-address sock)
        addr-info (format "%s:%d"  (:host remote-addr) (:port remote-addr))
        data-handler (fn [data] (let [now (java.time.ZonedDateTime/now)]
                                      (eb/publish **broadcast-address**
                                                  (format "%s<%s> %s" now addr-info data))))]
    (eb/on-message **broadcast-address** message-handler)
    (-> sock
      (stream/write (format "Welcome to the chat %s" addr-info))
      (stream/on-data data-handler)
      (net/on-close #(eb/unregister-handler **broadcast-address** message-handler)))))
(defn init[]
  (let [server (net/server)]
    (-> server
      (net/on-connect -connect-handler)
      (net/listen 1234 "localhost"))))