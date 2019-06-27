(ns webapp.pedestal_web
  (:require [io.pedestal.http :as http])
  (:require [hiccup.core :as html])
  )

(defn respond-hello [request]
  {:status 200
   :body (html
           [:p "Foo page"]
           [:pre {:style "color: blue"}]
           )
   }
  )

(def routes
  #{
    ["/" :get [http/html-body `respond-hello] :route-name :home]
    ["/greet" :get [] `respond-hello]
    }
  )

(defn start-server []
  (http/server
    (http/create-server
      {::http/port 8080
       ::http/routes routes
       ::http/type :jetty
       }
      )
    )
  )

