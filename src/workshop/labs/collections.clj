(ns workshop.labs.collections)

(def scores {"Una" 1400, "Bob" 1240, "Cid" 1024})

(println (assoc scores "mel" 0))

(defrecord Player [name ranking])

(def una (->Player "Una" 43))

(println (get una :name))
(println (:name una))
;(println (una :name))

(defn next-round [game]
  (update-in game [:round] inc))

; game[:players].find {|p| p[:name] == "Una"}
;(defn add-score [game name points]
;  (let [players (get game [:players])
;        matches ((filter (fn [p] (= (:name p) name))) players)
;        player (first matches)
;        rank (get player [:ranking])
;        new-rank (+ rank points)
;        ]
;    (update-in game [:players player :ranking] new-rank)))
;

(defn add-score [game name points]
  (update-in game [:scores name] (fn [x] (+ x points)))
  )


(defn add-player [game name]
  (let [
        new-game (update-in game [:players] (fn [players] (conj players {:name name :ranking 0})))
        ]
    (update-in new-game [:scores] (fn [scores] (assoc scores name 0))))
  )