package section11

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

fun main() {
    var count = 0
    val mutex = Mutex()
    runBlocking {
        repeat(10000) {
            mutex.withLock {
                count++
            }
        }
    }
    println("count = $count")
}