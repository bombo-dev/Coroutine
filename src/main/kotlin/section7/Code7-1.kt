package section7

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking


// Coroutine Context 내부는 Map<Key, Element> 형태로 구성
// 기존 Map의 plus 연산자 처럼 기존 Key의 값을 새로운 Value로 대체하거나, 추가하는 형태로 컨텍스트 내부의 값을 구성

/**
 * public operator fun plus(context: CoroutineContext): CoroutineContext =
 *         if (context === EmptyCoroutineContext) this else // fast path -- avoid lambda creation
 *             context.fold(this) { acc, element ->
 *                 val removed = acc.minusKey(element.key)
 *                 if (removed === EmptyCoroutineContext) element else {
 *                     // make sure interceptor is always last in the context (and thus is fast to get when present)
 *                     val interceptor = removed[ContinuationInterceptor]
 *                     if (interceptor == null) CombinedContext(removed, element) else {
 *                         val left = removed.minusKey(ContinuationInterceptor)
 *                         if (left === EmptyCoroutineContext) CombinedContext(element, interceptor) else
 *                             CombinedContext(CombinedContext(left, element), interceptor)
 *                     }
 *                 }
 *             }
 *
 *     /**
 *      * Returns a context containing elements from this context, but without an element with
 *      * the specified [key].
 *      */
 *     public fun minusKey(key: Key<*>): CoroutineContext
 */

fun main() = runBlocking<Unit> {
    val coroutineContext = newSingleThreadContext("Thread-1") + CoroutineName("MyCoroutine")

    launch(coroutineContext) {
        println("launch coroutine")
    }
}