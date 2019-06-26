(ns workshop.game.game-v2)

(defn new-game []
  {
   :secret (inc (rand-int 10))
   :guess  -1
   :status :start
   }
  )

(comment
  {:secret ...
   :guess  ...
   }
  )


(defn response-message [state]
  (let [
        guess (:guess state)
        secret (:secret state)
        ]
    (cond
      (= guess secret) "you win"
      (> guess secret) "too high"
      (< guess secret) "too low"
      :else (println "error")
      )
    )
  )

(defn read-guess [state]
  (-> state
      (assoc :guess (read))
      (assoc :prompt (response-message state))
      )
  )

(defn finished? [state]
  (cond
    (not (number? (:guess state))) true
    (= (:guess state) (:secret state)) true
    :else false
    )
  )

(defn run-game [state]
  (let [game (read-guess state)]
    (println (:prompt game))
    (when-not (finished? game)
      (recur state)
      )
    )
  )

(defn play3 []
  (let [initial-state (new-game)]
    (println "Guess between 1-10:")
    (println "enter your guess: ")
    (run-game initial-state)
    )
  )

;(defn play2 []
;  (let [initial-state (new-game)]
;    (println "Guess between 1-10:")
;    (loop []
;      (println "enter your guess: ")
;      (let [game (read-guess initial-state)]
;        (println (response-message game))
;        (when-not (finished? game)
;          (recur)
;          )
;        )
;      )
;    )
;  )

;(defn play []
;  (println "Guess between 1-10:")
;  (loop [
;         state (new-game)
;         ]
;    (let [
;          guess (:guess state)
;          secret (:secret state)
;          ]
;      (cond
;        (not (number? guess)) (println "bye!")
;        (= guess secret) (println "you win")
;
;        (> guess secret) (do
;                           (println "too high")
;                           (recur (read-guess state))
;                           )
;        (< guess secret) (do
;                           (println "too low")
;                           (recur (read-guess state))
;                           )
;        :else (println "error")
;        )
;      )
;    )
;  )

(defn -main [& args]
  (play3)
  )