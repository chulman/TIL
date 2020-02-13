package concision

fun main() {
    println(lastChar("Kotlin"))
    println(firstChar("Swift1234"))

    // 간결성 1) 기존 정의된 함수를 확장할 수 있다.
    println("Kotlin".lastCharToString())
    println("Swift1234".firstCharToString())

    // 확장함수보단 멤버함수가 우선순위가 눞다.
    println(Test().get())
}

// 일반 함수
fun lastChar(s: String): Char {
    return s[s.length - 1]
}

// 일반 함수
fun firstChar(s: String): Char {
    return s[0]
}

fun String.lastCharToString(): Char {
    return this[this.length - 1]
}

fun String.firstCharToString(): Char {
    return this[0]
}


class Test {
    fun get(): String {
        return "1"
    }

    fun Test.get(): String {
        return "2"
    }
}