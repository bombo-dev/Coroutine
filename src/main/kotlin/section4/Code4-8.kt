package section4

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()
    val lazyJob = launch(start = CoroutineStart.LAZY) {
        println("[${Thread.currentThread().name}] 작업 1 시작")
    }
    delay(1000L)
//    lazyJob.join() // join을 해도 실행 됨.
    lazyJob.start() // 지연이 되었다가 호출 시에 호출 되도록 명시적인 코루틴 실행을 요청함.
}