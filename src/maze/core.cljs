(ns maze.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defn genboard []
  (into-array (repeat 40 (into-array (repeat 40 "blue")))))

(def app-state (atom {:maze (genboard)}))

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


(defn hello-world []
  (into [] (concat [:svg {:x 10 :y 10 :width 400 :height 400}] (drawBoard (:maze @app-state)))))


(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))


(defn on-js-reload [])
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
