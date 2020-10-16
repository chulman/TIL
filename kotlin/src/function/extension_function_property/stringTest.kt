package function.extension_function_property

import concision.lastCharToString

fun main() {

    print("a".isLastCharEqualsA())
}

fun String.isLastCharEqualsA() : Boolean {
    return this.lastCharToString() == 'a'
}
