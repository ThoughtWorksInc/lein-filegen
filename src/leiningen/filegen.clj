(ns leiningen.filegen
  (:require [clojure.java.io :as io])
  (:import [java.io File FilenameFilter]))

(defn mkdirs
  [path]
  (.mkdirs (io/file path)))

(defn parent
  [path]
  (.getParentFile (io/file path)))

(defn filegen [project & args]
  "Generate files from instructions in project.clj"
  (doseq [{requires :requires
           data :data
           target :target
           template-fn-str :template-fn} (:filegen project)]

    (mkdirs (parent target))
    (if requires (require (eval requires)))
    (let [template-fn (eval template-fn-str)]
      (spit target (template-fn data)))))
