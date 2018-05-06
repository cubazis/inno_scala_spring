import scala.annotation.tailrec

object Tasks {
  def main(args: Array[String]): Unit = {
    assert(fib(1) == 0)
    assert(fib(5) == 3)
    assert(prime(10) == 29)
    assert(sum(identity, 1, 1) == 1)
    assert(sum(identity, 1, 10) == 55)
    assert(map((_: Int) + 1, List(1, 2, 3, 4)) == List(2, 3, 4, 5))
    assert(filter((_: Int) > 2, List(1, 2, 3, 4)) == List(3, 4))
    assert(foldLeft(0, (_: Int) + (_: Int), List(1, 2, 3, 4)) == 10)
    assert(map_fold((_: Int) + 1, List(1, 2, 3, 4)) == List(2, 3, 4, 5))
    assert(filter_fold((_: Int) > 2, List(1, 2, 3, 4)) == List(3, 4))
    val one = Succ(Zero)
    val two = Succ(one)
    assert(add(one, two) == Succ(Succ(Succ(Zero))))
    assert(sub(two, one) == Succ(Zero))
    fixCompile()
  }

  // Fibonacci sequence
  def fib(n: Int): Int = {
    @tailrec
    def fib(n: Int, smaller: Int = 0, bigger: Int = 1): Int = n match {
      case 1 => smaller
      case 2 => bigger
      case _ => fib(n - 1, bigger, smaller + bigger)
    }

    fib(n)
  }

  // Prime numbers
  private def isPrime(n: Int): Boolean = {
    assert(n > 1, "n must be bigger than 1")
    if (n == 2) return true
    if (n % 2 == 0) return false

    @tailrec
    def isPrime(curr: Int = 3): Boolean = {
      if (curr * curr > n) true
      else if (n % curr == 0) false
      else isPrime(curr + 2)
    }

    isPrime()
  }

  def prime(n: Int): Int = {
    require(n > 0, "n must be positive")
    if (n == 1) return 2

    @tailrec
    def prime(curr: Int, primesCounter: Int): Int = primesCounter match {
      case `n` => curr
      case _ => prime(curr + 2, primesCounter + (if (isPrime(curr + 2)) 1 else 0))
    }

    prime(1, 1)
  }

  // High order functions - 1
  def sum(func: Int => Int, start: Int, finish: Int): Int = {
    @tailrec
    def sum(curr: Int = start, res: Int = 0): Int = {
      if (curr > finish) res
      else sum(curr + 1, res + func(curr))
    }

    sum()
  }

  // High order functions - 2 - map
  def map[T1, T2](func: T1 => T2, seq: Iterable[T1]): Iterable[T2] = {
    @tailrec
    def map(tail: Iterable[T1] = seq, res: Iterable[T2] = Iterable.empty[T2]): Iterable[T2] = {
      if (tail.isEmpty) res
      else map(tail.tail, res ++ Iterable[T2](func(tail.head)))
    }

    map()
  }

  // High order functions - 3 - filter
  def filter[T](predicate: T => Boolean, seq: Iterable[T]): Iterable[T] = {
    @tailrec
    def filter(tail: Iterable[T] = seq, res: Iterable[T] = Iterable.empty[T]): Iterable[T] = {
      if (tail.isEmpty) res
      else filter(tail.tail, if (predicate(tail.head)) res ++ Iterable[T](tail.head) else res)
    }

    filter()
  }

  // High order functions - 4 - foldLeft
  @tailrec
  def foldLeft[T1, T2](acc: T2, func: (T2, T1) => T2, seq: Iterable[T1]): T2 =
    if (seq.isEmpty) acc
    else foldLeft(func(acc, seq.head), func, seq.tail)

  // High order functions - 5 - filter and map revisited
  def map_fold[T1, T2](func: T1 => T2, seq: Iterable[T1]): Iterable[T2] = foldLeft(
    Iterable.empty[T2],
    (acc: Iterable[T2], el: T1) => acc ++ Iterable[T2](func(el)),
    seq)

  def filter_fold[T](predicate: T => Boolean, seq: Iterable[T]): Iterable[T] = foldLeft(
    Iterable.empty[T],
    (acc: Iterable[T], el: T) => if (predicate(el)) acc ++ Iterable[T](el) else acc,
    seq)

  // Natural numbers
  sealed abstract class Nat

  case object Zero extends Nat

  case class Succ(nat: Nat) extends Nat

  @tailrec
  def add(nat1: Nat, nat2: Nat): Nat = nat1 match {
    case Succ(nat) => add(nat, Succ(nat2))
    case Zero => nat2
  }

  @tailrec
  def sub(nat1: Nat, nat2: Nat): Nat = nat2 match {
    case Zero => nat1
    case Succ(nat2_) => nat1 match {
      case Succ(nat1_) => sub(nat1_, nat2_)
      case Zero => throw new IllegalArgumentException("Subtrahend must be less than minuend")
    }
  }

  // FixCompile
  def fixCompile(): Unit = {
    val mapper = (i: Int) => if (i % 2 != 0) i * 2 else 0

    val result = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
      .map(mapper)
      .foldLeft(0) { (acc, v) => acc + v }

    print(result)
  }
}