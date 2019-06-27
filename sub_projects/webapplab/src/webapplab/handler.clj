(ns webapplab.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn handle-hello [request]
  {:status 200
   :body (str "Hello " (:name (:params request)))
   }
  )

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/hello/:id" [id] (str "hello " id))
  (route/not-found "Not Found"))

(defn print-url [handler]
  (fn [request]
    (println (:url request))
    (handler request)
    )
  )

(def app
  (wrap-defaults app-routes site-defaults))
