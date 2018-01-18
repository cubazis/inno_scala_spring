import scala.annotation.tailrec



object Solutions {

  sealed abstract class Nat
  case object Zero extends Nat
  case class Succ(n: Nat) extends Nat

  def add(a: Nat, b: Nat): Nat = {
    (a, b) match {
      case (Zero, y) => y
      case (x, Zero) => x
      case (x: Succ, y: Succ) => Succ(Succ(add(x.n, y.n)))
    }
  }

  def sub(a: Nat, b: Nat): Nat = {
    (a, b) match {
      case (x, Zero) => x
      case (Zero, y) => y
      case (x: Succ, y: Succ) => sub(x.n, y.n)
    }
  }


  def main(args: Array[String]): Unit = {
    // counting fib from 1
    assert(fib(5) == 3)
    assert(fib(1) == 0)
    assert(fib(2) == 1)
    assert(fib(10) == 34)
    // http://www.maths.surrey.ac.uk/hosted-sites/R.Knott/Fibonacci/fibtable.html
    assert(fib(18) == 1597)

    assert(sum(id, 1, 1) == 1)
    assert(sum(id, 1, 10) == 55)

    val numbers = List(1, 2, 3, 4)
    val result = List(1.0, 8.0, 27.0, 64.0)
    var evenNumbers = List(2, 4)

    assert(map((i: Int) =>  math.pow(i, 3), numbers) == result)
    assert(filter((i: Int) => i % 2 == 0, numbers) == evenNumbers)
    assert(foldLeft(0, (a: Int, b: Int) => a * b % 5, numbers) == 0)

    // map with foldLeft
    assert(mapWithFold((i: Int) =>  math.pow(i, 3), numbers) == result)

    // filter with foldLeft
    assert(filterWithFold((i: Int) => i % 2 == 0, numbers) == evenNumbers)

    // Natural numbers
    val one = Succ(Zero)
    val two = Succ(one)
    assert((add(one, two)) == Succ(Succ(Succ(Zero))))
    assert((sub(two, one) == Succ(Zero)))

    // fix compile
    assert(fixCompile() == 70)
  }

  def fib(n: Int): Int = {

    @tailrec
    def calc_fib(pre_last: Int, last: Int, n: Int): Int = {
      if (n == 1)
        pre_last
      else
        calc_fib(last, last + pre_last, n - 1)
    }
    calc_fib(0, 1, n)
  }

  def id(x: Int): Int = x

  def sum(f: (Int) => Int, a: Int, b: Int): Int = {
    if (a == b)
      id(a)
    else
      f(a) + sum(f, a + 1, b)
  }

  def map[A, B](func: A => B, l: List[A]): List[B] = {

    def helper(result: List[B], initial: List[A]): List[B] = {
      if (initial.isEmpty)
        result
      else
        helper(result :+ func(initial.head), initial.slice(1, initial.size))
    }

    helper(List[B](), l)
  }

  def filter[A](func: A => Boolean, l: List[A]): List[A] = {

    def helper(result: List[A], initial: List[A]): List[A] = {
      if (initial.isEmpty)
        result
      else if (func(initial.head))
        helper(result :+ initial.head, initial.slice(1, initial.size))
      else
        helper(result, initial.slice(1, initial.size))
    }

    helper(List[A](), l)
  }

  def foldLeft[A, B](acc: B, f: (B, A) => B, l: List[A]): B = {

    def helper(result: B, initial: List[A]): B = {
      if (initial.isEmpty)
        result
      else
        helper(f(result, initial.head), initial.slice(1, initial.size))
    }

    helper(acc, l)
  }

  def mapWithFold[A, B](func: A => B, l: List[A]): List[B] = {
    foldLeft(List[B](), (l: List[B], elem: A) => l :+ func(elem), l)
  }

  def filterWithFold[A](func: A => Boolean, l: List[A]): List[A] = {
    val predicate = (list: List[A], elem: A) => {
      if(func(elem))
        list :+ elem
      else
        list
    }
    foldLeft(List[A](), predicate, l)
  }

  def fixCompile(): Int = {
    val mapper = (i: Int) => if (i % 2 != 0) i * 2 else i

    val result = List(1, 2, 3, 4, 5, 6, 7, 8, 9).map(
      mapper
    ).foldLeft(0) ( (acc, v) => acc + v )

    result
  }
}

