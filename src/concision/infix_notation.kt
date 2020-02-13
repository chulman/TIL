package concision

//함수는 중위 표기법을 통해 호출될 수 있다.
/** 조건
 *  1. 멤버함수 또는 확장 함수여야 한다.
 *  2. 하나의 파라미터를 가져야 한다.
 *  3. infix 키워드를 가져야 한다.
 */
fun main() {
    println("1234".isMoreThanArgs(2))
    // dot 대신에 공백을 사용한다.
    println("1234" isMoreThanArgsWithInfix 3)
}


fun String.isMoreThanArgs(args: Long): Boolean {
    return this.toLong() > args
}

infix fun String.isMoreThanArgsWithInfix(args: Long): Boolean {
    return this.toLong() > args
}
