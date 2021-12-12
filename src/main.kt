import java.io.File

fun main() {
    println("Hello World")
    val filename = "input"
    val lines = File(filename).readLines()  // returns List<String>

}