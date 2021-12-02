import java.io.File


fun main() {
    println("Hello World")
    val filename = "input"
    val lines = File(filename).readLines()  // returns List<String>
    var inc = 0
    var prev = ""
    for (line in lines)
    {
        if (line == ""){
            continue
        }
        if (prev == ""){
            prev = line
            continue
        }
        if (prev.toInt() < line.toInt()){
            inc++
        }
        prev = line
    }
    println("increases: "+inc.toString())
}