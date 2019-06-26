(ns workshop.test)

(defn is [actual expected]
  (if (= actual expected)
    (println "PASS: " actual " == " expected )
    (println "FAIL: " actual " == " expected)))