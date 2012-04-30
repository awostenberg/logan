(ns logan.core
  (:use [clj-time.core :only (date-time in-days in-hours interval)]
        [clj-time.format]
        [clojure.string :only (split)]))

(defn hello [] 42)

(def utc-formatter (formatter "yyyy.MM.dd"))

(defn syslog-parse-line   "parse a map from the syslog entry with date in utc form as in from   'syslog -u'  "
  [string] (let [tokens (split string #" ")]
    {:date (parse utc-formatter (first tokens))}))

;;(syslog-parse "2012.04.28 15:27:37 UTC alan-wostenbergs-imac syslog[58545] <Notice>: hello")

(defn syslog-dates  "answer the collection of dates of the syslog entries in the file specified by syslog-filename"
   [syslog-filename] (with-open [rdr (clojure.java.io/reader syslog-filename)]
    (doall (map :date (map syslog-parse-line (line-seq rdr))))))

(defn ordinals     "answer offset in-days from first date for each date in dates"
  ([dates]     (ordinals dates in-days))
  ([dates in-fn]
     (map #(in-fn (interval (first dates) %)) dates)))
