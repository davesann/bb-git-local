(ns dsann.git
  (:require [babashka.process :refer [shell]]
            [clojure.string :as s]
            [babashka.fs :as fs]
            [dsann.let-map :refer [let-map]]))


(defn git-repo? []
  (fs/exists? ".git"))

(defn check-git! []
  (when-not (git-repo?)
    (let [p (fs/canonicalize ".")]
      (throw (ex-info (str "git-local : '" p "'" " is not a git repository") {:path p})))))

(defn shell-git [command]
  (check-git!)
  (let [result (shell {:out :string} command)]
    (->> result :out)))

(defn remove-tag-prefix [tag]
  (s/replace-first tag (re-pattern "^refs/tags/") ""))

(defn parse-git-tags [s]
  (->> s
       s/split-lines
       (map    (fn [s] (s/split s (re-pattern "\\s+"))))
       (reduce (fn [r [hash tag]]
                 (let [tag (remove-tag-prefix tag)]
                    (assoc r tag {:commit-sha hash
                                  :commit-sha-abbrev (subs hash 0 7)})))
               {})))

(defn git-tags []
  (-> "git show-ref --tags"
      shell-git
      parse-git-tags))

;; git tags cannot have spaces
(defn parse-git-messages [s]
  (->> s
      s/split-lines
      (map (fn [s] (s/split s (re-pattern "\\s+") 2)))
      (reduce
        (fn [r [tag message]]
          (assoc r tag {:tag-message message}))
        {})))

(defn git-tag-messages []
  (-> "git tag -n"
      shell-git
      parse-git-messages))

(defn git-latest-tag []
  (-> "git describe --abbrev=0"
      shell-git
      s/trim))

(defn git-tag-data []
  (merge-with merge (git-tags) (git-tag-messages)))

(defn git-remote-origin []
  (->> "git config --get remote.origin.url"
      shell-git
      s/trim))

(defn git-remote-name []
  (->> (git-remote-origin)
      (re-matches (re-pattern ".*github[.]com:(.*)[.]git$"))
      second))


(defn git-data []
  (let-map
    git-remote-name (git-remote-name)
    git-coord-name  (str "io.github." git-remote-name)
    git-tag-data    (git-tag-data)

    latest-git-tag       (git-latest-tag)
    latest-git-sha       (-> git-tag-data (get latest-git-tag) :commit-sha-abbrev)
    latest-git-coord     {(symbol git-coord-name) {:git/tag latest-git-tag}}
    latest-git-coord-str (binding [*print-namespace-maps* false]
                           (pr-str latest-git-coord))))



