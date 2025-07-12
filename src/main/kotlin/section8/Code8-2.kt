package section8

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import section4.getElapsedTime
import section4.printCoroutineInfo

/**
 * 자식 코루틴이 모두 실행되어야 부모 코루틴이 완벽하게 실행 완료 될 수 있다.
 */
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val parentJob = launch {
        launch {
            delay(1000L)
            println("[${getElapsedTime(startTime)}] 자식 코루틴 실행완료")
        }

        println("[${getElapsedTime(startTime)}] 부모 코루틴의 마지막 코드")
    }

    parentJob.invokeOnCompletion { // 부모 코루틴 실행완료 callback 함수
        println("[${getElapsedTime(startTime)}] 부모 코루틴 실행 완료")
    }

    delay(500L)
    println(parentJob)
    printCoroutineInfo(parentJob)
}