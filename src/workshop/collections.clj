(ns workshop.collections)

(def set #{:a :b :c})
(def vector [])
(def list '(1,2,3))
(def map {:a 1, :b 2})

; conj disj
(println (assoc map :a 3))
(println (dissoc map :b ))