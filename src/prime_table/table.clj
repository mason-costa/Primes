(ns prime-table.table
  (:require [prime-table.primes :as primes])
  (:gen-class))

(defn get-table-headers
  "Generates a seq of headers for a prime multiplication table."
  [n]
  (primes/get-primes n))

(defn create-row
  "Given a list of prime numbers and a number, returns a seq of
  the multiplication of the seq by the number. Prepends the number for
  presentation purposes."
  [coll n]
  (cons n
        (map * (repeat n) coll)))

(defn format-row
  "Formats all cells in the row for optimal layout. Takes as an argument
  the padding number to use."
  [pad row]
  (let [padding (str "%" pad "d")]
    (map (partial format padding) row)))

(defn generate-table
  "Creates a table n rows by n columns. The inner cells are the product of the column header by the row header"
  [n]
  (let [headers (get-table-headers n)
        primes (map (partial create-row
                             headers)
                    headers)
        pad (count (str (last (last primes))))
        header (cons (format (str "%" pad "s") "")
                     (format-row pad headers))]

    (cons header
          (map (partial format-row pad)  primes))))

(defn format-table
  "Formats the given table for printing to STDOUT."
  [table]
  (map (partial clojure.string/join " ") table))
