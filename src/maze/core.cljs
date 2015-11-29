(ns maze.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload


(defn setCell [maze row col color]
  (assoc maze row
    (let [newRow (nth maze row)]
      (assoc newRow col color))))



(defn hline [maze start end row color]
  (loop [acc maze
         col start]
    (if (> col end) acc
        (recur (setCell acc row col color) (+ col 1)))))

(defn vline [maze start end col color]
  (loop [acc maze
         row start]
    (if (> row end) acc
        (recur (setCell acc row col color) (+ row 1))

(defn genrow [width]
  (vec (replicate width "blue")))

(defn genboard [width height]
  (let [a (vec (replicate height (genrow width)))]
    (loop [acc a pos 0]
      (if (> pos 39) acc
          (recur (-> acc (hline 0 39 pos "red") (vline 0 39 pos "red")) (+ 3 pos))))))

(def app-state (atom {:maze (genboard 40 40)}))

(defn drawCell [row col color]
  [:rect {:x col :y row :width 10 :height 10 :style {:fill color}}])

(defn drawRow [row rownum]
  (loop [acc []
         row row
         ronum rownum
         col 0]

    (if (empty? row) acc
        (recur (conj acc (drawCell rownum col (first row))) (rest row) rownum (+ col 10)))))

(defn drawBoard [board]
  (loop [acc []
          board board
          rownum 0]
        (if (empty? board) acc
            (recur (concat acc (drawRow (first board) rownum)) (rest board) (+ rownum 10)))))

(defn genMaze
  (let [board (:maze @app-state)]
    ()))


(defn hello-world []
  (into [] (concat [:svg {:x 10 :y 10 :width 400 :height 400}] (drawBoard (:maze @app-state)))))


(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))


(defn on-js-reload [])
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
