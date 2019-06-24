(ns workshop.fizz-buzz)

(defn divisible? [x y] (zero? (rem x y)))

(defn fizz-buzz-str [i]
  (let [div-by-3 (divisible? i 3)
        div-by-5 (divisible? i 5)]
  (cond
    (and div-by-3 div-by-5) "FizzBuzz"
    div-by-3 "Fizz"
    div-by-5 "Buzz"
    :else i
    )
  )
)

(defn fizzbuzz [max]
  (dotimes [i max]
    (println (fizz-buzz-str i))))

(fizzbuzz 16)