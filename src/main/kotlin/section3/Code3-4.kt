package section3

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep

/**
 * limitedParallelism을 통해 Default 공유 스레드 풀에서도 특정 스레드만 점유하도록 제한 할 수 있다.
 */
fun main() = runBlocking<Unit> {
    val cpuBoundedDispatchers = Dispatchers.Default.limitedParallelism(2)
    repeat(4) {
        launch(cpuBoundedDispatchers) {
            println("[${Thread.currentThread().name}] CPU 바운드 작업 $it")
            sleep(1000)
        }
    }
}