
object Lecture3 {
        def main(args: Array[String]): Unit = {
                println("This is the third lecture support code")
                println("You can run all the examples of 'repl' in REPL shell")
                repl()
        }

        def repl(unit: Unit): Unit = {
                /** Partially-applied function */
                println("Partially-applied function")
                def sum(_1: Int, _2:Int, _3:Int) = _1 + _2 + _3
                val a = 1
                val b = 2
                val c = 3
                def sum1 = sum(_:Int, b, c)
                def sum2 = sum(a, _:Int, c)
                def sum3 = sum(a, b, _:Int)
                println(sum1(5), sum2(5), sum3(5))
        }
}
