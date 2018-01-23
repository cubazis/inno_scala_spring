/** import area */

// Look at this construction {_ => _}, very functional!
import scala.collection.mutable.{HashMap => hashmap}

object Lecture1 {
        def main(args: Array[String]): Unit = {
                println("This is the first lecture support code")
                println("You can run all the examples of 'repl' in REPL shell")
                repl()
        }

        def repl(unit: Unit): Unit = {
                /** Conciseness (1) */
                println("Conciseness (1)")

                // Conciseness in classes: ~2 times more compact than Java

                // Scaladoc example
                /** class Point with hidden constructor and override toString function.
                  *
                  * Notice!
                  * toString overrides Java's Object.toString which is the method eventually called by println.
                  * If you don't override it - the default implementation
                  * (which prints the class name and memory address) will be used,
                  * and NOT any other method that you create.
                  *
                  * @param x abscissa
                  * @param y ordinate
                  */
                class Point(val x:Int, y:Int) {override def toString = "("+x+" "+y+")"}
                val p = new Point(3,4)
                println(p.toString)
                println(p.x)

                //println(p.y) //try to print ordinate of [Point] p, why is it so? The answer is in Section 5.7 of Chapter 5

                // Conciseness in types declaration: please look at import area above!!!
                val hashmap1: hashmap[Int,String] = new hashmap[Int,String]()
                // Due to 'Type inference' we can just write
                val hashmap2 = new hashmap[Int,String]()

                /** Conciseness (2) */
                println("Conciseness (2)")
                /** TODO: */

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
                println()

                /** Functions & Closures */
                println("Functions & Closures")
                val addOne = (x: Int) => x + 1
                println(addOne(41))

                var more = 1 //look at declaration, it's not a 'val' anymore. What is the difference between var and val?
                val addMore = (x: Int) => x + more
                println(addMore(41))

                more = 5
                println(addMore(37))

                var sum = 0
                someNumbers.foreach(sum += _)
                println(sum)

                def makeIncreaser(more: Int) = (x: Int) => x + more
                val inc1 = makeIncreaser(1)
                val inc2 = makeIncreaser(2)
                println(inc1(41))
                println(inc2(40))

                /** TODO: Tasks for your homework: */

                /** 1. Write the function makeMultiplier that returns the
                  * closure applying multiplication on an external variable by its
                  * parameter.
                  */
                // Write your code here

                /** 2. Write the function cutter returning its parameter if its
                  * value is less than the value of an external variable or the
                  * value of the external variable otherwise.
                  */
                // Write your code here

                /** 3. Write the function predicate remover that returns true if
                  * its parameter is less than the value of an external variable.
                  */
                // Write your code here

                /** 4. Create a list of 20+ integer values. Define the external
                  * variable limit that would play a role of the external variable.
                  */
                // Write your code here

                /** 5. Create a “multiplier” closure using makeMultiplier function.
                  * Apply multiplier and cutter to each list element, and then
                  * apply remover to the whole list. Show the resulting list.
                  */
                // Write your code here

                /** 6. Repeat step 5 for different values of limit.
                  */
                // Write your code here


        }
}
