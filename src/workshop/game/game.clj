(ns workshop.game.game)

; guess 1-10
; too-high or too-low or correct

; rand-in
; read-line => string
; read => clojure code input

; Mixing concerns
; IO, intialization, flow control
; its very hard to test
(defn play2 [secret]
  (println "Guess between 1-10: (" secret ")")
  (loop []
    (println "enter your guess: ")
    (let [guess (read)]
      (cond
        (not (number? guess)) (println "bye!")
        (= guess secret) (println "you win")

        (> guess secret) (do
                           (println "too high")
                           (recur)
                           )
        (< guess secret) (do
                           (println "too low")
                           (recur)
                           )
        :else (println "error")
        )
      )
    )
  )

(defn sample-loop-with-recur [i]
  (if (< i 10)
    (recur (inc i))
    i
    )
  )

(defn sample-loop []
  (loop [i 0]
    (if (< i 10)
      (recur (inc i))
      i
      )
    )
  )

(defn sample-loop2 []
  (loop [
         i 0
         j 10
         ]
    (when (< (* i j) 100)
      (println i j (* i j))
      (recur (inc i) (+ j 5))
      )
    )
  )


(defn play [num prompt]
  (println prompt)
  (let [
        guess (read)
        ]
    (println num guess)
    (cond
      (= num -1) 0
      (= guess num) (play -1 "You win!")
      (> guess num) (play num "Too high.")
      (< guess num) (play num "Too low.")
      :else "error"
      )
    )
  )

(defn play-game []
  (play (inc (rand-int 10)) "Guess a number between 1-10")
  )

(defn -main [& args]
  (play2 (inc (rand-int 10)))
  )