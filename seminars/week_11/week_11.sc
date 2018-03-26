println("kek")

/** The following trait declares one of each kind of
  *  abstract member: an abstract type (T), method
(transform), val (initial), and var (current) */

trait Abstract {
        type T
        def transform(x: T): T
        val initial: T
        var current: T
}


/** A concrete implementation of Abstract needs
  * to fill in definitions for each of its abstract members.
  * The implementation gives a concrete meaning to the
  * type name T by defining it as an alias of type String.
  * */
class Concrete extends Abstract {
        type T = String
        def transform(x: String) = x + x
        val initial = "Hapiness"
        var current = initial
}

type C = String
val c: C = "test string"


abstract class Fruit {val v: String // `v' for value
def m: String // `m' for method
}
abstract class Apple extends Fruit {
        val v: String
        val m: String // OK to override a `def' with a `val'
}
abstract class BadApple extends Fruit {
        def v: String // ERROR: cannot override a `val' with a `def'
        def m: String
}

trait AbstractTime {
        var hour: Int
        var minute: Int
}

trait RationalTrait {
        val numerArg: Int
        val denomArg: Int
        require(denomArg != 0)
        private val g = gcd(numerArg, denomArg)
        val numer = numerArg / g
        val denom = denomArg / g

        private def gcd(a: Int, b: Int): Int =
                if (b == 0) a else gcd(b, a % b)

        override def toString = numer + "/" + denom
}

class RationalClass(n: Int, d: Int) extends {
        val numerArg = n
        val denomArg = d
} with RationalTrait {
        def + (that: RationalClass) = new RationalClass(
                numer * that.denom + that.numer * denom,
                denom * that.denom
        )
}

trait LazyRationalTrait {
        val numerArg: Int
        val denomArg: Int
        lazy val numer = numerArg / g
        lazy val denom = denomArg / g
        override def toString = numer + "/" + denom
        private lazy val g = {
                require(denomArg != 0)
                gcd(numerArg, denomArg)
        }
        private def gcd(a: Int, b: Int): Int =
                if (b == 0) a else gcd(b, a % b)
}