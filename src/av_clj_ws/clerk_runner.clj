(ns av-clj-ws.clerk-runner
  (:require [nextjournal.clerk :as clerk]))

(defn start-clerk []
  (clerk/serve! {:port 7777 :watch-paths ["notebooks" "src"] :browse false}))

