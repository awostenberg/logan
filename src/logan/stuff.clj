(ns logan.stuff
  (:use [logan.core]
        [clojure.tools.cli :only (cli)]
        [incanter core charts])
  (:gen-class))

(defn plot-it [syslog-output-filename]
  (histogram (ordinals (syslog-dates syslog-output-filename)) :x-label (str "day in " syslog-output-filename)))

(defn oldmain [& args]
  (if (empty? args)
    (println "Usage: logan x.log
\twhere x.log is output from osx 'syslog -u' command")
    (view (plot-it (first args)))))

(defn all-lines
  "lazy read STDIN (courtesy Gareth Jones)"
  []
  (lazy-cat
   (when-let [line (read-line)]
     (cons line (all-lines)))))

(defn -main [& args]
   (let [[options args banner]
        (cli args
             ["-h" "--help" "logan x.log (x.log is output of 'syslog -u')"
              :default false :flag true])]

     (when (or (:help options) (empty? args))
      (println banner)
      (System/exit 0))

    (view (plot-it (first args)))))

    




