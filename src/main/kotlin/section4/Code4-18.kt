package section4

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        delay(1000L)
    }
    printCoroutineInfo(job)
}

fun printCoroutineInfo(job: Job) {
    println("Coroutine Info: $job")
    println("Is Active: ${job.isActive}")
    println("Is Completed: ${job.isCompleted}")
    println("Is Cancelled: ${job.isCancelled}")
}