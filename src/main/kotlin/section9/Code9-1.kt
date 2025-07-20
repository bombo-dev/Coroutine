package section9

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch(CoroutineName("Parent Coroutine")) {
        launch(CoroutineName("Child Coroutine 1") + Job()) {
            launch(CoroutineName("Child Coroutine 3")) {
                throw Exception()
            }

            delay(100L)
            println("Child Coroutine1 are complete")
        }

        // 단, 예외 전파 뿐만이 아니라 취소 전파까지 제한된다.
        launch(CoroutineName("Child Coroutine 2")) {
            delay(100L)
            println("Child Coroutine2 are complete")
        }
    }
    delay(500L)
}