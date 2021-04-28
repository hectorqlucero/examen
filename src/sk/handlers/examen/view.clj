(ns sk.handlers.examen.view
  (:require [sk.handlers.examen.sql :refer [preguntas-sql
                                            opciones-sql]]
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [hiccup.page :refer [include-js]]
            [sk.models.crud :refer [Query db]]
            [sk.models.util :refer [build-field
                                    build-radio-buttons]]))

(def cnt (atom 0))

(defn build-opciones [label id rows]
  [:div.form-group.col-10
   [:label [:span {:style "font-size:2em;"} (str (swap! cnt inc) ".  " label)]]
   (map (fn [row]
          (let [the-options (str "label:'" (:opcion row) "'")]
            [:div {:style "margin-right:5px;"}
             [:input {:type "radio"
                      :id (str id "_" (:id row))
                      :name id
                      :class "easyui-radiobutton"
                      :data-options the-options}]])) rows)])

(defn build-pregunta [title row]
  (build-opciones (:pregunta row) (:id row) (Query db [opciones-sql (:id row)])))

(defn examen-view
  [title]
  (reset! cnt 0)
  (list
    [:div.container
     [:div.easyui-panel {:style "width:100%;
                                max-width:600px;
                                padding:30px 60px"
                         :title (str title " Matematicas - Pedro Pacas")
                         :data-options "style:{margin:'0 auto'}"}
      [:form.fm {:method "post"
                 :enctype "multipart/form-data"}
       (map build-pregunta title (Query db preguntas-sql))]]]))

(defn examen-scripts
  []
  nil)
