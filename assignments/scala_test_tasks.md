# Scala test tasks

This tasks is just to get general understanding of what you know about `Scala` and functional programming. If you can't do some tasks don't do them, this would not be graded. If you know how to do something simmilar but with different API, it's ok and welcomed!

We will check your solutions, but in order to check them, fork the [repo](https://github.com/cubazis/inno_scala_spring), and please do a pull-requst with your name and name of this assignment. For example: "Basov Alexandr - Scala test tasks".

Later solutions would be added.
## Fibonacci sequence
Write function `fib` that computes n-member of fibonacci sequence

example:
```scala
assert(fib(5) == 3) // should not fail
```

Try to use tailrec to perform better

## Prime numbers
Write function `prime` that computes n's prime number

example:
```scala
assert(prime(10) == 31)
```

## High order functions - 1
Write function `sum` that takes function `f` as a first argument from `Int` to `Int` and two `Int`s as a second `a` and third `b` argument. It should call `f` on `a` and summs with the result of `sum(f, a + 1, b)`

example:
```scala
def id(x: Int): Int = x
assert(sum(id, 1, 1) == 1)
assert(sum(id, 1, 10) == 55)
```

## High order functions - 2 - map
For next tasks consult this picture, to get intuitive understanding of required functions, `foldLeft` is a `reduce`
![](https://www.lambda3.com.br/wp-content/uploads//2017/01/map-filter-reduce-in-emoji-1.png)

Write a function `map` that takes a function from `A` to `B` and a list of `A`s, `map` should iterate list and apply function to each member, so in result we will receive list of `B`s

function signature `def map[A, B](f: A => B, l: List[A]): List[B]`

example:
```scala
val numbers = List(1, 2, 3, 4)
val result = List(2, 3, 4, 5)
assert(map((i: Int) => i + 1, numbers) == result)
```

## High order functions - 3 - filter
Write a function `filter` that takes a function `p` from `A` to `Bollean` and a list of `A`s, `filter` should iterate list and apply function to each member, if member saticifies `p` than it goes to result list. As a result `filter` should return only members of list that saticfies `p`.

function signature `def filter[B](p: B => Bollean, l: List[A]): List[A]`

example:
```scala
val numbers = List(1, 2, 3, 4)
val result = List(3, 4)
assert(filter((i: Int) => i > 2, numbers) == result)
```

## High order functions - 4 - foldLeft
Write a function `foldLeft` that takes an acumulator `acc` of type `B`, function `f` that takes `acc` as a first argument and element of type `A` and list of `A`s as a third argument. `fold` iterate through list of `A`s and apply function `f` to acc and element of list and combines them in a some way so it produces new `B`.

function signature 
```scala
def foldLeft[A, B](acc: B, f: (B, A) => B, l: List[A])
```
example:
```scala
val numbers = List(1, 2, 3, 4)
asset(foldLeft(0, (_ + _), numbes) == 10)
```

## High order functions - 5 - filter and map revisited
Function `foldLeft` is really expressive, you can use it to implement `filter` and `map` functions using only just `foldLeft`, try to implement them:) See examples of `filter` and `map` in corresponding tasks. Probably you can replace old functions or use new names for new functions.

## Natural numbers
Write a class that will represent natural numbers as a `Zero` or successor of `0` and two functions to `add` them and `sub` subtract them.

Probably you will need:
- Case-classes
- Patern-matching
[Good tutorial](https://https://docs.scala-lang.org/tour/pattern-matching.html) on this topics

Class `Nat` for the task:
```scala
sealed abstract class Nat

case object Zero extends Nat
case class Succ(n: Nat) extends Nat
```

example:
```scala
val one = Succ(Zero)
val two = Succ(one)
assert((add(one, two)) == Succ(Succ(Succ(Zero))))
assert((sub(two, one)) == Succ(Zero))
```

## FixCompile
Remove comments and fix the program so it would compile:
This program should double all odd members of the list and then sum them.
Fix the `mapper` function, notice that `map` and `foldLeft` used from the standrad library.

```scala
object FixCompile extends App {

//  val mapper = (i: String) => if (i % 2 != 0) i * 2
//
//  val result = List(1, 2, 3, 4, 5, 6, 7, 8, 9).map {
//      mapper
//    }.foldLeft(0) { (acc, v) => acc + v }
//
//   print(result)
}
```
