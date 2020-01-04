package foundation

//unit is void
fun main(args: Array<String>): Unit {

    println("hello kotlin!!")

    //표현식 (expression), kotlin 에서는 해당 표현식에서 에러가 나지 않음.
    34 + 23 - 100

    //var(Variable) : mutable
    var sum: Int = 100
    print(sum)

    //var와 val의 차이점 : val 은 final 을 선언한 것과 같다.(Immutable)
    val a: Int = 120

}