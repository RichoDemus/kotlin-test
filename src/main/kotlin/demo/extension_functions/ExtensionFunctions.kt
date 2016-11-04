package demo.extension_functions

private fun Int.double(): Int = this * 2
// ----------------------------------------
private fun (() -> Unit).andHello() {
    this()
    println("Hello, world!")
}

private fun greet() {
    println("Hey there!")
}

private fun greetAndHello() {
    ::greet.andHello()
}

private fun ((str: String) -> Unit).wat(arg: String) {
    this(arg)
    println("$arg is weird")
}

private fun methodThatTakesString(arg: String) {
    println("Got the string $arg")
}

// ----------------------------------------
private fun String.addName() = "$this and Richo "

private fun String.separate() = this.split(" ")
private fun List<String>.join() = this.reduce { left, right -> "$left - $right" }
// ----------------------------------------
private fun <T, Y> T.map(func: (T) -> Y): Y = func.invoke(this)
// ----------------------------------------

fun main(args: Array<String>) {
    println("2 doubled is ${2.double()}")

    greetAndHello()
    ::methodThatTakesString.wat("geez")

    println("First word".addName().separate().join())

    println("hello".map { str -> str.toUpperCase() })

    val message = 1.map { number -> "Your number is $number" }
    println(message)
}
