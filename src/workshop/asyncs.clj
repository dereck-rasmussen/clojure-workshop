(ns workshop.asyncs
   (:require [clojure.core.async
              :as async
              :refer [>! >!! <! <!! go chan]])
  (:require [workshop.test :as test])
  )

(defn demo1 []
  (let [c1 (chan 1)]
    (>!! c1 :a)
    (<!! c1)
    )
  )

(defn demo2 []
  (let [
        c1 (chan 1)
        g1 (go (println :in-the-go (<! c1)))
        g2 (go (<! (async/timeout 3000))
               (>! c1 :done)
               )
        ]
    (<!! g2)
    )
  )

(test/is demo1 "")
(test/is demo2 "")