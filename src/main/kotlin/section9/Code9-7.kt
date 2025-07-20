package section9

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val coroutineScope = CoroutineScope(coroutineContext)

    // 코루틴의 생성 시점과 실행 시점의 예외에 대한 처리는 명확하게 분리해야 한다.
    // 코루틴의 실행 시점에 예외를 생성 시점에 잡더라도 실행 시점에서의 스레드와 생성을 담당하는 스레드가 명확하게 분리되어있기 때문에
    // 외부의 쓰레드는 내부에서 발생한 쓰레드를 try-catch로 처리 할 수 없다.
        coroutineScope
            .launch(CoroutineName("Coroutine1")) {
                try {
                    launch(CoroutineName("Coroutine2")) {
                        try {
                            throw Exception("Coroutine2 예외 발생")
                        } catch (e: Exception) {
                            println(e.message)
                            throw Exception("Coroutine2 예외 재 전파")
                        }
                    }
                } catch(e: Exception) {
                    println(e.message)
                }
            }
    delay(500L)
}
