
(ns tiye.component.about
  (:require [respo.alias :refer [create-comp div span a img]]
            [tiye.style.layout :as layout]
            [tiye.style.devtool :as devtool]
            [tiye.style.typeset :as typeset]
            [tiye.component.text :refer [comp-text]]
            [tiye.style.widget :as widget]
            [tiye.component.visiting-card :refer [comp-visiting-card]]
            [tiye.information :as info]))

(def about-tiye (str "题叶"))

(defn render []
  (fn [state mutate!]
    (div
      {:style (merge layout/article {:width "40%"})}
      (comp-visiting-card)
      (div
        {}
        (->>
          info/data
          (map-indexed
            (fn [index category] [index
                                  (div
                                    {}
                                    (div
                                      {:style typeset/heading}
                                      (comp-text
                                        (:category category)
                                        nil))
                                    (div
                                      {:style
                                       (merge
                                         layout/row
                                         {:flex-wrap "wrap"})}
                                      (->>
                                        (:data category)
                                        (map-indexed
                                          (fn 
                                            [index address]
                                            [index
                                             (div
                                               {:style
                                                (merge
                                                  layout/row
                                                  widget/showcase)}
                                               (div
                                                 {:style
                                                  (widget/logo-small
                                                    (get address 2))})
                                               (a
                                                 {:style
                                                  widget/resource,
                                                  :attrs
                                                  {:target "_blanck",
                                                   :href
                                                   (get address 1)}}
                                                 (comp-text
                                                   (first address)
                                                   nil)))])))))])))))))

(def comp-about (create-comp :about render))
