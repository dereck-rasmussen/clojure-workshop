Intellij and Cursive (intellij plugin)

Cognitect

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
docs.clj

Cheat sheet:
https://clojure.org/api/cheatsheet

## Variables

## Flow control
if cond ex1 ex2
do exectute a block of expressions and returns last vale
When is like if without an else
when => if ... do ...
