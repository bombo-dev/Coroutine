package section9

import kotlinx.coroutines.CancellationException
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

    // CancellationException은 부모 코루틴으로 예외를 전파하지 않는다.
    // exceptionHandler는 부모 코루틴에 걸려있는 ExceptionHandler가 예외를 잡아서 처리하기 때문에 예외 메시지 또한 노출되지 않는다.
    // CancellationException은 코루틴에 대해서 취소(cancel) 요청을 보낼 때 사용된다.
    CoroutineScope(exceptionHandler)
        .launch(CoroutineName("Coroutine1") + exceptionHandler2) {
            launch(CoroutineName("Coroutine2")) {
                throw CancellationException("Coroutine2 예외 발생")
            }
        }

    delay(500L)
}