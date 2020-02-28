package foundation

//generic class
class MutableStack<E>(vararg items: E) {

    private val elements = items.toMutableList()

    fun push(element: E) = elements.add(element)

    fun peek(): E = elements.last()

    fun pop(): E = elements.removeAt(elements.size - 1)

    fun isEmpty() = elements.isEmpty()

    fun size() = elements.size

    override fun toString() = "MutableStack(${elements.joinToString()})"
}

//generic function
fun <E> mutableStackOf(vararg elements: E) = MutableStack(*elements)

fun main() {
    println(MutableStack<String>().toString())
    //compile 단계에서 제네릭 타입에 대해 추론이 가능하기 떄문에 <> 키워드를 사용할 필요가 없다.
    println(mutableStackOf("2","3","4","5").toString())
}