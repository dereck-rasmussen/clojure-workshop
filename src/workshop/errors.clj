(ns workshop.errors)

(defn foo-error
  [x]
  (+ 1 x))

; use pst (print stack trace) to read full stack trace
;(println (foo-error "hello"))
(println (foo-error nil))