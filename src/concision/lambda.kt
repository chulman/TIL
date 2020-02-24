package concision

fun main() {
    var printMessage = { message: String -> println(message) }

    printMessage("hello world ")
    printMessage("hello world 2")

    val printNoArgsMessage = { println("hello world") }

    printNoArgsMessage()

    val sum = { X: Int, Y: Int -> X + Y }

    println(sum(2, 3))

    val sum2: (Int, Int) -> Int = { x, y -> x + y }

    println(sum2(3, 5))

    fun downloadData(url: String, completion: () -> Unit) {
        println("$url....")
        completion()
    }

    downloadData("stream", printNoArgsMessage)
}


