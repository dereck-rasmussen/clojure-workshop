(ns workshop.destructuring
  (:require [workshop.test :as test])
  )

(def stuff [7 8 9 10 11]) ;=> #'user/stuff

;; Bind a, b, c to first 3 values in stuff
(test/is
  (let [[a b c] stuff]
    (list (+ a b) (+ b c))
    )
  (15, 17)
  )

(test/is
  (let [[a b c d e f] stuff]
    (list d e f)
    )
  (10 11 nil)
  )

; (-> some-data :users first :name) ; => "Alice"