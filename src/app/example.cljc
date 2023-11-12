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
       (e/fn []
         (let [state (e/snapshot state)]
           (println "client CLICK " state)
           (e/server
            (println "server CLICK " state)
            (swap! !state inc))))
       (dom/text "button"))))))
