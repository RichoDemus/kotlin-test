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
// How to turn this into a function that applies to Any?
private fun String.map(func: (String) -> String): String = func.invoke(this)

// private fun <T> Any.map(func: (T)->Any):Any = func.invoke((T)this)


fun main(args: Array<String>) {
    println("2 doubled is ${2.double()}")

    greetAndHello()
    ::methodThatTakesString.wat("geez")

    println("First word".addName().separate().join())

    println("hello".map { str -> str.toUpperCase() })
}
