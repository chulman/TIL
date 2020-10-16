package collection

fun main() {
    val lists = listOf(1,2,3,4,5)

    /**
     * filter
     */
//    var positive = lists.filter { i -> i > 0 }
    val positive = lists.filter { it > 0 }
    println(positive)

    /**
     * map
     */
    val double = lists.map { it * 2 }
    println(double)

    /**
     * any - 원소중 어떤 하나라도 체크되면
     */
//    val anyNumber = lists.any { it < 0 }
    val isAny = lists.any { it > 0 }
    println(isAny)

    /**
     * all - 모든 원소가 predicate 에 일치하
     */
    val isAll = lists.all { it < 0 }
    println(isAll)

    /**
     * none - all 반대 : 모든 조건이 일치하지 않으면
     */
    val isNone = lists.none { it < 0 }
    println(isNone)

    /**
     * find - 가장 빨리 찾는 원소 Return
     */
    val findNumber = lists.find { it < 3 }
    println(findNumber)

    /**
     * findLast
     */
    val findLastNumber = lists.findLast { it < 3 }
    println(findLastNumber)

    /**
     * first, last, firstOrNull, lastOrNull
     */
    val firstNumber = lists.first()
    println(firstNumber)

    /**
     * count
     */
    val count = lists.count()
    println("count = $count")

    /**
     * groupBy : 람다에 해당하는 키값을 토대로 일치하는 value를 리스트로
     * associateBy: 람다에 해당하는 키값을 토대로 일치하는 value를 1개만 / 특이점은 unique 하지않으면 마지막 원소가 기록됨
     */
    val cities = mutableListOf(
            City("seoul", "1"),
            City("tokyo", "2"),
            City("busan", "1")
    )

    val groupByCode = cities.groupBy { it.code }
    println(groupByCode)

    val assignedByCode = cities.associateBy { it.code }
    println(assignedByCode)

    /**
     * partition - 주어진 조건에 따라 pair로 구분한다.
     */
    val partition = lists.partition { it > 2 }
//    println(partition)
    println("partition first = ${partition.first}, second = ${partition.second}")

    val (positives, negatives) = lists.partition { it > 0 }
    println("positives = $positives, negatives = $negatives")

    /**
     * flatmap - 각 요소를 Iterable 가능하도록 변환하며, 단일 목록으로 변환한다.
     */
    val flatmap = lists.flatMap { listOf(it, it - 1, it + 1) }
    println(flatmap)

    /**
     * min , max
     */
    val minNumber = lists.min()
    val maxNumber = lists.max()

    //    val maxByNumber = lists.maxBy { it < 5 }  // 조건에 해당하는 가장 첫번 째 원소에 의해 찾아지나.?
    val maxByNumber = lists.maxBy { it }

    println("$minNumber, $maxNumber, $maxByNumber")

    /**
     * sort
     */
    val sorted = lists.sorted()

    val sortedBy= cities.sortedBy { it.name } // comparable 을 특정할 수 없기에 sorted는 존재하지 않음
    println(sortedBy)

    cities.sortBy { it.code }   // 리턴 값 없음 Unit
    println(cities)

    /**
     * map of access - python이랑 유사함
     */
    val mapOfName = mapOf(
            "tim" to 1, "ch" to 2, "kim" to 3
    )

    val value1 = mapOfName["ch"]
    val value2 = mapOfName["kim"]
    val value3 = mapOfName["none"]
    val value4 = mapOfName["none"] ?: "none"

    println("$value1, $value2, $value3, $value4")

    val mapWithDefault = mapOfName.withDefault { it.length }
    println(mapWithDefault.getValue("none"))
    println(mapWithDefault.getOrDefault("none", 0))

//    mapOfName.getValue("none")    // noSuchElement exception 발생함


    /**
     * zip
     */
    val A = listOf("a", "b", "c")
    val B = listOf(1, 2, 3, 4)

    println(A zip B)    // pair 로 리턴 (partition과 흡사한)
    println(A.zip(B) { a, b -> "$a$b" })    //infix notation 사용하여 다른 결과로 리턴할 수 있다.

    println(A.zipWithNext { a, b -> "$a$b" })   // 원소 내 다음 element와 zip

    /**
     * getOrElse : optional 대체 가능
     */

//    val checkSixOrGet = lists.getOrElse(6) {6}
    val checkSixOrGet = lists.getOrElse(6, defaultValue = {6})
    println(checkSixOrGet)

    val map = mutableMapOf<String, Int?>()
    println(map.getOrElse("x") { 1 })       // return 1

    map["x"] = 3
    println(map.getOrElse("x") { 1 })       // return 3

    map["x"] = null
    println(map.getOrElse("x") { 1 })   // return 1
}


data class City(val name: String, val code: String)
