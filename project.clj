(defproject prime-table "0.1.0-SNAPSHOT"
  :description "A command line app which will produce a multiplication table
of the first n prime numbers."
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot prime-table.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
