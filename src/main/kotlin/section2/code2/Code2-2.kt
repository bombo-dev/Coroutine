package section2.code2

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// Debugging VM arguments -Dkotlinx.coroutines.debug
// CoroutineName은 Coroutine Context의 하위 인터페이스 구현 객체로 CoroutineContext.Element에 해당한다.
fun main() = runBlocking<Unit>(CoroutineName("runBlocking 코루틴")) {
    println("[${Thread.currentThread().name}] Hello, Coroutines[runBlocking]!")

    launch(CoroutineName("Launch 코루틴")) {
        println("[${Thread.currentThread().name}] Hello, Coroutines[Launch]!")
    }
    // 아래 코드에 대한 사용이 가능하나, runBlocking 안에서 다시 runBlocking을 사용하면 상위에 있는 가상 스레드에 대해서 블로킹을 한다.
//    runBlocking {
//        println("[${Thread.currentThread().name}] Hello, Coroutines[runBlocking2]!")
//    }
}