package demo.async_queue

import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.sync.Mutex
import kotlinx.coroutines.experimental.time.delay
import java.time.Duration
import java.util.UUID

internal class AsyncQueue(initial: List<String>) {
    var running = true
    private val events = mutableListOf<String>()
    val mutex = Mutex()

    init {
        initial.forEach { produce(it) }
    }

    suspend fun test() {


        launch {
            while (running) {
                produce(UUID.randomUUID().toString())
                delay(Duration.ofSeconds(1))
            }
        }





        consume { event -> println("event: $event") }


    }

    private fun consume(consumer: (String?) -> Unit) {
        val scope = launch {
            var nextMessage = 0
            while (running) {
                if (events.size > nextMessage) {
                    consumer(events[nextMessage++])
                } else {
//                    delay(Duration.ofMillis(10))
                    println("awaiting lock")
//                    mutex.
                    mutex.lock(events)
//                    if (!mutex.isLocked){
//                        println("locking...")
//                    }
                }
            }
        }
    }

    private fun produce(msg: String) {
        events.add(msg)
        if (mutex.isLocked) mutex.unlock(events)
    }


}

fun main(args: Array<String>) {
    runBlocking {
        val queue = AsyncQueue(listOf("one", "two", "three"))
        queue.test()

        while (queue.running) {
            delay(Duration.ofSeconds(1))
        }
    }
}