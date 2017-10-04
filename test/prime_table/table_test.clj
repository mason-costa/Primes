(ns prime-table.table-test
  (:require [clojure.test :refer :all]
            [prime-table.table :as table]))

(def ^:private row [2 3 5 7 11 13 17 19 23 29])

(deftest get-table-headers-test
  (testing "Given 10, should return the first 10 primes."
    (is (= (count (table/get-table-headers 10)) 10)))

  (testing "Given -5, should return an empty seq"
    (is (empty? (table/get-table-headers -5)))))

(deftest create-row-test
  (testing "given row and 2, should return all elements multiplied by 2"
    (let [expected [2 4 6 10 14 22 26 34 38 46 58]
          actual (table/create-row row 2)]
      (is (= expected actual)))))

(deftest format-row-test
  (testing "Given 3 and row, should return all cells of length 3"
    (let [formatted (table/format-row 3 row)]
      (is (= (count formatted) 10))
      (is (every? #(= (count %) 3) formatted)))))

(deftest generate-table-test
  (testing "Given 5, Should return a 6 x 6 table."
    (let [tab (table/generate-table 5)]
      (is (= 6 (count tab)))
      (is (= 6 (count (first tab)))))))
