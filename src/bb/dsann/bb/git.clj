(ns dsann.bb.git
  (:require [babashka.process :refer [shell]]
            [clojure.string :as s]
            [babashka.fs :as fs]
            [dsann.let-map :refer [let-map]]))


(defn git-repo?
  "checks if .git exists"
  []
  (fs/exists? ".git"))

(defn check-git!
  "checks the current directory is a git repo. Throws if not"
  []
  (when-not (git-repo?)
    (let [p (fs/canonicalize ".")]
      (throw (ex-info (str "git-local : '" p "'" " is not a git repository") {:path p})))))

(defn shell-git
  "Checks in a git repo, executes command and return the result"
  [command]
  (check-git!)
  (let [result (shell {:out :string} command)]
    (->> result :out)))

(defn remove-tag-prefix
  "removes refs/tag/ from tagnames"
  [tag]
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

(defn git-tags
  "extracts a map of the form:
   {tagname  {:commit-sha        \"full sha here\"
              :commit-sha-abbrev \"abbreviated sha here\"}
    tagname2 ...}
  "
  []
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

(defn git-tag-messages
  "extracts a map of the form:
   {tagname  {:tag-message   \"first line of tag message here\"
    tagname2 ...}
  "
  []
  (-> "git tag -n"
      shell-git
      parse-git-messages))

(defn git-latest-tag 
  "gets the most recent git tag"
  []
  (-> "git describe --abbrev=0"
      shell-git
      s/trim))

(defn git-tag-data []
  (merge-with merge (git-tags) (git-tag-messages)))

(defn git-remote-origin []
  "gets the remote origin string"
  (->> "git config --get remote.origin.url"
      shell-git
      s/trim))

(defn git-remote-name 
  "gets the remote name for deps.edn coords"
  []
  (->> (git-remote-origin)
      (re-matches (re-pattern ".*github[.]com:(.*)[.]git$"))
      second))


(defn git-data 
  "extracts git data (for tags and versions currently)" 
  []
  (let-map
    git-remote-name (git-remote-name)
    git-coord-name  (str "io.github." git-remote-name)
    git-tag-data    (git-tag-data)

    latest-git-tag       (git-latest-tag)
    latest-git-sha       (-> git-tag-data (get latest-git-tag) :commit-sha-abbrev)
    latest-git-coord     {(symbol git-coord-name) {:git/tag latest-git-tag}}
    latest-git-coord-str (binding [*print-namespace-maps* false]
                           (pr-str latest-git-coord))))



