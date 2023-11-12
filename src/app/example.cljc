(ns app.example
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]))

(e/defn Page
  []
  (e/server
   (let [!state (atom 0)
         state (e/watch !state)]
     (e/client
      (ui/button
       (e/fn [] ;; will get executed twice, since it depends on state
         (println "client CLICK " state)
         (e/server
          (println "server CLICK " state)
          (swap! !state inc) ;; if we did't update state, e/fn would get called only once
          ))
       (dom/text "button"))))))
