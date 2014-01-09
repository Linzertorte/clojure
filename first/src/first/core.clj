(ns first.core)
(use 'clojure.string)
(defn sum [& n]
  (+ n))

(defn -main [& args]
  (loop [in (read-line)]
     (when in
       (println (reduce + 0 (map read-string (split in #"\s"))))
       (recur (read-line)))))


