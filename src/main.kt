import java.io.File
import javax.print.DocFlavor
import kotlin.math.pow


fun binToDec(input: String): Int
{
    var dec = 0
    for (bit in 0..input.length-1){
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
    var count = MutableList(length) {i -> 0}
    var prefix = ""
    var o2 = ""
    for (bit in 0..length-1) {
        val numLines = lines.filter{prefix == it.take(bit)}.count()
        println(numLines)
        if (numLines == 1){
            o2 = lines.filter{prefix == it.take(bit)}.first()
            break
        }
        for (line in lines.filter{prefix == it.take(bit)}){
            count[bit] = count[bit] + line[bit].toString().toInt()
        }
        if(count[bit].toFloat() >= numLines.toFloat()/2f){
            prefix += "1"
        }else{
            prefix += "0"
        }
        println("count: $count")
        println("prefix: $prefix")
        o2 = prefix
    }
    println("o2: $o2")
    val o2_dec = binToDec(o2)
    println("o2_dec: $o2_dec")

    count = MutableList(length) {i -> 0}
    prefix = ""
    var co2 = ""
    for (bit in 0..length-1) {
        val numLines = lines.filter{prefix == it.take(bit)}.count()
        println(numLines)
        if (numLines == 1){
            co2 = lines.filter{prefix == it.take(bit)}.first()
            break
        }
        for (line in lines.filter{prefix == it.take(bit)}){
            count[bit] = count[bit] + line[bit].toString().toInt()
        }
        if(count[bit].toFloat() < numLines.toFloat()/2f){
            prefix += "1"
        }else{
            prefix += "0"
        }
        println("count: $count")
        println("prefix: $prefix")
        co2 = prefix
    }
    println("co2: $co2")
    val co2_dec = binToDec(co2)
    println("o2_dec: $o2_dec  co2_dec: $co2_dec")
    val power = o2_dec * co2_dec
    println("power: $power")
}