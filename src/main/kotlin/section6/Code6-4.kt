package section6

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import section4.getElapsedTime

fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()
    // 단순 스레드만 교체
    val String = withContext(Dispatchers.IO) {
        delay(1000L)
        "Hello, Coroutine!"
    }

    val String2 = withContext(Dispatchers.IO) {
        delay(500L)
        "Hello, Coroutine 2!"
    }

    // 별도 코루틴을 생성하여 병렬로 처리하는 것이 아니라 스레드를 교체해서 순차적으로 실행하므로, 병렬 처리 X

    val results = listOf(String, String2)
    println("time taken: ${getElapsedTime(startTime)} ms") // 1519ms
}