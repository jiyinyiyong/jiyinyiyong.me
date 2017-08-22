
(ns app.comp.tags
  (:require-macros [respo.macros :refer [defcomp <> div button span]])
  (:require [clojure.string :as string]
            [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [app.schema :refer [tags]]))

(def style-tag
  {:color :white,
   :display :inline-block,
   :border (str "1px solid " (hsl 0 0 100)),
   :border-radius "16px",
   :padding "0 8px",
   :font-size 16,
   :height 32,
   :line-height "32px",
   :margin 8,
   :cursor :pointer,
   :user-select :none})

(defn on-select [aspect] (fn [e d! m!] (d! :query (name aspect))))

(def style-tags {:padding "0 24px"})

(defcomp
 comp-tags
 ()
 (div
  {:style style-tags}
  (->> tags
       (map
        (fn [aspect]
          [aspect
           (div
            {:style style-tag,
             :inner-text (let [tag (name aspect)]
               (str (string/upper-case (first tag)) (subs tag 1))),
             :on {:click (on-select aspect)}})])))))