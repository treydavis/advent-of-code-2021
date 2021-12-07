(ns treydavis.advent-of-code-2021.day2
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def sample ["forward 5"
             "down 5"
             "forward 8"
             "up 3"
             "down 8"
             "forward 2"])
(def problem1 (line-seq (io/reader (io/resource "day2-input"))))

(defn find-position [s]
  (->> s
       (map #(str/split % #"\s"))
       (map (fn [[direction-word magnitude]]
              [({"forward" [1 0] "up" [0 -1] "down" [0 1]} direction-word) (Long/parseLong magnitude)]))
       (map (fn [[point magnitude]]
              (map (partial * magnitude) point)))
       (apply map +)))

(defn get-movements [aim coll]
  (lazy-seq
   (when-let [s (seq coll)]
     (let [[direction magnitude] (first s)]
       (case direction
         "forward" (cons [magnitude (* aim magnitude)] (get-movements aim (rest s)))
         "up" (get-movements (- aim magnitude) (rest s))
         "down" (get-movements (+ aim magnitude) (rest s)))))))

(defn find-position2 [s]
  (->> s
       (map #(str/split % #"\s"))
       (map (fn [[direction magnitude]] [direction (Long/parseLong magnitude)]))
       (get-movements 0)
       (apply map +)))

(= (apply * (find-position sample)) (apply * [15 10]))
(def solution1 (apply * (find-position problem1)))
(def solution2 (apply * (find-position2 problem1)))
