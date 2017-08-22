
(ns app.comp.container
  (:require-macros [respo.macros :refer [defcomp <> div button span]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [app.comp.profile :refer [comp-profile]]
            [app.schema :refer [information]]
            [app.comp.search :refer [comp-search]]
            [app.comp.tags :refer [comp-tags]]))

(def style-container
  {:font-family "Avenir, Roboto, Arial, Serif",
   :font-weight 300,
   :background-color (hsl 0 0 0 0.24),
   :justify-content :flex-start,
   :padding "120px 0",
   :overflow :auto,
   :min-height "100%"})

(defcomp
 comp-container
 (store mock-ssr?)
 (div
  {:style (merge ui/global ui/fullscreen ui/column-center style-container),
   :class-name "app-container"}
  (comp-profile)
  (=< nil 40)
  (comp-search (:buffer store) (:query store) mock-ssr?)
  (=< nil 40)
  (comp-tags)))