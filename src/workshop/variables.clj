(ns workshop.variables)

(let [a 3
      b 4
      sum (+ a b)]
  (println a + b)
  (println sum)
  sum)

; :else evaluates to true, so last statement always matches
(let [x 100]
  (cond
    (< x 10) 5
    (< x 5) 2
    :else nil))