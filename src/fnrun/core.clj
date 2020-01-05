(ns fnrun.core
  (:import [io.github.tessellator.fnrun
            EventHandler
            Result
            Runner]))

(defn- fnrun-handler [handler]
  (reify EventHandler
    (handle [this event context]
      (let [{:keys [status body] :as m} (handler event context)]
        (Result. (or status 200)
                 (or body ""))))))

(defn run [handler]
  (Runner/run (fnrun-handler handler)))
