(ns prime-table.primes
  "Functionality related to prime numbers.")

(def ^:private not-zero?
  "A utility function which checks that the value is not zero"
  (comp not zero?))

(defn- sieve-not-zero
  "Filters all numbers from the given sequence which evenly divide by div."
  [coll div]
  (filter #(not-zero? (mod % div)) coll))

(defn- sieve-of-eratosthenes
  "An implementation of the sieve of eratosthenes. This version has the optimization that it only checks numbers in the sequence up to the square root of the upper bound."
  ([upper]
   (sieve-of-eratosthenes 2 upper []))

  ([lower upper coll]
   {:pre [(pos? upper)
          (> upper lower)]}
   (loop [primes []
          nums (concat coll
                       (range lower upper))]

     (let [n (first nums)]
       (if (> (* n n) upper)
         (concat primes nums)
         (recur (conj primes n)
                (sieve-not-zero nums n)))))))

(defn get-primes
  "Fetches the requested number of primes. For efficiency reasons, the initial set of primes are capped to those under 121. If more numbers are required, the primes prior to 121 will be combined with the range of numbers increasing by 100."
  [n]
  (loop [low 2
         high 121
         primes []]

    (let [primes (sieve-of-eratosthenes low high primes)]
      (if (> (count primes) n)
        (take n primes)
        (recur (inc high)
               (+ high 100)
               primes)))))
