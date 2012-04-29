(ns logan.test.core
  (:use [logan.core]
        [clojure.test]
        [clj-time.core :only (date-time in-days in-hours)]))

(deftest test-hello
  (is (= 42 (hello))))

(deftest test-syslog-date
  (is (= (date-time 2012 04 28) (:date (syslog-parse-line "2012.04.28 15:27:37 UTC alan-wostenbergs-imac syslog[58545] <Notice>: hello")))))

(deftest test-syslog-dates
  (is (= (date-time 2011 04 20) (second (syslog-dates "test/data/sample.log")))))

(defn april [n] (date-time 2012 04 n))

(deftest test-no-day-ordinals
  (is (empty? (ordinals []))))

 (deftest test-day-ordinals
  (is (= [0 1 2] (ordinals [ (april 3) (april 4) (april 5)]))))

(deftest test-hour-ordinals
  (is (= [0 24 48] (ordinals [ (april 3) (april 4) (april 5)] in-hours))))


