(ns workshop.game.game-v4
  (:require [clojure.core.async :as async :refer [>! >!! <! <!! chan go]])
  )

;;; User interface messages

(def initial-message
  "I am thinking of a number from 1 to 10")

(def prompt-message
  "Enter your guess:")

(def messages
  {:quit "Bye!"
   :win "Correct. You Win!"
   :low "Too low."
   :high "Too high."})

;;; Functional core

(defn new-game []
  {
   :secret (inc (rand-int 10))
   :input (chan 1)
   :output (chan 1)
   })

(defn read-guess [game]
  (assoc game :guess (read)))

(defn win? [game]
  (= (:guess game) (:secret game)))

(defn quit? [game]
  (not (number? (:guess game))))

(defn low? [game]
  (< (:guess game) (:secret game)))

(defn evaluate [game]
  (assoc game :result
              (cond
                (quit? game) :quit
                (win? game) :win
                (low? game) :low
                :else :high)))

(defn response-message [game]
  (get messages (:result game)))

(def finished-results #{:quit :win})

(defn finished? [game]
  (contains? finished-results (:result game)))

;;; Control flow

(defn play-game2 []
  (let [initial-game (new-game)]
    ;(println initial-message)
    (>! (:output new-game) initial-message)
    (loop []
      (println prompt-message)
      (let [game (evaluate (read-guess initial-game))]
        (println (response-message game))
        (when-not (finished? game)
          (recur))))))

(defn play-game []
  (let [
        initial-game (new-game)
        input (:input initial-game)
        output (:output initial-game)
        ;printer (go (do
        ;              (println (<! output))
        ;              (!> input :trigger)
        ;              )
        ;            )
        reader (go (>! input (read)))
        control (async/go-loop [state []]
                  (let [val (<! input)]
                    (if val
                      (do (>! output val)
                          (println val)
                          (recur (conj state val)))
                      (do (async/close! output)
                          state))))
        ]
    ))
    ;(println initial-message)
    ;(>! output initial-message)
    ;(loop []
    ;  (println prompt-message)
    ;  (let [game (evaluate (read-guess initial-game))]
    ;    (println (response-message game))
    ;    (when-not (finished? game)
    ;      (recur))))))

(defn -main [& args]
  (play-game)
  )
;;; Functional test harness

;(defn new-test-game [secret]
;  {:secret secret})
;
;(defn provide-guess [game guess]
;  (assoc game :guess guess))
;
;(defn finished-reduced [game]
;  (if (finished? game)
;    (reduced game)
;    game))
;
;(defn step-test-game [game guess]
;  (-> game
;      (provide-guess guess)
;      evaluate
;      finished-reduced))
;
;(defn play-test-game [secret guesses]
;  (reductions step-test-game (new-test-game secret) guesses))


;(defn take-until [pred coll]
;  (lazy-seq
;    (when-let [s (seq coll)]
;      (if (pred (first s))
;        (cons (first s) nil)
;        (cons (first s) (take-until pred (rest s)))))))

;(defn play-test-game [secret guesses]
;  (take-until finished?
;              (reductions step-test-game (new-test-game secret) guesses)))