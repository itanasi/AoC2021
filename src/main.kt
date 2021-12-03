import java.io.File
import kotlin.math.pow


fun binToDec(input: String): Int
{
    var dec = 0
    for (bit in input.indices){
        if (input[bit].toString() == "1"){
            dec += 2.toDouble().pow(input.length-bit-1).toInt()
        }
    }
    return dec
}

fun main() {
    println("Hello World")
    val filename = "input"
    val length = 12
    val lines = File(filename).readLines().asSequence()  // returns List<String>
    var count = MutableList(length) {0}
    var prefix = ""
    var o2 = ""
    for (bit in 0 until length) {
        val numLines = lines.filter{prefix == it.take(bit)}.count()
        println(numLines)
        if (numLines == 1){
            o2 = lines.filter{prefix == it.take(bit)}.first()
            break
        }
        for (line in lines.filter{prefix == it.take(bit)}){
            count[bit] = count[bit] + line[bit].toString().toInt()
        }
        prefix += if(count[bit].toFloat() >= numLines.toFloat()/2f){
            "1"
        }else{
            "0"
        }
        println("count: $count")
        println("prefix: $prefix")
        o2 = prefix
    }
    println("o2: $o2")
    val o2dec = binToDec(o2)

    count = MutableList(length) {0}
    prefix = ""
    var co2 = ""
    for (bit in 0 until length) {
        val numLines = lines.filter{prefix == it.take(bit)}.count()
        println(numLines)
        if (numLines == 1){
            co2 = lines.filter{prefix == it.take(bit)}.first()
            break
        }
        for (line in lines.filter{prefix == it.take(bit)}){
            count[bit] = count[bit] + line[bit].toString().toInt()
        }
        prefix += if(count[bit].toFloat() < numLines.toFloat()/2f){
            "1"
        }else{
            "0"
        }
        println("count: $count")
        println("prefix: $prefix")
        co2 = prefix
    }
    println("co2: $co2")
    val co2dec = binToDec(co2)
    println("o2dec: $o2dec  co2dec: $co2dec")
    val power = o2dec * co2dec
    println("power: $power")
}