package function.lambda

// All examples create a function object that performs upper-casing.
// So it's a function from String to String

val upperCase1: (String) -> String = { str: String -> str.toUpperCase() } // 1 모든 곳에 명시적인 타입이 명시된 람다

val upperCase2: (String) -> String = { str -> str.toUpperCase() }         // 2 람다 내부 유형 추론

val upperCase3 = { str: String -> str.toUpperCase() }                     // 3 람다 외부 유형 추론

// val upperCase4 = { str -> str.toUpperCase() }                          // 4 둘다 없으면 유형 추론을 할 수 없다.

val upperCase5: (String) -> String = { it.toUpperCase() }                 // 5 단일 매개변수 람다는 내부에서 명시하지 않고 it 변수를 사용 가능

val upperCase6: (String) -> String = String::toUpperCase                  // 6 단일 함수일 경우 메소드 참조로 구성할 수 있다.

fun main() {
    println(upperCase1("hello"))
    println(upperCase2("hello"))
    println(upperCase3("hello"))
    println(upperCase5("hello"))
    println(upperCase6("hello"))
}
