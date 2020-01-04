package foundation

fun main(args: Array<String>): Unit {

    var ch: Char = 'A'

    println(ch)
    println(ch.toInt())

    //unicode 16진수
    // -34xxx ~ 34xx
    ch = '\uAC00';

    //0 ~ 65535
    println(ch.toInt())
    println('바'.toInt())

    var s: String = "kotlin"
    println(s)
    //배열로 바로 접근 가능
    println(s[5])

    val number = 123
    s += number

    val aa = 100
    var bb = 200
    var c = "sss"
    // 문자 안에 $변수명이 있으면 값을 의미한다.
    println("a는 $aa")
    println("b는 $bb  $c")

}