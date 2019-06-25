(ns workshop.labs.collections-test
  (:require [clojure.test :refer :all])
  (:require [workshop.labs.collections :as col]))

(def game
  {:round 2
   :players #{{:name "Una" :ranking 43}
              {:name "Bob" :ranking 77}
              {:name "Cid" :ranking 33}}
   :scores {"Una" 1400
            "Bob" 1240
            "Cid" 1024}})

(defn isEqual [actual expected]
  (is (= actual expected))
  )

;(println (next-round game))
(deftest next-round
  (testing "increments the round"
    (let [
          result (col/next-round game)
          ]
      (is (= (result :round) 3)))))


(deftest add-score
  (testing "adds given points to score"
    (let [
          result (col/add-score game "Una" 100)
          ]
      (is (= (get-in result [:scores, "Una"]), 1500)))))

;(println (get-in (add-score game "Una" 2) [:scores "Una"]) 1402)


(deftest add-player-test
  (testing "adds player with 0 score"
    (let [result (col/add-player game "Alice")
              ]
      (is (get-in result [:scores "Alice"]), 0)))

  (testing "adds player to players set"
    (let [result (col/add-player game "Alice")
          players (:players result)
          alice (first (filter (fn [player] (= (:name player) "Alice")) players))
          ]
      (isEqual (:ranking alice), 0)
      )
    )
  )

(run-tests)