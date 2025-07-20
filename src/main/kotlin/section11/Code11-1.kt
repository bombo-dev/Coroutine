package section11

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

fun main() {
    var count = 0
    val mutex = Mutex() // ReentrantLock은 쓰레드를 블로킹하나 Coroutine의 Mutex는 일시중단하여 스레드를 양보함.
    runBlocking {
        repeat(10000) {
            mutex.withLock {
                count++
            }
        }
    }
    println("count = $count")
}