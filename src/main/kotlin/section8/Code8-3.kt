package section8

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import section4.getElapsedTime

/**
 * 구조화 된 코루틴 병렬처리
 */
fun main() = runBlocking<Unit> {
    val parentJob = launch {
        val startTime = System.currentTimeMillis()
        val dbs = listOf("DB1", "DB2", "DB3").map {
            async {
                delay(1000L)
                println("Fetching $it ${getElapsedTime(startTime)}")
                return@async "get $it data"
            }
        }

        val dbsData = dbs.awaitAll()
        println(dbsData)
    }

//    parentJob.cancel() 만약, 부모 코루틴에 취소 요청을 하면 취소 전파가 되어 자식 코루틴이 실행되지 않는다. BFS
}