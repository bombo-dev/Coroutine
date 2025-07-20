package section9

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val supervisorJob = SupervisorJob(parent = this.coroutineContext[Job])
    launch(supervisorJob + CoroutineName("Coroutine 1")) {
        launch(CoroutineName("Coroutine 3")) {
            throw Exception("예외 발생")
        }
        delay(1000L)
        println("Coroutine 1 is completed")
    }
    launch(supervisorJob + CoroutineName("Coroutine 2")) {
        delay(1000L)
        println("Coroutine 2 is completed")
    }

    // 새롭게 생성 된 Job은 scope 외부에서도 사용 될 수 있으므로, coroutineScope 처럼 구조적 컨커런시가 지켜지지 않아 완료 처리를 해주어야 한다.
    supervisorJob.complete()
}