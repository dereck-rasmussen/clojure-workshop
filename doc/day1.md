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

## Collections
contains? 
conj disj
### Maps
get 
assoc dissoc 

## Records
* slightly faster
* can extend behavior

## Iterators and sequences
doseq => for loop
map 
for => for comprehension 
mapcat => flatmap
concat 

-> thread first => pipe (pass item as last arg in expression)
->> thread last => for sequence functions map, etc (pass item as first arg to next expression)

## Namespace
One namespace per file

// Proper way to require libraries into your file
(ns workshop.temperature
    (:require [clojure.string :as string])
)

z.x :as x
z.x :refer [fn1 fn2]
z.x :refer :all

## Clojure.spec
Validates shape of data between functions