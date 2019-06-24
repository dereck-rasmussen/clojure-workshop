(ns workshop.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println "good morning")
  (println x "Hello, World!"))

(defn fahrenheit->celsius
  "Converts degrees farenheit to degress celsius"
  [degrees]
  (/ (* (- degrees 32) 5) 9))

(defn celsius->fahrenheit
  [degrees]
  (+ 32 (/ (* degrees 5) 9))
  )
(println (fahrenheit->celsius 32)) ; 0
(println (fahrenheit->celsius 212)) ; 100
(println (fahrenheit->celsius -40)) ; -40

(println (celsius->fahrenheit 0)) ; 32
(println (celsius->fahrenheit 100)) ; 212
(println (celsius->fahrenheit -40)) ; -40

