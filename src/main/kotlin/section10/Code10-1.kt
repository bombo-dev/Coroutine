package section10

import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import section4.getElapsedTime

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    // 별도의 coroutine에서 실행되는게 아니므로 병렬 처리는 안 됨.
    // 따라서, 동기적으로 처리되는 것으로 보임.
    doSomething()
    doSomething()
    println(getElapsedTime(startTime))

    val job1 = launch {
        doSomething()
    }

    val job2 = launch {
        doSomething()
    }

    joinAll(job1, job2)
    println(getElapsedTime(startTime))
}

private suspend fun doSomething() {
    delay(1000L)
    println("doSomething")
}


// 일시중단 함수 내부에서 별도의 코루틴을 생성 할 수는 없다.
//private suspend fun parallelWork(): Job {
//    return launch {
//        delay(1000L)
//        println("doSomething")
//    }
//}