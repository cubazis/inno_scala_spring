object Lecture2 {
        def main(args: Array[String]): Unit = {
                println("This is the second lecture support code")
                println("You can run all the examples of 'repl' in REPL shell")
                repl()
        }

        def repl(unit: Unit): Unit = {
                /** Functional objects */
                println("Functional objects")
                val strings = new Array[String](3)
                strings(0) = "Hello"
                strings(1) = ", "
                strings(2) = "world!\n"
                for (i <- 0 to 2)
                        print(strings(i))

                println(strings(0))
                println("The same stuff")
                println(strings.apply(0))
        }
}