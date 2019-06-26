(ns workshop.atoms [
                    :require [workshop.test :as t]
                    ])


; Boxes for immutable data
(def a (atom 5))

(t/is (deref a), 5)
(t/is @a 5)
(t/is (reset! a 6), 6)
(t/is (swap! a inc), 7)

(def m (atom {
              :foo 1
              :bar "hi"
              }))

(let [
      new (swap! m assoc :foo 3)
      val (deref m)
      foo (get val :foo)
      ]
  (t/is foo 3)
  )
