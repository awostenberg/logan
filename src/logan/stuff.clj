(ns logan.stuff
  (:use [logan.core]
        [incanter core charts])
  (:gen-class))

(defn plot-it [syslog-output-filename]
  (histogram (ordinals (syslog-dates syslog-output-filename)) :x-label (str "day in " syslog-output-filename)))

(defn -main [& args]
  (if (empty? args)
    (println "Usage: logan x.log
\twhere x.log is output from osx 'syslog -u' command")
    (view (plot-it (first args)))))

