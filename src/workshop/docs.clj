(ns workshop.docs)
(use 'clojure.repl)

(println "== Print the type")
(println "Type is: " (type 'hello))

(println "== To read docs on a function")
(doc when)

(println "== Search for documentation")
(find-doc "remainder")

(println "== Autocomplete options")
(println (apropos "whe"))

(println "== View source")
(source when)

(println "== List functions in file")
(println (dir workshop.docs))