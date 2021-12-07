(ns treydavis.advent-of-code-2021.day1
  (:require [clojure.java.io :as io]))

(def sample [199 200 208 210 200 207 240 269 260 263])
(def data (map #(Long/parseLong %) (line-seq (io/reader (io/resource "day-1-input")))))

(defn number-increases [s]
  (reduce (fn [num-increased [curr next]]
            (if (> next curr) (inc num-increased) num-increased))
          0 (partition 2 1 s)))

(= (number-increases sample) 7)
(def solution1 (number-increases data))
(def solution2 (number-increases (map #(apply + %) (partition 3 1 problem1))))
