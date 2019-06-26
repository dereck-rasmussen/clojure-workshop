(ns workshop.channels
  (:require [clojure.core.async
             :as async
             :refer [<! <!! >! >!! go chan thread]]))

(defn demo1 []
  (let [c1 (chan 1)]
    (>!! c1 :a)
    (<!! c1)))

(defn demo2 []
  (let [c1 (chan 1)
        g1 (go (println
                 :in-the-go
                 (<! c1)))]
    (>!! c1 :a)))

(defn demo3 []
  (let [c1 (chan 1)
        g1 (go (println
                 :in-the-go
                 (<! c1)))
        g2 (go (<! (async/timeout 3000))
               (>! c1 :done))]
    (<!! g2)))

(defn demo4 []
  (let [c1 (chan 10)
        g1 (go (tap> [:one (<! c1)])
               #_(tap> [:two (<! c1)]))
        g2 (go (>! c1 :a)
               (tap> "Put :a to channel")
               (>! c1 :b)
               (tap> "Put :b to channel"))]
    (async/alts!! [g2 (async/timeout 3000)])))

(defn demo5 []
  (let [g1 (go (tap> "Calling Google")
               (<! (async/timeout 1200))
               "Google result")
        g2 (go (tap> "Calling Yahoo")
               (<! (async/timeout 1000))
               "Yahoo result")]
    (async/alt!!
      g1 ([x] (println "Got answer from Google:" x))
      g2 ([x] (println "Got answer from Yahoo:" x)))))

(defn demo6 []
  (let [g1 (go (tap> "Calling Google")
               (<! (async/timeout 1200))
               "Google result")
        g2 (go (tap> "Calling Yahoo")
               (<! (async/timeout 1000))
               "Yahoo result")]
    (<!! (async/into [] (async/merge [g1 g2])))))

(defn demo7 []
  (let [fut (future (Thread/sleep 1000)
                    (println "Woke up")
                    :done)]
    (deref fut)))

(defn demo8 []
  (let [t (async/thread
            (<!! (async/timeout 1000))
            (println "Woke up")
            :done)]
    (<!! t)))

(defn demo9 []
  (let [c1 (chan 1)
        g1 (async/go-loop [counter 0]
             (if (<! c1)
               (recur (inc counter))
               counter))]
    (>!! c1 :foo)
    (>!! c1 :foo)
    (>!! c1 :foo)
    (async/close! c1)
    (<!! g1)))

(defn demo10 []
  (let [output (chan 10)
        input (chan 1)
        control (async/go-loop [state []]
                  (let [val (<! input)]
                    (if val
                      (do (>! output val)
                          (recur (conj state val)))
                      (do (async/close! output)
                          state))))]
    (>!! input 1)
    (>!! input 2)
    (>!! input 3)
    (async/close! input)
    (println (<!! (async/into [] output)))
    (<!! control)))