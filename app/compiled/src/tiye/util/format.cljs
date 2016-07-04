
(ns tiye.util.format)

(defn size-2 [x] (if (< x 10) (str 0 x) (str x)))

(defn readable-time [unix-time]
  (let [the-time (js/Date. unix-time)]
    (str
      (size-2 (inc (.getMonth the-time)))
      "-"
      (size-2 (.getDate the-time))
      " "
      (size-2 (.getHours the-time))
      ":"
      (size-2 (.getMinutes the-time)))))