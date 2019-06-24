Intellij and Cursive (intellij plugin)

Leiningen: build tool
Deps: newer build tool built into Clojure, less features

Right click on project root file in Intelli
- click “create repl” menu option
- this will create a run configuration for running the project


## Install
brew install clojure # clj command line tool, lightweight
brew install leiningen # lein repl

# To reload a file in the command line
(require 'workshop.core' :reload)

Keep file loaded in Repl
Right click on file and "Repl / Load File in Repl"

## Documentation
Print the type
$ (type <thing>)

To read docs on a function
$ (doc when)

Search for documentation
$ (find-doc "remainder")

Autocomplete options
$ (apropo when)

View source
$ (source when)

List functions in file
$ (dir workshop.core)

Cheat sheet:
https://clojure.org/api/cheatsheet