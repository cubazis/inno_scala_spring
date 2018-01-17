import scala.annotation.tailrec

class Assignment1 {
  // Fibonacci sequence
  
  def fib(n: Int) = fibHelper(0, n, 0, 1)
  @tailrec
  private def fibHelper(i: Int, n: Int, acc: Int, acc2: Int): Int = {
    if (n == 0)
      return 0
    if (n == 1)
      return 1
    if (i == n)
      return acc
    return fibHelper(i + 1, n, acc2, acc + acc2)
  }

  // Prime numbers

  def prime(n: Int): Int = primeHelper(n, 0, 1)

  @tailrec
  private def primeHelper(n: Int, counter: Int, number: Int): Int = {
    if (isPrime(number, 3)) {
      if (counter + 1 == n)
        return number
      else
        return primeHelper(n, counter + 1, number + 1)
    } else
      return primeHelper(n, counter, number + 1)

  }

  @tailrec
  private def isPrime(n: Int, i: Int): Boolean = {
    if (n % 2 == 0 || (n % i == 0 && n != i))
      return false

    if (i >= (math.sqrt(n) + 1).toInt)
      return true
    else
      return isPrime(n, i + 2)
  }

  // High order functions - 1
  
  def sum(f: (Int) => Int, a: Int, b: Int) = sumHelper(f, a, b, 0)

  @tailrec
  private def sumHelper(f: (Int) => Int, a: Int, b: Int, acc: Int): Int = {
    if (a >= b)
      return acc + f(a)
    return sumHelper(f, a + 1, b, acc + f(a))
  }

  // High order functions - 2 - map
  
  def map[A, B](f: A => B, l: List[A]): List[B] = l match {
    case head :: tail => List[B](f(head)) ::: map(f, tail)
    case Nil          => List[B]()
  }

  // High order functions - 3 - filter
  
  def filter[A](p: A => Boolean, l: List[A]): List[A] =
    filterHelper2(p, l, List[A]())

  @tailrec
  private def filterHelper2[A](p: A => Boolean,
                               l: List[A],
                               result: List[A]): List[A] = l match {
    case head :: tail => {
      if (p(head))
        return filterHelper2(p, tail, result ::: List[A](head))
      else
        return filterHelper2(p, tail, result)
    }
    case Nil => result
  }

  // High order functions - 4 - foldLeft
  
  def foldLeft[A, B](acc: B, f: (B, A) => B, l: List[A]): B = l match {
    case head :: tail => foldLeft(f(acc, head), f, tail)
    case Nil          => acc
  }

  // High order functions - 5 - filter and map revisited
  
  def foldMap[A, B](f: A => B, l: List[A]): List[B] =
    foldLeft(List[B](), (b: List[B], a: A) => b ::: List[B](f(a)), l)

  def foldFilter[A](p: A => Boolean, l: List[A]): List[A] =
    foldLeft(List[A](), (b: List[A], a: A) => {
      if (p(a)) b ::: List[A](a) else b
    }, l)

  sealed abstract class Nat

  case object Zero extends Nat
  case class Succ(n: Nat) extends Nat

  def add(a: Nat, b: Nat): Nat = (a, b) match {
    case (Zero, _)    => b
    case (_, Zero)    => a
    case (Succ(x), y) => add(x, Succ(y))
    case (x, Succ(y)) => add(Succ(x), y)
  }

  def sub(a: Nat, b: Nat): Nat = (a, b) match {
    case (_, Zero)          => a
    case (Succ(x), Succ(y)) => sub(x, y)
  }

  def fixCompile(): Int = {
    val mapper = (i: Int) => if (i % 2 != 0) i * 2 else 0

    val result = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
      .map {
        mapper
      }
      .foldLeft(0) { (acc, v) =>
        acc + v
      }

    return result
  }

  def test() = {

    assert(fib(5) == 5) //  starting from 0
    assert(prime(10) == 29)

    def id(x: Int): Int = x
    assert(sum(id, 1, 1) == 1)
    assert(sum(id, 1, 10) == 55)

    val numbers = List(1, 2, 3, 4)
    val result = List(2, 3, 4, 5)
    assert(map((i: Int) => i + 1, numbers) == result)

    val result2 = List(3, 4)
    assert(filter((i: Int) => i > 2, numbers) == result2)

    assert(foldLeft(0, (a: Int, b: Int) => a + b, numbers) == 10)

    assert(foldMap((i: Int) => i + 1, numbers) == result)
    assert(foldFilter((i: Int) => i > 2, numbers) == result2)

    assert(fixCompile() == 50)
    val one = Succ(Zero)
    val two = Succ(one)
    assert((add(one, two)) == Succ(Succ(Succ(Zero))))
    assert((sub(two, one)) == Succ(Zero))
  }
}

val a = new Assignment1();
a.test();
