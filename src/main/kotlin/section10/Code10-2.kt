package section10

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import section4.getElapsedTime

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    println(searchByKeyword("테스트"))
    println(getElapsedTime(startTime))
}

private suspend fun searchByKeyword(keyword: String): List<String> {
    return coroutineScope {
        val deferredDB = async {
            searchByDB(keyword)
        }
        val deferredNetwork = async {
            searchByNetwork(keyword)
        }
        return@coroutineScope deferredDB.await() + deferredNetwork.await()
    }
}

private suspend fun searchByDB(keyword: String): List<String> {
    delay(1000L)
    return listOf("DB${keyword}1", "DB${keyword}2")
}

private suspend fun searchByNetwork(keyword: String): List<String> {
    delay(1000L)
    return listOf("NW${keyword}1", "NW${keyword}2")
}