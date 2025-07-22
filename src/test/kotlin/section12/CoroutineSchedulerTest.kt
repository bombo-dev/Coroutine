package section12

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class CoroutineSchedulerTest {

    @Test
    fun coroutineSchedulerTest() {
        val testCoroutineScheduler = TestCoroutineScheduler()

        testCoroutineScheduler.advanceTimeBy(5000L)
        Assertions.assertThat(testCoroutineScheduler.currentTime).isEqualTo(5000L)

        testCoroutineScheduler.advanceTimeBy(6000L)
        Assertions.assertThat(testCoroutineScheduler.currentTime).isEqualTo(11000L)

        testCoroutineScheduler.advanceTimeBy(11000L)
        Assertions.assertThat(testCoroutineScheduler.currentTime).isEqualTo(22000L)
    }

    @Test
    fun coroutineTestDispatcherTest() {
        val standardTestDispatcher = StandardTestDispatcher()
        val coroutineScope = CoroutineScope(standardTestDispatcher)

        var result = 0

        coroutineScope.launch {
            delay(10000L)
            result = 1
            delay(20000L)
            result = 2
        }

        standardTestDispatcher.scheduler.advanceTimeBy(5000L)
        Assertions.assertThat(result).isEqualTo(0)

        standardTestDispatcher.scheduler.advanceTimeBy(6000L)
        Assertions.assertThat(result).isEqualTo(1)

        standardTestDispatcher.scheduler.advanceTimeBy(20000L)
        Assertions.assertThat(result).isEqualTo(2)
    }

    @Test
    fun coroutineTestDispatcherAdvancedUtilsTest() {
        val standardTestDispatcher = StandardTestDispatcher()
        val coroutineScope = CoroutineScope(standardTestDispatcher)

        var result = 0

        coroutineScope.launch {
            delay(10000L)
            result = 1
            delay(20000L)
            result = 2
        }

        standardTestDispatcher.scheduler.advanceUntilIdle()

        Assertions.assertThat(result).isEqualTo(2)
    }

    @Test
    fun coroutineWithTestScope() {
        val testScope = TestScope()

        var result = 0

        testScope.launch {
            delay(10000L)
            result = 1
            delay(20000L)
            result = 2
        }

        testScope.advanceUntilIdle()

        Assertions.assertThat(result).isEqualTo(2)
    }
}