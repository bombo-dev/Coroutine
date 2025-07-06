package section2.code1

import kotlinx.coroutines.runBlocking

// run + Blocking 상위 스레드를 블로킹
// runBlocking 안에서는 launch를 통해서 스레드를 만들어야 한다.
fun main() = runBlocking {
    println("Hello, Coroutines!")
}