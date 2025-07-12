package section8

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

/**
 * CoroutineScope는 CoroutineContext를 가지는 Wrapper 클래스이다.
 * class customCoroutineScope: CoroutineScope {
 *   override val coroutineContext: CoroutineContext = Job() + Dispatchers.IO
 * }
 *
 * 위와 같이 Delegate로 CoroutineScope를 커스터 마이징 할 수 있다.
 */
fun main() {
    val newScope = CoroutineScope(CoroutineName("MyCoroutine") + Dispatchers.IO)
    newScope.launch(context = CoroutineName("LaunchCoroutine")) {
        println("newScope의 coroutineContext : ${newScope.coroutineContext}")
        println("launch 코루틴의 coroutineContext : ${this.coroutineContext}")
        println("launch 코루틴의 parentJob은 newScope 인가: ${this.coroutineContext[Job]?.parent == newScope.coroutineContext[Job]}")
    }
    sleep(500L)
}