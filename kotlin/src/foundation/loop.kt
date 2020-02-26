package foundation


fun main() {
    //for loop
    val names = listOf("ray", "ted", "kim", "choi")

    for (name in names) {
        println("list , $name")
    }

    //val(value) 과 var(variable)의 차이점은 immutable 과 mutable
    var cnt = 0
    while (cnt == names.size) {
        cnt++
        println(names[cnt])
    }

    val zoo = Zoo(listOf(Animal("zebra"), Animal("lion")))

    val iter = zoo.iterator()

    while (iter.hasNext()) {
        val next = iter.next()
        println("next animal ==> ${next.name}")
    }

    for (animal in zoo) {                                   // 3
        println("Watch out, it's a ${animal.name}")
    }

}


class Animal(val name: String)

class Zoo(val animals: List<Animal>) {

    //custom iterator 정의
    //확장 함수 혹은 type으로 선언될 수 있다.
    //연산자 오버로딩을 통해 기본으로 제공되는 연산자를 오버로딩한다.(plus, minus, .. 등)
    operator fun iterator(): Iterator<Animal> {
        return animals.iterator()
    }
}