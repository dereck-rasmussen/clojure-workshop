(defproject workreload "0.1.0-SNAPSHOT"
  :description "TODO"
  :url "TODO"
  :license {:name "TODO: Choose a license"
            :url "http://choosealicense.com/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [com.stuartsierra/component "0.4.0"]
                 ]
  :profiles {:dev {:dependencies [[org.clojure/tools.namespace "0.3.0"]
                                  [com.stuartsierra/component.repl "0.2.0"]
                                  ]
                   :source-paths ["dev"]}})
