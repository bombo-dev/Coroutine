package section4

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()

    val longJob = launch(Dispatchers.Default) {
        repeat(10) {
            delay(1000L)
            println("[${getElapsedTime(startTime)}] Task from DefaultDispatcher: $it")
        }
    }

    delay(2500L)
    longJob.cancel() // 코루틴 취소
}

fun getElapsedTime(startTime: Long): String {
    val elapsedTime = System.currentTimeMillis() - startTime
    return "경과 시간: ${elapsedTime}밀리초"
}