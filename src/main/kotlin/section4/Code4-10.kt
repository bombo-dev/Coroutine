package section4

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep

fun main() = runBlocking<Unit> {
    val longJob = launch(Dispatchers.Default) {
        sleep(1000L)
        println("longJob 코루틴 로직")
    }
    longJob.cancel() // 코루틴 취소 -> 명확하게는 취소 플래그를 날림.
    println("코루틴 취소 후 실행되어야 하는 로직")
}