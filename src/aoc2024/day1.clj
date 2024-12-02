(ns aoc2024.day1
  (:require [clojure.string :as cstr]
            [clojure.java.io :as io]))

(defn sort-files
  [lines]
  (let [list-one (map first lines)
        list-two (map second lines)]
    (map vector (sort list-one) (sort list-two))))

(defn- differences
  [[input1 input2]]
  (abs (- input1 input2)))

(defn- split-input
  [file]
  (for [line (line-seq file)]
    (map read-string (cstr/split line #"\s+"))))

(defn main-part-one
  [path]
  (with-open [file (io/reader path)]
    (->> (split-input file)
         sort-files
         (map differences)
         (reduce +))))

(defn count-freq
  [coll item]
  (update coll item #(inc (or %1 0))))

(defn sim-score
  [freq-map number]
  (* number (get freq-map number 0)))

(defn main-part-two
  [path]
 (with-open [file (io/reader path)]
    (let [input-numbers (split-input file)
          freq-map (reduce count-freq {} (map second input-numbers))]
      (->> (map first input-numbers)
           (map (partial sim-score freq-map))
           (reduce +)))))
