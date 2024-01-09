(ns av-clj-ws.web-handler
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.handler :as handler]
            [ring.util.response :as response]
            [ring.middleware.resource :refer [wrap-resource]]
            [clojure.tools.nrepl.server :as repl-server]
            [clojure.tools.nrepl.transport :as repl-transport]))

;; Start the nREPL server
(defonce repl-server
  (repl-server/start-server :port 4005))

(defn repl-evaluate [code]
  (let [conn (repl-transport/tcp-client "localhost" 4005)]
    (try
      (repl-transport/write conn {:op :eval :code code})
      (repl-transport/read conn)
      (finally
        (repl-transport/close conn)))))

(defn repl-ui []
  "Define your REPL UI here."
  [:html
   [:head
    [:title "REPL UI"]]
   [:body
    [:h1 "Welcome to the REPL UI"]
    [:p "You can interact with the Leiningen REPL here."]
    [:form {:method "post" :action "/eval"}
     [:input {:type "text" :name "code"}]
     [:input {:type "submit" :value "Evaluate"}]]]])

(defroutes app-routes
  (GET "/" [] (repl-ui))
  (POST "/eval" [code]
    (let [result (repl-evaluate code)]
      (response/ok (str "Result: " result)))))

(def app
  (-> (handler/site app-routes)
      (handler/wrap-defaults (assoc-in handler/default-interceptors [:session :store] (-> (handler/default-interceptors [:session :store])
                                                                                            (assoc-in [::ring.session.cookie-attrs :path] "/"))))
      (handler/wrap-params)))

