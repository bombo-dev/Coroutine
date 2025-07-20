package section9

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { context, throwable ->
        println("[예외 발생] $throwable")
    }

    val exceptionHandler2 = CoroutineExceptionHandler { context, throwable ->
        println("[예외 발생2] $throwable")
    }

    // CoroutineExceptionHandler는 CoroutineContext의 구성요소이다.
    CoroutineScope(exceptionHandler)
        .launch(CoroutineName("Coroutine1") + exceptionHandler2) {
            launch(CoroutineName("Coroutine2")) {
                throw Exception("Coroutine2 예외 발생")
            }
        }

    delay(500L)
}