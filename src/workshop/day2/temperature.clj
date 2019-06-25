(ns workshop.day2.temperature)

(defn fahrenheit->celsius
  "Converts degrees farenheit to degress celsius"
  [degrees]
  (/ (* (- degrees 32) 5) 9)
  )

(defn celsius->fahrenheit
  [degrees]
  (+ 32 (/ (* degrees 9) 5))
  )


