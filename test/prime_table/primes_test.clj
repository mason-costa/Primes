(ns prime-table.primes-test
  (:require [clojure.test :refer :all]
            [prime-table.primes :as primes]))

(def not-zero? #'primes/not-zero?)
(def sieve-not-zero #'primes/sieve-not-zero)
(def sieve-of-eratosthenes #'primes/sieve-of-eratosthenes)

(deftest not-zero?-test
  (testing "Given zero, should return false"
    (is (not (not-zero? 0))))

  (testing "Given a positive integer, should return true."
    (is (not-zero? 12)))

  (testing "Given a negative decimal, should return true."
    (is (not-zero? 12.2))))

(deftest sieve-test
  (testing "Given collection [1 3 5 7] and 2, should return [1 3 5 7]"
    (let [expected [1 3 5 7]
          actual (sieve-not-zero [1 3 5 7] 2)]
      (is (= expected actual))))

  (testing "Given collection [2 4 6] and 2, should return empty"
    (let [sieved (sieve-not-zero [2 4 6] 2)]
      (is (empty? sieved)))))

(deftest sieve-of-eratosthenes-test
  (testing "Given upper bound 6 should return [2 3 5]"
    (let [expected [2 3 5] 
          actual (sieve-of-eratosthenes 6)]
      (is (= expected actual))))

  (testing "Given a negative number, should return AssertioError"
    (is (thrown? java.lang.AssertionError
                 (sieve-of-eratosthenes -5))))

  (testing "Given 2, 6, and [], should return [2 3 5]"
    (let [expected [2 3 5]
          actual (sieve-of-eratosthenes 2 6 [])]
      (is (= expected actual))))

  (testing "Given 5, 2, and [], should throw AssertionError"
    (is (thrown? java.lang.AssertionError
                 (sieve-of-eratosthenes 5 2 [])))))

(deftest get-primes-test
  (testing "Given 10, should return [2 3 5 7 11 13 17 19 23 29]"
    (let [expected [2 3 5 7 11 13 17 19 23 29]
          actual (primes/get-primes 10)]
      (is (= expected actual))))

  ;; This test ensures the logic of getting the second batch of primes
  ;; works correctly.
  (testing "Given 31, should retuen the first 31 primes."
    (is (= (count (primes/get-primes 31))))))
