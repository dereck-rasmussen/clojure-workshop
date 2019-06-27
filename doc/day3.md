# Day 3

## Spec blog post
https://clubhouse.io/blog/using-clojure-spec-datomic-and-webhooks-to-integrate-version-control-systems-vcs

* macros
* webapps
* basic optimization
* other labs


## Macros

most clojure keywords are macros: or and loop etc

## Web App

{request map}
-> Middleware
	-> MiddleWare 
		-> Handler
		<-
	<- middleWare 
<- middleWare 
{Response map}

Pedistal style
{Request Context}
-> :enter fn Interceptor :leave fn -> {Response Context}
-> :enter :leave ->
-> :enter :leave ->
	-> handler fn -> 
	
Context
{:request { ring request map }
 :response { ring request map }
}

## Afternoon overview day 3
Destructing 
Errors/Exceptions
Component/Systems/reload
Performance/Reflection/Interop

## Not covered
More Spec
Transducer
Multimethods
Refs
fn/partial/apply/comp/#()

## Lbs
Sums & Ciphers
Rock/Paper/Scissors
Poker
core.async
Interop/Performance


## Destructuring 


## Evaluating a library

- How many (transitive) dependencies 
- Can you read/understand the code?
- Wrapping another Java library API? Is it adding value? or just a wapper?
- How many new concepts?
- How many macros? More is bad!!!
- How heavily used by others?
	- Can you just find a more heavily used Java lib and interop?