(ns sk.handlers.examen.sql
  (:require [sk.models.crud :refer [Query db]]))

(def preguntas-sql
  "SELECT
  id,
  pregunta
  FROM preguntas
  ORDER BY id")

(def opciones-sql
  "SELECT
  opcion
  FROM opciones o
  WHERE preguntas_id = ?")
