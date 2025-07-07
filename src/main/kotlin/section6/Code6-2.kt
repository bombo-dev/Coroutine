package section6

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import section4.getElapsedTime

fun main() = runBlocking<Unit> {

    val startTime = System.currentTimeMillis()

    val deferredString = async {
        delay(1000L)
        return@async "Hello, Coroutine!"
    }

    println(deferredString.await())
    println("Time taken: ${getElapsedTime(startTime)} ms") // 1000ms

    val deferredInt = async {
        delay(500L)
        return@async 42
    }

    println(deferredInt.await())
    println("Time taken: ${getElapsedTime(startTime)} ms") // 1500ms
    // 위 결과와 같이 await()을 호출하면 코루틴을 기다리며 메인스레드의 다음 로직을 호출하지 않으므로 병목이 생긴다.
}