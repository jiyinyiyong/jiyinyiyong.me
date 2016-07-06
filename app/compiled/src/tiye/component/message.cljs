
(ns tiye.component.message
  (:require [hsl.core :refer [hsl]]
            [respo.alias :refer [create-comp div span]]
            [tiye.component.text :refer [comp-text]]
            [respo.component.debug :refer [comp-debug]]
            [respo.component.space :refer [comp-space]]
            [tiye.style.layout :as layout]
            [tiye.style.widget :as widget]
            [tiye.util.format :refer [readable-time]]))

(defn render [message]
  (fn [state mutate!]
    (div
      {:style (merge layout/row widget/message)}
      (comp-text (readable-time (:time message)) widget/time-tip)
      (div
        {:style widget/username}
        (comp-text (or (:nickname message) "Someone") nil))
      (comp-space 8 nil)
      (comp-text
        (:text message)
        (merge (if (:writing? message) {:color (hsl 0 0 70)})))
      (comp-space 16 nil)
      (comment comp-debug message {:opacity 0.1, :right 0}))))

(def comp-message (create-comp :message render))
