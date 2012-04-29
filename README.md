# logan

logan: syslog log analysis

## Usage

logan: logan x.log

plots syslog event activity in a time series. For example:

  => syslog -T utc >x.log
  => sh run.sh logan x.log

to run with leiningen
  => lein run test/data/sample.log

to build a standalone .jar for endusers and run that:
  => lein uberjar
  => sh run.sh test/data/sample.log

to run from the repl
  => lein repl
  => user> (use '(incanter core charts))
  => user> (use '(logan.core))
  => user> (view (histogram (ordinals (syslog-dates "test/data/sample.log"))))

## License

Copyright (C) 2012 Alan Wostenberg:

Distributed under the Eclipse Public License, the same as Clojure.
