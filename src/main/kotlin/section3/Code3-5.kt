package section3

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep

/**
 * IO Dispatcher의 limitedParallelism은 Default와 달리 최상위 부모 ThreadPool의 잉여 스레드에서 제한된 스레드를 점유한다.
 * 근데, 이럴거면 IO Dispatcher를 왜 쓰지? 특정 작업을 위해서 별도로 할당이 필요하다면
 * 그냥 만들어주는게 오히려 나을 수도 있겠는데.. 그래도 할당된 자원안에서 해결하려고 시도한거겠지.
 */
fun main() = runBlocking<Unit> {
    val ioBoundedDispatchers = Dispatchers.IO.limitedParallelism(2)
    repeat(4) {
        launch(ioBoundedDispatchers) {
            println("[${Thread.currentThread().name}] CPU 바운드 작업 $it")
            sleep(1000)
        }
    }
}