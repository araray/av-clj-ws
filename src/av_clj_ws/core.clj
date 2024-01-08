(ns av-clj-ws.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.util.response :as response]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.params :refer [wrap-params]]
            [cheshire.core :as json]
            [compojure.core :refer [defroutes GET POST ANY]]
            [compojure.route :as route]
            ))

(defn handle-root [request]
  (response/content-type (response/response "Welcome to the root!") "text/plain"))

(defn handle-app [request]
  (response/content-type (response/response "This is the app route!") "text/plain"))

(defn handle-something-else [request]
  (response/content-type (response/response "You've reached /somethingelse") "text/plain"))

(defn handle-generic [request]
  (let [params (cond
                 (:query-params request) (:query-params request)
                 (:form-params request)  (:form-params request)
                 :else {})]
    (response/content-type 
      (response/response (json/encode {:message "Handling a request"
                                       :params params}))
      "application/json")))

(defroutes app-routes
  (GET "/" [] handle-root)
  (GET "/app" [] handle-app)
  (GET "/somethingelse" [] handle-something-else)
  (GET "/teste" [] handle-app)
  (ANY "*" [] handle-generic))

(def app
  (-> (wrap-defaults app-routes site-defaults)
      (wrap-params)))

(defn -main [& args]
  (jetty/run-jetty app {:port 8080 :join? false}))

