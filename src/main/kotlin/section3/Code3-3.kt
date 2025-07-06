package section3

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 *runBlocking<Unit> {
 *         launch(Dispatchers.IO) {
 *             println("[${Thread.currentThread().name}] IO 작업 1")
 *         }
 *         launch(Dispatchers.IO) {
 *             println("[${Thread.currentThread().name}] IO 작업 2")
 *         }
 *         launch(Dispatchers.IO) {
 *             println("[${Thread.currentThread().name}] IO 작업 3")
 *         }
 *     }
 * 아래 코드는 위와 동일하다.
 * 부모 코루틴을 상속하여 Dispatcher를 공유 할 수 있다.
 */
fun main() = runBlocking<Unit> {
    launch(Dispatchers.IO) {
        launch {
            println("[${Thread.currentThread().name}] IO 작업 1")
        }
        launch {
            println("[${Thread.currentThread().name}] IO 작업 2")
        }
        launch {
            println("[${Thread.currentThread().name}] IO 작업 3")
        }
    }
}