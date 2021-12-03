import java.io.File
import kotlin.math.pow

fun main() {
    println("Hello World")
    val filename = "input"
    val length = 12
    val lines = File(filename).readLines()  // returns List<String>
    var count = MutableList(length) {i -> 0}
    for (line in lines){
        for (bit in 0..length-1) {
            count[bit] = count[bit] + line[bit].toString().toInt()
        }
    }
    println("count: $count")
    val numLines = lines.size
    println("numLines: $numLines")
    var gamma = 0
    for (bit in 0..length-1){
        if (count[bit] > numLines/2){
            gamma += 2.toDouble().pow(length-bit-1).toInt()
        }
    }
    val epsilon = gamma.xor(2.toDouble().pow(length).toInt()-1)
    println("gamma: $gamma")
    println("epsilon: $epsilon")
    val power = gamma * epsilon
    println("power: $power")
}