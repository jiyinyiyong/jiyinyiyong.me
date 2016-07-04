
(ns tiye.component.chatroom
  (:require [respo.alias :refer [create-comp div input button]]
            [tiye.component.text :refer [comp-text]]
            [tiye.component.message :refer [comp-message]]
            [tiye.component.settings :refer [comp-settings]]
            [tiye.style.layout :as layout]
            [tiye.style.widget :as widget]
            [tiye.style.typeset :as typeset]
            [tiye.style.devtool :as devtool]
            [respo.component.debug :refer [comp-debug]]
            [respo.component.space :refer [comp-space]]))

(defn init-state [] "")

(defn update-state [state text] text)

(defn on-input [e dispatch! mutate!]
  (let [content (:value e)]
    (mutate! content)
    (dispatch! :state/buffer content)))

(defn on-keydown [e dispatch! mutate!]
  (if (= 13 (:key-code e))
    (do (mutate! "") (dispatch! :message/confirm nil))))

(defn on-clear [e dispatch! mutate!] (dispatch! :message/clear nil))

(defn on-settings [e dispatch! mutate!] (dispatch! :state/settings nil))

(defn render [store]
  (fn [state mutate!]
    (div
      {:style (merge layout/vertical layout/flex {:padding "40px"})}
      (div
        {:style typeset/description}
        (comp-text
          (str
            "Currently there are "
            (get-in store [:statistics :user-count])
            " users in the chatroom.")
          nil))
      (div
        {:style
         (merge layout/flex layout/scroll {:padding-bottom "200px"})}
        (->>
          (merge (:messages store) (:buffers store))
          (map last)
          (sort-by :time)
          (map (fn [message] [(:id message) (comp-message message)]))))
      (if (get-in store [:state :show-settings?])
        (comp-settings (:state store))
        (div {}))
      (div
        {:style (merge layout/horizontal)}
        (input
          {:style (merge widget/textbox layout/flex),
           :event {:keydown on-keydown, :input on-input},
           :attrs {:placeholder "Message...", :value state}})
        (button
          {:style widget/button,
           :event {:click on-settings},
           :attrs {:inner-text "Settings"}})
        (comp-space 8 nil)
        (button
          {:style widget/button,
           :event {:click on-clear},
           :attrs {:inner-text "Clear"}})))))

(def comp-chatroom
 (create-comp :chatroom init-state update-state render))
