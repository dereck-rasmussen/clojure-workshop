(ns workshop.game.game-v3)

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
  {:secret (inc (rand-int 10))})

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

(defn play-game []
  (let [initial-game (new-game)]
    (println initial-message)
    (loop []
      (println prompt-message)
      (let [game (evaluate (read-guess initial-game))]
        (println (response-message game))
        (when-not (finished? game)
          (recur))))))

(defn -main [& args]
  (play-game))

;;; Functional test harness

(defn new-test-game [secret]
  {:secret secret})

(defn provide-guess [game guess]
  (assoc game :guess guess))

(defn finished-reduced [game]
  (if (finished? game)
    (reduced game)
    game))

(defn step-test-game [game guess]
  (-> game
      (provide-guess guess)
      evaluate
      finished-reduced))

(defn play-test-game [secret guesses]
  (reductions step-test-game (new-test-game secret) guesses))


;(defn take-until [pred coll]
;  (lazy-seq
;    (when-let [s (seq coll)]
;      (if (pred (first s))
;        (cons (first s) nil)
;        (cons (first s) (take-until pred (rest s)))))))

;(defn play-test-game [secret guesses]
;  (take-until finished?
;              (reductions step-test-game (new-test-game secret) guesses)))

;;; Testing with-redefs

(def ^:dynamic *guesses*)
(def ^:dynamic *output*)

(defn play-redef-game [secret guesses]
  (binding [*guesses* guesses
            *output* []]
    (with-redefs [rand-int (fn [& args] (dec secret))
                  println (fn [& args]
                            (set! *output*
                                  (conj *output* (apply print-str args))))
                  read (fn []
                         (let [guess (first *guesses*)]
                           (set! *guesses* (rest *guesses*))
                           (set! *output* (conj *output* guess))
                           guess))]
      (play-game)
      *output*)))

;;; With protocols

(defprotocol TextApp
  (output [this message]
    "Emit message as output.")
  (input [this]
    "Read one value as input."))

(defrecord ConsoleApp []
  TextApp
  (output [this message]
    (println message))
  (input [this]
    (read)))

(defrecord MockApp [inputs outputs]
  TextApp
  (output [this message]
    (swap! outputs conj message))
  (input [this]
    (let [old-and-new (swap-vals! inputs rest)
          old-inputs (first old-and-new)
          value (first old-inputs)]
      (swap! outputs conj value)
      (first old-inputs))))

(defn new-mock-app [inputs]
  (->MockApp (atom inputs)
             (atom [])))

(defn read-injected-guess [game]
  (assoc game :guess (input (:app game))))

(defn play-injected-game [game]
  (let [app (:app game)]
    (output app initial-message)
    (loop []
      (output app prompt-message)
      (let [game (evaluate (read-injected-guess game))]
        (output app (response-message game))
        (when-not (finished? game)
          (recur))))
    game))

(defn test-injected-game [secret inputs]
  (-> (play-injected-game
        {:secret secret
         :app (new-mock-app inputs)})
      :app
      :outputs
      deref))