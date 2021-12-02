import java.io.File


fun main() {
    println("Hello World")
    val filename = "input"
    val lines = File(filename).readLines()  // returns List<String>
    // Create 3-sliding window list
    val finalwindow: MutableList<Int> = mutableListOf()
    for (i in lines.indices){
        if (i < 2){
            continue
        }
        finalwindow.add(lines[i].toInt() + lines[i-1].toInt() + lines[i-2].toInt())
    }
    var inc = 0
    var prev = 0
    for (value in finalwindow)
    {
        //println(value.toString())
        if (prev == 0){
            prev = value
            continue
        }
        if (prev < value){
            inc++
        }
        prev = value
    }
    println("increases: "+inc.toString())
}