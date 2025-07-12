package section8

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

/**
 * Coroutine Scope는 내부의 Job 객체에 대한 구조화 된 구조를 유지 할 수 있도록 도와준다.
 * Coroutine Scope 내부에 연결 된 Job에 대해서만 Job의 취소 전파가 이루어진다. 새로운 CoroutineScope는 다르게 동작한다.
 * 만약에, 원래 존재하던 Coroutine Scope에 또 다른 Coroutine Scope가 선언되면 해당 CoroutineScope에 상속되므로 NestedCoroutineScope도 상위 CoroutineScope에 맞게 동작한다.
 *
 * public static final void cancel(@NotNull CoroutineScope $this$cancel, @Nullable CancellationException cause) {
 *       Job var10000 = (Job)$this$cancel.getCoroutineContext().get((CoroutineContext.Key)Job.Key);
 *       if (var10000 == null) {
 *          throw new IllegalStateException(("Scope cannot be cancelled because it does not have a job: " + $this$cancel).toString());
 *       } else {
 *          Job job = var10000;
 *          job.cancel(cause);
 *       }
 *    }
 * coroutine scope는 위와 같이 내부의 Job을 찾아서, Job을 취소하는 것이고, Job은 취소 전파를 하기 떄문에 취소된다.
 */
fun main() {
    val newScope = CoroutineScope(CoroutineName("MyCoroutine") + Dispatchers.IO)
    newScope.launch(context = CoroutineName("LaunchCoroutine")) {
        delay(500L)
        println("newScope의 coroutineContext : ${newScope.coroutineContext}")
        println("launch 코루틴의 coroutineContext : ${this.coroutineContext}")
        println("launch 코루틴의 parentJob은 newScope 인가: ${this.coroutineContext[Job]?.parent == newScope.coroutineContext[Job]}")
    }

    CoroutineScope(Dispatchers.IO).launch(CoroutineName("NestedLaunchCoroutine")) {
        delay(500L)
        println("별도의 CoroutineScope는 실행된다 $this")
    }

    newScope.cancel()
    sleep(1000L)
}