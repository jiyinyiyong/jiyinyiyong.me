
(ns tiye.component.title
  (:require [respo.alias :refer [span create-comp]]))

(defn render [text]
  (fn [state mutate!] (set! (.-title js/document) text) (span {})))

(def comp-title (create-comp :title render))