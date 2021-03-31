(ns sk.models.cdb
  (:require [sk.models.crud :refer [db
                                    Query!
                                    Insert-multi]]
            [noir.util.crypt :as crypt]))


;; Start users table
(def users-sql
  "CREATE TABLE users (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  lastname varchar(45) DEFAULT NULL,
  firstname varchar(45) DEFAULT NULL,
  username varchar(45) DEFAULT NULL,
  password TEXT DEFAULT NULL,
  dob date DEFAULT NULL,
  cell varchar(45) DEFAULT NULL,
  phone varchar(45) DEFAULT NULL,fax varchar(45) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  level char(1) DEFAULT NULL COMMENT 'A=Administrator,U=User,S=System',
  active char(1) DEFAULT NULL COMMENT 'T=Active,F=Not active',
  imagen varchar(200) DEFAULT NULL
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8")

(def users-rows
  [{:lastname  "User"
    :firstname "Regular"
    :username  "user@gmail.com"
    :password  (crypt/encrypt "user")
    :dob       "1957-02-07"
    :email     "user@gmail.com"
    :level     "U"
    :active    "T"}
   {:lastname "User"
    :firstname "Admin"
    :username "admin@gmail.com"
    :password (crypt/encrypt "admin")
    :dob "1957-02-07"
    :email "admin@gmail.com"
    :level "S"
    :active "T"}])
;; End users table

;; Start escuela table
(def escuela-sql
  "CREATE TABLE escuela (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ct varchar(100) DEFAULT NULL,
  nombre varchar(200) DEFAULT NULL,
  tipo char(1) NOT NULL COMMENT 'P=primaria,S=secundaria',
  UNIQUE INDEX ct (ct)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8")

(def escuela-rows
  [{:id 1
    :ct "ct1"
    :nombre "Primaria"
    :tipo "P"}
   {:id 2
    :ct "ct2"
    :nombre "Secundaria"
    :tipo "S"}])
;; End escuela table

;; Start grado table
(def grado-sql
  "CREATE TABLE grado (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  grado int(10) NOT NULL
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8")

(def grado-rows
  [{:id 1 :grado 1}
   {:id 2 :grado 2}
   {:id 3 :grado 3}
   {:id 4 :grado 4}
   {:id 5 :grado 5}
   {:id 6 :grado 6}])
;; End grado table

;; Start asignatura
(def asignatura-sql
  "CREATE TABLE asignatura (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre varchar(200) DEFAULT NULL
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8")

(def asignatura-rows
  [{:id 1 :nombre "Matematicas"}
   {:id 2 :nombre "Ingles"}
   {:id 3 :nombre "Historia"}
   {:id 4 :nombre "Geografia"}])
;; End asignatura

;; Start preguntas
(def preguntas-sql
  "CREATE TABLE preguntas (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  pregunta varchar(800) DEFAULT NULL,
  escuela_id int(11) NOT NULL,
  asignatura_id int(11) NOT NULL,
  grado_id int(11) NOT NULL,
  CONSTRAINT fk_escuela FOREIGN KEY (escuela_id) REFERENCES escuela(id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_asignatura FOREIGN KEY (asignatura_id) REFERENCES asignatura(id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_grado FOREIGN KEY (grado_id) REFERENCES grado(id) ON UPDATE CASCADE ON DELETE CASCADE
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8")

(def preguntas-rows
  [{:id 1
    :pregunta "2 + 2 = ?"
    :escuela_id 1
    :asignatura_id 1
    :grado_id 2}
   {:id 2
    :pregunta "3 + 3 = ?"
    :escuela_id 1
    :asignatura_id 1
    :grado_id 2}
   {:id 3
    :pregunta "8 + 8 = ?"
    :escuela_id 1
    :asignatura_id 1
    :grado_id 2}
   {:id 4
    :pregunta "10 + 4 = ?"
    :escuela_id 1
    :asignatura_id 1
    :grado_id 2}
   {:id 5
    :pregunta "20 + 2 = ?"
    :escuela_id 1
    :asignatura_id 1
    :grado_id 2}])
;; End preguntas

;; Start opciones
(def opciones-sql
  "CREATE TABLE opciones (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  opcion varchar(800) DEFAULT NULL,
  preguntas_id int(11),
  CONSTRAINT fk_preguntas FOREIGN KEY (preguntas_id) REFERENCES preguntas(id) ON UPDATE CASCADE ON DELETE CASCADE
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8")

(def opciones-rows
  [
   ;; start pregunta 1
   {:id 1
    :opcion "22"
    :preguntas_id 1}
   {:id 2
    :opcion "2"
    :preguntas_id 1}
   {:id 3
    :opcion "21"
    :preguntas_id 1}
   {:id 4
    :opcion "4"
    :preguntas_id 1}
   ;; end pregunta 1

   ;; start pregunta 2
   {:id 5
    :opcion "33"
    :preguntas_id 2}
   {:id 6
    :opcion "3"
    :preguntas_id 2}
   {:id 7
    :opcion "31"
    :preguntas_id 2}
   {:id 8
    :opcion "6"
    :preguntas_id 2}
   ;; end pregunta 2

   ;; start pregunta 3
   {:id 9
    :opcion "88"
    :preguntas_id 3}
   {:id 10
    :opcion "8"
    :preguntas_id 3}
   {:id 11
    :opcion "81"
    :preguntas_id 3}
   {:id 12
    :opcion "16"
    :preguntas_id 3}
   ;; end pregunta 2

   ;; start pregunta 4
   {:id 13
    :opcion "104"
    :preguntas_id 4}
   {:id 14
    :opcion "10"
    :preguntas_id 4}
   {:id 15
    :opcion "101"
    :preguntas_id 4}
   {:id 16
    :opcion "14"
    :preguntas_id 4}
   ;; end pregunta 4

   ;; start pregunta 5
   {:id 17
    :opcion "202"
    :preguntas_id 5}
   {:id 18
    :opcion "20"
    :preguntas_id 5}
   {:id 19
    :opcion "201"
    :preguntas_id 5}
   {:id 20
    :opcion "22"
    :preguntas_id 5}
   ;; end pregunta 4
   ])
;; End opciones

;; Start respuestas
(def respuestas-sql
  "CREATE TABLE respuestas (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  preguntas_id int(11) NOT NULL,
  opciones_id int(11) NOT NULL,
  CONSTRAINT fk_preguntas_id FOREIGN KEY (preguntas_id) REFERENCES preguntas(id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_opciones_id FOREIGN KEY (opciones_id) REFERENCES opciones(id) ON UPDATE CASCADE ON DELETE CASCADE
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8")

(def respuestas-rows
  [
   ;; Start respuesta 1
   {:preguntas_id 1
    :opciones_id 4}
   ;; End respuesta 1

   ;; Start respuesta 2
   {:preguntas_id 2
    :opciones_id 8}
   ;; End respuesta 2

   ;; Start respuesta 3
   {:preguntas_id 3
    :opciones_id 12}
   ;; End respuesta 3

   ;; Start respuesta 4
   {:preguntas_id 4
    :opciones_id 16}
   ;; End respuesta 4

   ;; Start respuesta 5
   {:preguntas_id 5
    :opciones_id 20}
   ;; End respuesta 4
   ])
;; End respuestas

(defn reset-database
  "Removes existing tables and re-creates them"
  []
  ;; Start DROP tables
  (Query! db "DROP table IF EXISTS respuestas")
  (Query! db "DROP table IF EXISTS opciones")
  (Query! db "DROP table IF EXISTS preguntas")
  (Query! db "DROP table IF EXISTS asignatura")
  (Query! db "DROP table IF EXISTS grado")
  (Query! db "DROP table IF EXISTS escuela")
  (Query! db "DROP table IF EXISTS users")
  ;; End DROP tables

  ;; Start users
  (Query! db users-sql)
  (Query! db "LOCK TABLES users WRITE;")
  (Insert-multi db :users users-rows)
  (Query! db "UNLOCK TABLES;")
  ;; End users

  ;; Start escuela
  (Query! db escuela-sql)
  (Query! db "LOCK TABLES escuela WRITE;")
  (Insert-multi db :escuela escuela-rows)
  (Query! db "UNLOCK TABLES;")
  ;; End escuela

  ;; Start grado
  (Query! db grado-sql)
  (Query! db "LOCK TABLES grado WRITE;")
  (Insert-multi db :grado grado-rows)
  (Query! db "UNLOCK TABLES;")
  ;; End grado

  ;; Start asignatura
  (Query! db asignatura-sql)
  (Query! db "LOCK TABLES asignatura WRITE;")
  (Insert-multi db :asignatura asignatura-rows)
  (Query! db "UNLOCK TABLES;")
  ;; End asignatura

  ;; Start preguntas
  (Query! db preguntas-sql)
  (Query! db "LOCK TABLES preguntas WRITE;")
  (Insert-multi db :preguntas preguntas-rows)
  (Query! db "UNLOCK TABLES;")
  ;; End preguntas

  ;; Start opciones
  (Query! db opciones-sql)
  (Query! db "LOCK TABLES opciones WRITE;")
  (Insert-multi db :opciones opciones-rows)
  (Query! db "UNLOCK TABLES;")
  ;; End opciones

  ;; Start respuestas
  (Query! db respuestas-sql)
  (Query! db "LOCK TABLES respuestas WRITE;")
  (Insert-multi db :respuestas respuestas-rows)
  (Query! db "UNLOCK TABLES;")
  ;; End respuestas
  )

(defn migrate []
  "Migrate by the seat of my pants")
