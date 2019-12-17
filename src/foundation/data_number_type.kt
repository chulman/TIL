package foundation

fun main(args: Array<String>): Unit {
    val a: Byte = 125
    println(a)

    val b: Int = 126
    println(b)

    var c: Int = 1_000_000_00
    println(c)

    c = 0xFF_AA_77
    println(c)

    c = 0b10101010110
    println(c)

    var d: Long = 233_343_456_456
    println(d)

    c = a + b
    println(c)

    var ee:Float = 45.4f

    var dd:Double = 45.43

    ee = (ee + dd).toFloat()
    println(ee)

    //실수는 0.1을 2진수로 바꿀 때 0.10000~ 무한소수로 바뀌기 떄문에 0으로 정확하게 나누어 떨어지지 않을 수 있음.
    println(0.1f + 0.1f + 0.1f + 0.1f + 0.1f + 0.1f + 0.1f + 0.1f + 0.1f+ 0.1f)
    println(0.1 * 10)


}