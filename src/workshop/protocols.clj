(ns workshop.protocols
  [
   :require [workshop.test :as t]
   ])

(defprotocol TextApp
  (output [this message]
    "Emit message as output."
    )
  (input [this]
    "Read on value as input."
    )
  )

(defrecord ConsoleApp []
  TextApp
  (output [this message]
    (println message)
    )
  (input [this]
    (read)
    )
  )

(defrecord MockApp [inputs outputs]
  TextApp
  (output [this message]
    (swap! outputs conj message)
    )
  (input [this]
    (let [
          old-and-new (swap-vals! inputs rest)
          old-inputs (first old-and-new)
          ]
      (first old-inputs)
      )
    )
  )

(defn new-mock-app [inputs]
  (->MockApp
    (atom [])
    (atom inputs)
    )
  )

(t/is
  (let [
        inputs (atom [])
        outputs (atom [])
        app (->MockApp inputs outputs)
        ]
    (output app "Hello, world")
    )
    ["Hello, world"]
  )

(t/is
  (input (->ConsoleApp))
  42
  )