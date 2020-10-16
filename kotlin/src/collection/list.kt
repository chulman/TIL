package collection

val systemUsers: MutableList<Int> = mutableListOf(1, 2, 3)        // 1 mutable list : 변경 가능 리스트
val sudoers: List<Int> = systemUsers                              // 2 list로 캐스팅하면 read-only

fun addSudoer(newUser: Int) {                                     // 3
    systemUsers.add(newUser)
}

fun getSysSudoers(): List<Int> {                                  // 4
    return sudoers
}

fun main() {
    addSudoer(4)                                                  // 5
    println("Tot sudoers: ${getSysSudoers().size}")               // 6
    getSysSudoers().forEach {                                     // 7
        i -> println("Some useful info on user $i")
    }
    // getSysSudoers().add(5) <- Error!                           // 8
}
