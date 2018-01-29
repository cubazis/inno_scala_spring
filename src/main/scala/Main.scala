import scala.collection.mutable.ListBuffer

object Main extends App {

  def fib(x: Int): BigInt = {
    def fib1(x: Int, prev: BigInt = 0, next: BigInt = 1): BigInt = x match {
      case 0 => prev
      case 1 => next
      case _ => fib1(x - 1, next, next + prev)
    }

    fib1(x)

  }


  def sum(f: Int => Int, a: Int, b: Int): Int = {
    if (a < b)
      f(a) + sum(f, a + 1, b)
    else
      f(a)
  }

  def map[A, B](f: A => B, l: List[A]): List[B] = {
    var l2 = ListBuffer[B]()
    l.foreach(l2 += f(_))
    l2.toList
  }

  def filter[A](p: A => Boolean, l: List[A]): List[A] = {
    var l2 = ListBuffer[A]()
    l.foreach(x => if (p(x)) l2 += x)
    l2.toList
  }

  def foldLeft[A, B](acc: B, f: (B, A) => B, l: List[A]): B = {
    var s: B = acc
    l.foreach(x => s = f(s, x));
    s
  }

  def map1[A, B](f: A => B, l: List[A]): List[B] = {
    def fun(lb: ListBuffer[B], l: A): ListBuffer[B] = {
      lb += f(l)
    };

    foldLeft(ListBuffer[B](), fun, l).toList
  }

  def filter1[A](p: A => Boolean, l: List[A]): List[A] = {
    def fun (lb: ListBuffer[A], l: A): ListBuffer[A] = {
      if (p(l))
        lb+=l
      else
        lb
    }

    foldLeft(ListBuffer[A](), fun, l).toList
  }

  sealed abstract class Nat

  case object Zero extends Nat
  case class Succ(n: Nat) extends Nat

  def add(a: Nat, b: Nat): Nat = {
    b match {
      case Zero => a
      case Succ(x) => add(Succ(a), x)
    }
  }

  def sub(a: Nat, b: Nat): Nat = {
    b match {
      case Zero => a
      case Succ(x) => a match {
        case Zero => null
        case Succ(y) => sub(y, x)
      }
    }
  }


  override def main(args: Array[String]): Unit = {
    assert(fib(0) == 0)
    assert(fib(5) == 5)

    def id(x: Int): Int = x

    assert(sum(id, 1, 1) == 1)
    assert(sum(id, 1, 10) == 55)

    var numbers = List(1, 2, 3, 4)
    var result = List(2, 3, 4, 5)
    assert(map((i: Int) => i + 1, numbers) == result)

    numbers = List(1, 2, 3, 4)
    result = List(3, 4)
    assert(filter((i: Int) => i > 2, numbers) == result)

    numbers = List(1, 2, 3, 4)
    assert(foldLeft(0, (a: Int, b: Int) => a + b, numbers) == 10)

    numbers = List(1, 2, 3, 4)
    result = List(2, 3, 4, 5)
    assert(map1((i: Int) => i + 1, numbers) == result)

    numbers = List(1, 2, 3, 4)
    result = List(3, 4)
    assert(filter1((i: Int) => i > 2, numbers) == result)

    val one = Succ(Zero)
    val two = Succ(one)
    assert((add(one, two)) == Succ(Succ(Succ(Zero))))
    assert((sub(two, one)) == Succ(Zero))

    val mapper = (i: Int) => if (i % 2 != 0) i * 2 else i

    val result1 = List[Int](1, 2, 3, 4, 5, 6, 7, 8, 9).map {
      mapper
    }.fold(0) { (acc, v) => acc + v }

    print(result1)

    println("done")
  }

}



