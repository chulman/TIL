package function.higher_order

fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {  // 1 고차 함수 선언
    return operation(x, y)                                          // 2 전달받은 함수 값 리턴
}

fun sum(x: Int, y: Int) = x + y                                     // 3 전달할 함수 정의

fun main() {
    val sumResult = calculate(4, 5, ::sum)                          // 4 코틀린에서 메소드 참조 방식
    val mulResult = calculate(4, 5) { a, b -> a * b }               // 5 짧으면 람다가 더 명확할 수 있다.
    println("sumResult $sumResult, mulResult $mulResult")   // 함수의 리턴 값을 출력
}
