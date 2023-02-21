# Table of contents
-  [`dsann.git`](#dsann.git) 
    -  [`check-git!`](#dsann.git/check-git!) - checks the current directory is a git repo.
    -  [`git-data`](#dsann.git/git-data) - extracts git data (for tags and versions currently).
    -  [`git-latest-tag`](#dsann.git/git-latest-tag) - gets the most recent git tag.
    -  [`git-remote-name`](#dsann.git/git-remote-name) - gets the remote name for deps.edn coords.
    -  [`git-remote-origin`](#dsann.git/git-remote-origin)
    -  [`git-repo?`](#dsann.git/git-repo?) - checks if .git exists.
    -  [`git-tag-data`](#dsann.git/git-tag-data)
    -  [`git-tag-messages`](#dsann.git/git-tag-messages) - extracts a map of the form: {tagname {:tag-message "first line of tag message here" tagname2 ...}.
    -  [`git-tags`](#dsann.git/git-tags) - extracts a map of the form: {tagname {:commit-sha "full sha here" :commit-sha-abbrev "abbreviated sha here"} tagname2 ...}.
    -  [`parse-git-messages`](#dsann.git/parse-git-messages)
    -  [`parse-git-tags`](#dsann.git/parse-git-tags)
    -  [`remove-tag-prefix`](#dsann.git/remove-tag-prefix) - removes refs/tag/ from tagnames.
    -  [`shell-git`](#dsann.git/shell-git) - Checks in a git repo, executes command and return the result.

-----
# <a name="dsann.git">dsann.git</a>






## <a name="dsann.git/check-git!">`check-git!`</a> [:page_facing_up:](https://github.com/davesann/bb-git-local/blob/main/src/bb/dsann/git.clj#L13-L18)
<a name="dsann.git/check-git!"></a>
``` clojure

(check-git!)
```


checks the current directory is a git repo. Throws if not

## <a name="dsann.git/git-data">`git-data`</a> [:page_facing_up:](https://github.com/davesann/bb-git-local/blob/main/src/bb/dsann/git.clj#L97-L109)
<a name="dsann.git/git-data"></a>
``` clojure

(git-data)
```


extracts git data (for tags and versions currently)

## <a name="dsann.git/git-latest-tag">`git-latest-tag`</a> [:page_facing_up:](https://github.com/davesann/bb-git-local/blob/main/src/bb/dsann/git.clj#L73-L78)
<a name="dsann.git/git-latest-tag"></a>
``` clojure

(git-latest-tag)
```


gets the most recent git tag

## <a name="dsann.git/git-remote-name">`git-remote-name`</a> [:page_facing_up:](https://github.com/davesann/bb-git-local/blob/main/src/bb/dsann/git.clj#L89-L94)
<a name="dsann.git/git-remote-name"></a>
``` clojure

(git-remote-name)
```


gets the remote name for deps.edn coords

## <a name="dsann.git/git-remote-origin">`git-remote-origin`</a> [:page_facing_up:](https://github.com/davesann/bb-git-local/blob/main/src/bb/dsann/git.clj#L83-L87)
<a name="dsann.git/git-remote-origin"></a>
``` clojure

(git-remote-origin)
```


## <a name="dsann.git/git-repo?">`git-repo?`</a> [:page_facing_up:](https://github.com/davesann/bb-git-local/blob/main/src/bb/dsann/git.clj#L8-L11)
<a name="dsann.git/git-repo?"></a>
``` clojure

(git-repo?)
```


checks if .git exists

## <a name="dsann.git/git-tag-data">`git-tag-data`</a> [:page_facing_up:](https://github.com/davesann/bb-git-local/blob/main/src/bb/dsann/git.clj#L80-L81)
<a name="dsann.git/git-tag-data"></a>
``` clojure

(git-tag-data)
```


## <a name="dsann.git/git-tag-messages">`git-tag-messages`</a> [:page_facing_up:](https://github.com/davesann/bb-git-local/blob/main/src/bb/dsann/git.clj#L63-L71)
<a name="dsann.git/git-tag-messages"></a>
``` clojure

(git-tag-messages)
```


extracts a map of the form:
   {tagname  {:tag-message   "first line of tag message here"
    tagname2 ...}
  

## <a name="dsann.git/git-tags">`git-tags`</a> [:page_facing_up:](https://github.com/davesann/bb-git-local/blob/main/src/bb/dsann/git.clj#L42-L51)
<a name="dsann.git/git-tags"></a>
``` clojure

(git-tags)
```


extracts a map of the form:
   {tagname  {:commit-sha        "full sha here"
              :commit-sha-abbrev "abbreviated sha here"}
    tagname2 ...}
  

## <a name="dsann.git/parse-git-messages">`parse-git-messages`</a> [:page_facing_up:](https://github.com/davesann/bb-git-local/blob/main/src/bb/dsann/git.clj#L54-L61)
<a name="dsann.git/parse-git-messages"></a>
``` clojure

(parse-git-messages s)
```


## <a name="dsann.git/parse-git-tags">`parse-git-tags`</a> [:page_facing_up:](https://github.com/davesann/bb-git-local/blob/main/src/bb/dsann/git.clj#L32-L40)
<a name="dsann.git/parse-git-tags"></a>
``` clojure

(parse-git-tags s)
```


## <a name="dsann.git/remove-tag-prefix">`remove-tag-prefix`</a> [:page_facing_up:](https://github.com/davesann/bb-git-local/blob/main/src/bb/dsann/git.clj#L27-L30)
<a name="dsann.git/remove-tag-prefix"></a>
``` clojure

(remove-tag-prefix tag)
```


removes refs/tag/ from tagnames

## <a name="dsann.git/shell-git">`shell-git`</a> [:page_facing_up:](https://github.com/davesann/bb-git-local/blob/main/src/bb/dsann/git.clj#L20-L25)
<a name="dsann.git/shell-git"></a>
``` clojure

(shell-git command)
```


Checks in a git repo, executes command and return the result
