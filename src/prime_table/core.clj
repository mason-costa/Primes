(ns prime-table.core
  (:require [prime-table.table :as table])
  (:gen-class))

(defn -main
  "The entry point into the application. Generates a table of the requested
  size."
  [n]
  (doall (map println
              (table/format-table (table/generate-table
                                   (read-string n))))))
