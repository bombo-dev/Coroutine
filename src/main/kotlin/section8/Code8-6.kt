package section8

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val newJob = Job()
    launch(CoroutineName("Coroutine1") + newJob) {
        launch(CoroutineName("Coroutine3")) {
            delay(100L)
            println("${Thread.currentThread().name} 코루틴 실행")
        }
        launch(CoroutineName("Coroutine4")) {
            delay(100L)
            println("${Thread.currentThread().name} 코루틴 실행")
        }
    }

    launch(CoroutineName("Coroutine2") + newJob) {
        // 내부에서 새로운 Job()을 생성하면 별도의 Job으로 분리된다.
        launch(CoroutineName("Coroutine5") + Job()) {
            delay(100L)
            println("${this.coroutineContext[CoroutineName]} 코루틴 실행")
        }
    }

    delay(100L)
    newJob.cancel()
    delay(1000L)
}