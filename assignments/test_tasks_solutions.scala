import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks._;

// Fibonacci: count from 0 member
def fib(i: Int): Int = {
  if(i == 0) {
    return 0;
  } else if (i == 1) {
    return 1;
  } else {
    return (fib(i - 1) + fib(i - 2));
  };
};

println("Fibonacci: " + fib(5));

// Prime
def prime(i: Int): Int = {
  var count = 1;
  var j = 2;

  if (count == i) return j;

  while (count < i) {
    var flag = true;
    j = j + 1;
    println("J:= " + j)
    breakable {
      for (x <- (j - 1) to 2 by -1) {
        println(x)
        if (j % x == 0) {
          println("lets exit")
          flag = false;
          break;
        }
      }
      if (flag) {
        count = count + 1;
      }
    }
  }

  return j;
}

println("Prime: " + prime(10));

//High order functions - 1
def id(x: Int): Int = x;

def sum(id: (Int => Int), a: Int, b: Int): Int = {
  if (a < b) {
    id(a) + sum(id, a + 1, b);
  } else {
    id(a);
  }
}

println("sum(id,1,1) = " + sum(id, 1, 1));
println("sum(id,1,10) = " + sum(id, 1, 10));


// High order functions - 2
def map[A, B](f: (A => B), x: List[A]): List[B] = {
  // val y = x.foreach(f);
  var y = ListBuffer[B]();
  for (xx <- x) {
    y += f(xx);
  }
  y.toList;
}

val numbers = List(1, 2, 3, 4)
val result = List(2, 3, 4, 5)
println("Map = " + map((i: Int) => i + 1, numbers));


// High order functions - 3
def filter[A](p: A => Boolean, l: List[A]): List[A] = {
  var y = ListBuffer[A]();
  for (x <- l) {
    if(p(x)) {
      y += x;
    }
  }
  y.toList;
}
val result2 = List(3, 4)
println("Filter: " + filter((i: Int) => i > 2, numbers));

// High order functions - 4
def foldLeft[A, B](acc: B, f: (B, A) => B, l: List[A]): B = {
  var result = acc;
  for (x <- l) {
    result = f(result, x);
  }
  result;
}
println("FoldLeft: " + foldLeft(0, (a: Int, b: Int) => a + b, numbers))


// High order functions - 5
var lbuffer = ListBuffer[Int]();
println("Map through FoldLeft: " +
  foldLeft(lbuffer, (a: ListBuffer[Int], b: Int) => a += (b + 1), numbers).toList);

var lbuffer2 = ListBuffer[Int]();
def p(a: ListBuffer[Int], b: Int): ListBuffer[Int] = {
  if (b > 2) a += b;
  a;
}
println("Filter through FoldLeft: " + foldLeft(lbuffer2, p, numbers));