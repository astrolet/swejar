(ns ephemeris.time
  (:require [clojure.instant :refer (read-instant-timestamp)]
            [clj-time.coerce :as c]
            [clj-time.core :as t]))

;; https://clojure.github.io/clojure/clojure.instant-api.html
;; https://clj-time.github.io/clj-time/doc/clj-time.coerce.html
;; https://clj-time.github.io/clj-time/doc/clj-time.core.html
;; http://th-mack.de/download/swisseph-doc/swisseph/SweDate.html

;; parameters vector for getJDfromUTC
(defn- prepare [dt]
  [(t/year dt) (t/month dt) (t/day dt)
   (t/hour dt) (t/minute dt)
   (Double/parseDouble ;; ridiculous precision
     (str (t/second dt) "." (t/milli dt)))
   true   ;; Gregorian Calendar
   true]) ;; checkValidInput

(defn ut [utc]
  (prepare (c/from-sql-time (read-instant-timestamp utc))))
