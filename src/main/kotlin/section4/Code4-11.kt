package section4

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep

fun main() = runBlocking<Unit> {
    val longJob = launch(Dispatchers.Default) {
        sleep(1000L)
        println("longJob 코루틴 로직")
    }
    longJob.cancelAndJoin() // 코루틴에 취소 요청을 날리고 취소 작업 완료 대기
    println("코루틴 취소 후 실행되어야 하는 로직")
}