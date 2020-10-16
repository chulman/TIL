package function.higher_order

fun operation(): (Int) -> Int {                                     // 1 고차 함수 선언
    return ::square                                                 // 유효한 scope 내 선언 된 함수를 리턴할 수 있다.
}

fun square(x: Int) = x * x                                          // 2 매칭될 수 있는 함수 선언

fun main() {
    val func = operation()                                          // 3 함수를 변수로 전달 받을 수 있다.
    println(func(2))                                                // 4. 변수로 함수 호출
}
