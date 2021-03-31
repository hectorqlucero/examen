(ns sk.handlers.examen.handler
  (:require [sk.models.crud :refer [Query db]]
            [sk.models.util :refer [get-session-id]]
            [sk.layout :refer [application]]
            [sk.handlers.examen.view :refer [examen-view examen-scripts]]))

(defn examen
  [_]
  (try
    (let [title "Examen"
          ok (get-session-id)
          js (examen-scripts)
          content (examen-view title)]
      (application title ok js content))
    (catch Exception e (.getMessage e))))
