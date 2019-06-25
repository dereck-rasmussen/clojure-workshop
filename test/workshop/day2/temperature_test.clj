(ns workshop.day2.temperature-test
  (:require [clojure.test :refer :all])
  (:require [workshop.day2.temperature :as temp])
  (:require [clojure.spec.alpha :as s])
  (:require [clojure.spec.gen.alpha :as gen])
  (:require [clojure.spec.test.alpha :as stest])
  )

(deftest test-freezing
  (is (= 32 (temp/celsius->fahrenheit 0)))
  (is (= 0 (temp/fahrenheit->celsius 32)))
  )

(deftest test-equivalent
  (is (= -40 (temp/celsius->fahrenheit -40)))
  (is (= -40 (temp/fahrenheit->celsius -40)))
  )

(deftest test-boiling
  (is (= 212 (temp/celsius->fahrenheit 100)))
  (is (= 100 (temp/fahrenheit->celsius 212)))
  )

; ===================================
; Generative testing
;(number? 32) => true
;(s/gen number?) => returns a generator
;(gen/generate (s/gen number?)) # => -133025784
;(gen/sample (s/gen (number? 32)))
; => ???
(gen/sample (s/gen (s/coll-of number?)))
; => vector of numbers
;([-0.5]
;  [-0.75 -1 -2.0 -1 -1.5 -1 2.0 1.0 -1 3.0 -1 -1 0.75 -1]
;  [-1 -0.0]
; ...

; args: is argument to function
; ret: is return value
; fn: is the relation between them

;(s/fdef temp/celsius->fahrenheit
;        :args (s/cat :degrees number?)
;        :ret number?)

; helper function
(defn- approx= [x y]
  (< (Math/abs (- (double x) (double y))) 0.001)
  )

; with the fn
; state that the temperature functions are inverses of each other
; the return value of cel->fahr
; if passed to fahr->cel
; should equal the input of cel->fahr

; alternate s/cat
; (s/int-in -1000000 100000))
(s/fdef temp/celsius->fahrenheit
        :args (s/cat :degrees (s/and number?
                                     (fn [x] (< (Math/abs (double x)) 1000000.0))
                                     (fn [x] (Double/isFinite x))
                                     ))
        :ret number?
        :fn (fn [example]
              (approx=
                (temp/fahrenheit->celsius (:ret example))
                (:celsius (:args example)))
              )
        )

; instrument: actually verify input args (only for development)
; based on specs above ^
(stest/instrument `temp/celsius->fahrenheit)
;(`temp/celsius->fahrenheit "not")
;=> nil
;(temp/celsius->fahrenheit "not")
;Execution error - invalid arguments to workshop.day2.temperature/celsius->fahrenheit at (form-init5270421041480928279.clj:1).
;"not" - failed: number? at: [:degrees]

(s/exercise-fn `temp/celsius->fahrenheit)
;=>
;([(-2.0) 28.4]
;  [(2.0) 35.6]
;  [(-1.5) 29.3]
;  [(2.0) 35.6]
;  [(-3) 133/5]
;  [(-2.0) 28.4]
;  [(1) 169/5]
;  [(-9) 79/5]
;  [(-0.3984375) 31.2828125]
;  [(6.0) 42.8])

; Run generative tests based on the spec to find errors from input
(stest/check `temp/celsius->fahrenheit)
; => error: integer overflow
; Details of the generated number that failed
;:seed 1561482681073,
;:failing-size 72,
;:num-tests 73,
;:fail [(-2438335606432655446)],
;:shrunk {...
;         :smallest [(-1024819115206086201)]}}
;}

(run-tests)