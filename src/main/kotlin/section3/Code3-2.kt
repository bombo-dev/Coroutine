package section3

import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep

val multiThreadCoroutineDispatcher = newFixedThreadPoolContext(2, "SingleThreadContext")

/**
 * runBlocking 내부 코드 중 일부.
 *
 * val eventLoop: EventLoop?
 *     val newContext: CoroutineContext
 *     if (contextInterceptor == null) {
 *         // create or use private event loop if no dispatcher is specified
 *         eventLoop = ThreadLocalEventLoop.eventLoop
 *         newContext = GlobalScope.newCoroutineContext(context + eventLoop)
 *     } else {
 *         // See if context's interceptor is an event loop that we shall use (to support TestContext)
 *         // or take an existing thread-local event loop if present to avoid blocking it (but don't create one)
 *         eventLoop = (contextInterceptor as? EventLoop)?.takeIf { it.shouldBeProcessedFromContext() }
 *             ?: ThreadLocalEventLoop.currentOrNull()
 *         newContext = GlobalScope.newCoroutineContext(context)
 *     }
 */

// 스레드 개수만큼 할당 받아서 실행 이벤트 루프에서 사용가능한 스레드 탐색
fun main() = runBlocking<Unit> {
    launch(multiThreadCoroutineDispatcher) {
        println("[${Thread.currentThread().name}] 실행")
        sleep(1000)
    }
    launch(multiThreadCoroutineDispatcher) {
        println("[${Thread.currentThread().name}] 실행")
    }
    launch(multiThreadCoroutineDispatcher) {
        println("[${Thread.currentThread().name}] 실행")
    }
}