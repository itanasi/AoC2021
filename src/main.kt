import java.io.File


fun main() {
    println("Hello World")
    val filename = "input"
    val lines = File(filename).readLines()  // returns List<String>
    var horiz = 0
    var depth = 0
    for (line in lines){
        val parts = line.split(" ")
        val direction = parts[0]
        val value = parts[1]
        if (direction == "forward")
            horiz += value.toInt()
        if (direction == "down")
            depth += value.toInt()
        if (direction == "up")
            depth -= value.toInt()
    }
    println("horiz: $horiz")
    println("depth: $depth")
    val mult = horiz * depth
    println("mult: $mult")
}