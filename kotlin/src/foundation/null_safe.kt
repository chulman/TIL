package foundation


fun main() {
    var neverNull: String = "This Can Be Never null"
    //불가능
//    neverNull = null

    var nullable: String? = "null is allowed"

    nullable = null

    var inferredNonNull = "The compiler assumes non-null"

    // 컴파일시점에 타입을 유추할 때 값이 초기화된 변수에 대해 널이 아닌 것으로 간주
//    inferredNonNull = null


    strLength(neverNull)
    //null 이 가능한 값은 불가능
//    strLength(nullable)


    describeString(nullable)

}
fun strLength(notNull: String): Int {
    return notNull.length
}

//떄때로 코틀린은 자바와 상호작용을 위해 널 값이 필요로하게 동작한거나 작동해야 하는데 이러한 상황에 대한 대비로 널 추적을 제공한다.
fun describeString(maybeString: String?): String {
    return if (maybeString != null && maybeString.isNotEmpty()) {
        "String of length ${maybeString.length}"
    } else {
        "Empty or null string"
    }
}

