(ns webapp.core
  (:require [ring.adapter.jetty :as jetty])
  (:require [ring.middleware.params :as params])
  (:require [ring.middleware.keyword-params :as keyword-params])
  (:require [compojure.core :as cj])
  (:require [compojure.route :as route])
  )

(defn pre [children]
  (str "<pre>" children "</pre>")
  )

(defn div [& children]
  (str "<div>" children "</div>")
  )

(defn my-handler [request]
  {:status 200
   :body   (str
             (pre (:uri request))
             (pre (:query-string request))
             (pre (pr-str (:params request)))
             )
   }
  )

(cj/defroutes my-routes
  (cj/GET "/" [] my-handler)
  (cj/GET "/foo" [] my-handler)
  (route/not-found "Not Found")
  )

(def my-app2
  (params/wrap-params
    (keyword-params/wrap-keyword-params
      my-handler
      )
    )
  )

(def my-app
  (-> my-routes
      keyword-params/wrap-keyword-params
      params/wrap-params
      )
  )

(defn start-server []
  (jetty/run-jetty #'my-app
                   {:port  8080
                    :join? false
                    }
                   )
  )