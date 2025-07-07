package section6

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    val deferredString = async {
        delay(1000L)
        return@async "Hello, Coroutine!"
    }

    // await()는 async 블록에서 반환된 값을 기다리고, 그 값을 반환한다.
    // await() 호출 시에 코루틴의 결과를 대기하기 때문에 메인 스레드는 블락된다.
    println(deferredString.await())
}