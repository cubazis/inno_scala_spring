// Fibonacci sequence

def fib(i: Int): Int = {
    require(i >= 0)
    if (i == 0)
        0
    else if (i == 1)
        1
    else
        fib(i - 2) + fib(i - 1)
}

assert(fib(0) == 0)
assert(fib(5) == 5) // should not fail, if we count fib from 0

// Prime numbers

// very ugly and unoptimized, sorry
def isPrime(n: Int) = (2 until n).forall(n % _ != 0)
def prime(i: Int): Int = {
    var ii = i
    var c = 2
    while(ii > 0) {
        while( ! isPrime(c)) {
            c += 1
        }
        c += 1
        ii -= 1
    }
    c - 1
}

assert(prime(10) == 29)

// High order functions - 1

def sum(f: Int => Int, a: Int, b: Int): Int = {
    if (a < b) {
        f(a) + sum(f, a + 1, b)
    } else {
        f(a)
    }
}

def id(x: Int): Int = x
assert(sum(id, 1, 1) == 1)
assert(sum(id, 1, 10) == 55)

// High order functions - 2 - map

def map[A, B](f: A => B, l: List[A]): List[B] = {
    if (l.isEmpty) {
        Nil
    } else {
        f(l.head) :: map(f, l.tail)
    }
}

val numbers = List(1, 2, 3, 4)
val result = List(2, 3, 4, 5)
assert(map((i: Int) => i + 1, numbers) == result)

// High order functions - 3 - filter

def filter[A](p: A => Boolean, l: List[A]): List[A] = {
    if (l.isEmpty) {
        Nil
    } else if (p(l.head)) {
        l.head :: filter(p, l.tail)
    } else {
        filter(p, l.tail)
    }
}

val resultFilter = List(3, 4)
assert(filter((i: Int) => i > 2, numbers) == resultFilter)

// High order functions - 4 - foldLeft

def foldLeft[A, B](acc: B, f: (B, A) => B, l: List[A]): B = {
    if (l.isEmpty) {
        acc
    } else {
        foldLeft(f(acc, l.head), f, l.tail)
    }
}

assert(foldLeft(0, (a: Int, b: Int) => a + b, numbers) == 10)

// High order functions - 5 - filter and map revisited

def mapUsingFoldLeft[A, B](f: A => B, l: List[A]): List[B] = {
    foldLeft(List[B](), (b: List[B], a: A) => f(a) :: b, l.reverse)
}

assert(mapUsingFoldLeft((i: Int) => i + 1, numbers) == result)

def filterUsingFoldLeft[A](p: A => Boolean, l: List[A]): List[A] = {
    foldLeft(List[A](), (b: List[A], a: A) => {
        if (p(a)) {
            a :: b
        } else {
            b
        }
    }, l.reverse)
}

assert(filterUsingFoldLeft((i: Int) => i > 2, numbers) == resultFilter)

// Natural numbers

sealed abstract class Nat
case object Zero extends Nat
case class Succ(n: Nat) extends Nat

def add(a: Nat, b: Nat): Nat = {
    (a, b) match {
        case(Zero, y) => y
        case(x, Zero) => x
        case(Succ(x), y) => add(x, Succ(y))
        case(x, Succ(y)) => add(Succ(x), y)
    }
}

def sub(a: Nat, b: Nat): Nat = {
    (a, b) match {
        case(Zero, y) => Zero
        case(x, Zero) => x
        case(Succ(x), Succ(y)) => sub(x, y)
    }
}

val one = Succ(Zero)
val two = Succ(one)
assert((add(Zero, two)) == Succ(Succ(Zero)))
assert((add(one, two)) == Succ(Succ(Succ(Zero))))
assert((sub(two, one)) == Succ(Zero))
assert((sub(two, Zero)) == Succ(Succ(Zero)))

// FixCompile

def fixCompile(): Int = {

    val mapper = (i: Int) => if (i % 2 != 0) i * 2 else 0

    val result = List(1, 2, 3, 4, 5, 6, 7, 8, 9).map {
        mapper
    }.foldLeft(0) { (acc: Int, v: Int) => acc + v }

    result

}

print(fixCompile())
