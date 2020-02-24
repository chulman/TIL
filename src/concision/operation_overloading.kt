package concision

fun main() {
//    println(Point(1, 2).plus(Point(2, 3)))
    println(Point(1, 2) + Point(2, 3))
    println(Point(1, 2) - Point(2, 3))
}


//데이터 클래스(Data class)는 데이터 보관 목적으로 만든 클래스
//데이터 클래스는 프로퍼티에 대한 toString(), hashCode(), equals(), copy() 메소드를 자동으로 만든다
// 그래서 boilerplate code 구현하지 않아도 됨.
data class Point(val x: Int, val y: Int)


// 연산자 오버로딩이 가능한 함수 plus, minus, times(*), div, rem(%)
operator fun Point.plus(other: Point): Point {
    return Point(x + other.x, y + other.y)
}


operator fun Point.minus(other: Point): Point {
    return Point(x - other.x, y - other.y)
}
