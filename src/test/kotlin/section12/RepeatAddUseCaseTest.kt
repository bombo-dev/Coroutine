package section12

import kotlin.coroutines.ContinuationInterceptor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RepeatAddUseCaseTest {

    @Test
    fun add() = runBlocking<Unit> {
        val repeatAddUseCase = RepeatAddUseCase(Dispatchers.Default)

        val result = repeatAddUseCase.add(10)

        assertThat(result).isEqualTo(10)
    }

    @Test
    fun addWithDelay() = runTest {
        val testDispatcher = coroutineContext[ContinuationInterceptor] as CoroutineDispatcher
        val repeatAddUseCase = RepeatAddUseCase(testDispatcher)

        val result = repeatAddUseCase.addWithDelay(30)

        assertThat(result).isEqualTo(30)
    }
}