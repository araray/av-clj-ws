(defproject av-clj-ws "0.1.0-DEV"
  :description "A simple web service"
  :url "https://araray.org"
  :license {:name "Apache License, Version 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [selmer "1.12.59"]
                 [clj-commons/clj-yaml "1.0.27"]
                 [cheshire "5.11.0"]
                 [ring/ring-core "1.11.0"]
                 [ring/ring-jetty-adapter "1.11.0"]
;;                 [ring/ring-devel "1.11.1"]
                 [ring/ring-defaults "0.4.0"]
                 [org.slf4j/slf4j-api "2.0.11"]
                 [ch.qos.logback/logback-classic "1.4.14"]
                 [compojure "1.6.2"] ;; Compojure for routing
;;                 [io.github.nextjournal/clerk "0.15.957"] ;; Clerk journal at https://github.com/nextjournal/clerk
                 [nrepl "1.1.0"]]
  :plugins [[lein-cloverage "1.2.4"]
            [lein-vanity "0.2.0"]
            [lein-ancient "0.7.0"]
            [lein-ring "0.12.6"]]
  :ring {:handler {av-clj-ws.core/app
                   av-clj-ws.web-handler/app}
         :auto-reload true
         :port 8080}
  :main ^:skip-aot av-clj-ws.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

