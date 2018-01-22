object Lesson2 {
        def main(args: Array[String]): Unit = {
                println("This is the second lecture support code")
                println("You can run all the examples of repl in REPL shell")
                repl()
        }

        def repl(unit: Unit): Unit = {

                /** Placeholders (1) */
                println("Placeholders (1)")
                val someNumbers = List(-11, 10, -5, 0, 5, 10)
                someNumbers.filter((x:Int) => x > 0).foreach((x:Int) => print(x+" "))
                println()
                println("So, the same way")
                someNumbers.filter((x) => x > 0).foreach((x) => print(x+" ")) // here is no type specification
                println()
                println("And, another bombic way")
                someNumbers.filter(_ > 0).foreach(x => print(x+" ")) // amazing!
                println("\n")

                /** Placeholders (2) */
                println("Placeholders (2)")
                val f = (_: Int) + (_:Int)
                println(f(40, 2)) // It's ficking awesome!
                println("\n")

                /** Functions & Closures (1) */

                println("\n")

        }
}
