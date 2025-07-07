package section6

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import section4.getElapsedTime

fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()

    val deferredString = async {
        delay(1000L)
        "Hello, Coroutine!"
    }

    val deferredInt = async {
        delay(1000L)
        42
    }

    val deferredString2 = async {
        delay(1000L)
        "Hello, Coroutine 2!"
    }

    // 모든 코루틴의 async() 호출 이후 await()을 호출하므로 병렬 실행
    println(deferredString.await())
    println(deferredInt.await())
    val result = awaitAll(deferredString, deferredString2) // 같은 객체면 같은 타입을 List로 반환
//    val result2 = awaitAll(deferredString, deferredInt) // 다른 객체면 List<Comparable<*>> 을 반환
//    val awaitAll = listOf(deferredString, deferredString2).awaitAll() // 다음과 같이 Deferred<T>를 가진 List에 대해 awaitAll 확장함수가 정의되어 있음.
    /**
     * public suspend fun <T> Collection<Deferred<T>>.awaitAll(): List<T> =
     *     if (isEmpty()) emptyList() else AwaitAll(toTypedArray()).await()
     */
    println("Time taken: ${getElapsedTime(startTime)}") // 1011ms
}