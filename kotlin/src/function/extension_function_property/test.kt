package function.extension_function_property

data class Item(val name: String, val price: Float)                                   // 1

data class Order(val items: Collection<Item>)

fun Order.maxPricedItemValue(): Float = this.items.maxBy { it.price }?.price ?: 0F    // 2 엘비스 연산자를 이용한 확장함수
fun Order.maxPricedItemName() = this.items.maxBy { it.price }?.name ?: "NO_PRODUCTS"

val Order.commaDelimitedItemNames: String                                             // 3 확장 property
    get() = items.map { it.name }.joinToString()

fun main() {
    val order = Order(listOf(
            Item("Bread", 25.0F),
            Item("Wine", 29.0F),
            Item("Water", 12.0F)))

    println("Max priced item name: ${order.maxPricedItemName()}")
    println("Max priced item value: ${order.maxPricedItemValue()}")
    println("Items: ${order.commaDelimitedItemNames}")

}
