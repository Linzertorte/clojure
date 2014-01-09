(ns hello-seesaw.core
  (:use [seesaw.core])
  (:require [seesaw.selector :as selector]))

(defn identify
  [root]
  (doseq [w (select root [:*])]
    (if-let [n (.getName w)]
      (selector/id-of! w (keyword n))))
  root)

(def states ["CA", "MA","GA", "MI"])

(def defaults
  {:first-name "Qingfei"
   :last-name "MA"
   :street "36 S Huntington Ave #7."
   :city "Boston"
   :zip "12345"
   :state "MA"})

(defn my-form
  []
  (let [form (identify (hello_seesaw.MyForm.  ))]
    (config! (select form [:#state]) :model states)
    form))

(defn -main [& args]
  (invoke-later
   (let [form (value! (my-form) defaults)
         result (-> (dialog :content form :option-type :ok-cancel)
                    pack! show!)]
     (if (= :success result)
       (println "User entered: " (value form))
       (println "User canceled")))))
