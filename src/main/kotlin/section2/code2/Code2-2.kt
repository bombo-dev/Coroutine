package section2.code2

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    println("[${Thread.currentThread().name}] Hello, Coroutines[runBlocking]!")

    launch {
        println("[${Thread.currentThread().name}] Hello, Coroutines[Launch]!")
    }
    // 아래 코드에 대한 사용이 가능하나, runBlocking 안에서 다시 runBlocking을 사용하면 상위에 있는 가상 스레드에 대해서 블로킹을 한다.
//    runBlocking {
//        println("[${Thread.currentThread().name}] Hello, Coroutines[runBlocking2]!")
//    }
}