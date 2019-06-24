(defproject clojure-training "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/math.combinatorics "0.1.4"]
                 [org.clojure/data.xml "0.2.0-alpha5"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/core.async "0.4.500"]
                 [org.jline/jline "3.6.1"]
                 [org.clojure/test.check "0.9.0"]]
  :exclusions [org.clojure/clojure]
  :plugins [[lein-cljsbuild "1.0.3" :exclusions [org.clojure/clojure]]]
  :local-repo "repository")
