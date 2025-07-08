package section7

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val coroutineName = CoroutineName("MyCoroutine")
    val coroutineDispatcher = Dispatchers.IO
    val job = Job()

    val coroutineContext = coroutineDispatcher + coroutineName + job
    val deletedNameCoroutineContext = coroutineContext.minusKey(coroutineName.key)

    println("Coroutine Context: $coroutineContext") // 불변 보장
    println("Deleted Name Coroutine Context: $deletedNameCoroutineContext")
}