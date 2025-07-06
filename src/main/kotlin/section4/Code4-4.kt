package section4

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep

fun main() = runBlocking<Unit> {
    val convertedImage1 = launch(Dispatchers.Default) {
        sleep(1000L)
        println("[${Thread.currentThread().name}] 이미지 변환 작업 1")
    }

    val convertedImage2 = launch(Dispatchers.Default) {
        sleep(1000L)
        println("[${Thread.currentThread().name}] 이미지 변환 작업 2")
    }

    // join을 하면 Job이 완료될 때 까지 상위 Thread를 일시정지 시킨다. yield 한다.
    // yield의 위치를 적절히 선언해주어야, 순차 처리 시 지연 문제가 발생하지 않을 수 있으니 주의하기.
    // 만약, convertedImage1 아래로 가면, 1초 기다리고, 다시 2를 위해서 1초 기다리고 완료된다.
//    convertedImage1.join()
//    convertedImage2.join()
    joinAll(convertedImage1, convertedImage2)

    launch(Dispatchers.Default) {
        println("[${Thread.currentThread().name}] 이미지 업로드")
    }
}