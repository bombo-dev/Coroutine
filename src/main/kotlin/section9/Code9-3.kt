package section9

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val coroutineScope = CoroutineScope(SupervisorJob())
    coroutineScope.apply {
        launch(CoroutineName("Coroutine 1")) {
            launch(CoroutineName("Coroutine 3")) {
                throw Exception("예외 발생")
            }
            delay(1000L)
            println("Coroutine 1 is completed")
        }
    }
    launch(CoroutineName("Coroutine 2")) {
        delay(1000L)
        println("Coroutine 2 is completed")
    }
}