(ns workshop.macros
  (:require [clojure.pprint :refer [pprint]])
  (:require [clojure.repl :refer [pst]])
  )

;; And have it become
;(if condition
;  (do (foo)
;      (bar)))

;; Write a macro:
(defmacro when2 [test & body]
  (list 'if test (cons 'do body)))

; `(let [x# 100] (println x#))
(macroexpand-1 '(or false false true))
(macroexpand-1 (when2 (= 1 1) true))

; ` ~ ~@ x#

(defmacro just-do-it [& body]
  `(do ~@body)
  )

(defmacro execute [expr]
  `(do
    (pprint '~expr)
    (try
      (pprint ~expr)
      (catch Throwable e# (pst (.getMessage e#)))
      )
    )
  )

(defmacro transcript [& body]
  `(do
     (execute ~(first body))
     (transcript ~@(rest body))
     )
  )


;(println (macroexpand-1 '(execute (even? 1))))
;(execute (even? 1))
(println (transcript (even? 1) (odd? 2)))