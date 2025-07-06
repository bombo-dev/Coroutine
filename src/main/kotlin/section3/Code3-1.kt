package section3

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep

@OptIn(DelicateCoroutinesApi::class)
val singleThreadCoroutineDispatcher = newSingleThreadContext("SingleThreadContext")

// 무조건 하나의 스레드 실행
fun main() = runBlocking<Unit> {
    launch(singleThreadCoroutineDispatcher) {
        println("[${Thread.currentThread().name}] 실행")
        sleep(500)
    }
    launch(singleThreadCoroutineDispatcher) {
        println("[${Thread.currentThread().name}] 실행")
    }
}