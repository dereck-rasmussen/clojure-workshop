## EDN
clojure syntax for data files. It is not evaluated as code

## Dependencies
in project.clj file, hosted on maven central

require in the repl with $ require 'clojure.test.check
(drop the org from the namespace is the convention when requiring)

As a convention, alpha namespaces include "alpha" in the namespace 

## Testing
https://clojure.org/guides/spec
Right click on file and select "Run test in ns repl"

REPL (intellij file-right-click menu option)
Switch Repl to current file

Example test vs generative test (random sample input?)

## Misc
` backtick is used to resolve a namespace first before quotting
`temp/cels-faren => 'temperater/cels-faren


## Recursion 
loop and recur keywords for tail recursion

## Testing 2

### with-redefs
You can override clojure functions for a test using redefs
 with-redefs [
 	rand-int (fn [] ...)
 ]
 (...)
 
### Prodocols 


## Atoms
Boxes for immutable data
(atom a)
(deref a) ; => 5
(@a) ; => 5
(reset! a 6) ; => 6
(swap! a inc) ; => 7
(def m (atom {
:foo 1
:bar "hi"
}))

## Game with channels

prompt is channel
- waits for message input
- then triggers input channel to get input

is trigger for input