package section8

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val rootJob = coroutineContext[Job]
    println("launch를 실행하기 전에 rootJob은 parent가 null이다. 여부 : ${rootJob?.parent == null}")
    println("launch를 실행하기 전에 rootJob은 child의 수가 0이다. 여부 : ${rootJob?.children?.count()}")
    launch {
        val launchJob = coroutineContext[Job]
        println("launch를 실행하면 부모 Job의 children은 자식 Job이다. 여부 : ${rootJob?.children?.contains(launchJob)}")
        println("launch를 실행하면 자식 Job의 parent는 부모 Job이다. 여부 : ${launchJob?.parent == rootJob}")
    }

    /**
     * 코루틴은 계층화를 위해서 Coroutine Context를 상속하여 계층화 된 구조를 가지고 간다.
     * - 따라서, 자식은 늘 부모의 Coroutine Context를 가지고 가며, 만약 새로운 Coroutine Context의 Attribute를 추가하면 add operator의 동작방식대로 새로운 값으로 덮어씌운다.
     *
     * 단, Job은 launch, async 등, 코루틴을 제어하기 위해 코루틴 빌더가 Job을 늘 새로 만든다.
     * 하지만, Job 자체가 계층화 된 구조로 parent와 child를 포인터로 연결하여 계층화 된 구조를 유지한다.
     */
}