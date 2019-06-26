(ns workshop.game.game-v4
  (:require [clojure.core.async :as async :refer [go <! >! <!! >!! chan]])
  (:require [workshop.game.game-v3 :refer :all])
  )

;;; With Channels!

(defn add-guess [game guess]
  (assoc game :guess guess))

(defn go-game [initial-game]
  (let [output (:output initial-game)
        input (:input initial-game)
        prompts (:prompts initial-game)]
    (go
      (>! output initial-message)
      (loop []
        (>! prompts prompt-message)
        (let [game (evaluate (add-guess initial-game (<! input)))]
          (>! output (response-message game))
          (if (finished? game)
            (do (async/close! input)
                (async/close! output)
                (async/close! prompts))
            (recur)))))))

(defn play-channel-game []
  (let [input (chan 1)
        output (chan 1)
        prompts (chan 1)
        initial-game (assoc (new-game)
                       :input input
                       :output output
                       :prompts prompts)
        t1 (async/thread
             (loop []
               (let [msg (<!! prompts)]
                 (when msg
                   (println msg)
                   (>!! input (read))
                   (recur)))))
        t2 (async/thread
             (loop []
               (let [msg (<!! output)]
                 (when msg
                   (println msg)
                   (recur)))))]
    (<!! (go-game initial-game))))

(defn test-channel-game [secret inputs]
  (let [input (chan 100)
        output (chan 100)
        prompts (chan 1)
        initial-game {:secret  secret
                      :input   input
                      :output  output
                      :prompts prompts}
        g1 (async/go-loop [ins inputs]
                          (let [msg (<! prompts)]
                            (when msg
                              (>! output msg)
                              (>! input (first ins))
                              (recur (rest ins)))))
        g2 (go-game initial-game)]
    (async/onto-chan input inputs)
    (<!! g2)
    (let [final (async/into [] output)]
      (async/alt!!
        final ([x] x)
        (async/timeout 1000) :timeout))))

(defn -main []
  (play-channel-game))