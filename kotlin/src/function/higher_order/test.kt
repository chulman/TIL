package function.higher_order

// 고차 함수란 다른 함수를 인자로 받거나 함수를 반환하는 함수이다.
fun main() {
    /**
     * 1. 함수 타입
     * 표기법은 다음과 같다 <p>파라미터 타입 -> 반환 타입</p>
     */
    // sum 과 action 은 컴파일 시점에 함수 타입임을 추론한다.
    val sum = { x: Int, y: Int -> x + y }
    val action = { println(sum) }


    performRequest("http://www") { code, content -> println("code = $code, content = $content") }
}

/**
 * 2. 함수타입에 파라미터 이름을 지정할 수 있다.
 */
fun performRequest(url: String, callback: (code: Int, content: String) -> Unit) {
    /**
     * 3. 인자로 받은 함수 호출
     */
    callback(2, url)
}
