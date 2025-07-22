package section12

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class RepeatAddUseCase(
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun add(repeatTime: Int): Int = withContext(dispatcher) {
        var result = 0
        repeat(repeatTime) {
            result += 1
        }
        result
    }

    suspend fun addWithDelay(repeatTime: Int): Int = withContext(dispatcher) {
        var result = 0
        repeat(repeatTime) {
            delay(100L)
            result += 1
        }
        result
    }
}