package foundation

fun main() {
    printMessage("hello")
    println(sum(2,3))
    multiArgsFunc("1", "2")
}

// unit은 void와 같음.
fun printMessage(message: String): Unit {
    println(message)
}

// 단일 표현 함수
fun sum(x:Int, y:Int) = x + y

// 런타임 시점에 varargs 키워드는 단지 배열
fun multiArgsFunc(vararg args: String) {
    for (a in args) println(a)
}




