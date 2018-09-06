package demo.async_queue

import kotlinx.coroutines.experimental.channels.ActorJob
import kotlinx.coroutines.experimental.channels.actor
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.time.delay
import java.time.Duration
import java.util.UUID

internal class AsyncQueue(initial: List<String>) {
    var running = true
    private val events = mutableListOf<String>()
    var actors = listOf<ActorJob<Unit?>>()

    init {
        initial.forEach { produce(it) }
    }

    suspend fun test(): Pair<MutableList<String>, MutableList<String>> {
        launch {
            while (running) {
                produce(UUID.randomUUID().toString())
                delay(Duration.ofSeconds(1))
            }
        }

        val first = mutableListOf<String>()
        val second = mutableListOf<String>()
        consume { event ->
            println("Listener 1: $event")
            first.add(event)
        }
        delay(Duration.ofSeconds(5))
        consume { event ->
            println("\tListener 2: $event")
            second.add(event)
        }
        return first to second
    }

    private fun consume(consumer: (String) -> Unit) {
        val actor = actor<Unit?> {
            var nextMessage = 0
            while (!channel.isClosedForSend) {
                if (events.size > nextMessage) {
                    consumer(events[nextMessage++])
                } else {
//                    delay(Duration.ofMillis(10))
                    try {
                        channel.receive()
                    } catch (_: Exception) {
                    }
                }
            }
        }
        actors += actor
    }

    private fun produce(msg: String) {
        events.add(msg)
        actors.forEach { runBlocking { it.send(null) } }
    }

    fun close() {
        actors.forEach { it.close() }
        running = false
    }

}

fun main(args: Array<String>) {
    runBlocking {
        val queue = AsyncQueue(listOf("one", "two", "three", "four", "five", "six", "seven", "eight"))
        val (first, second) = queue.test()

        launch {
            delay(Duration.ofSeconds(15))
            queue.close()
        }

        while (queue.running) {
            delay(Duration.ofSeconds(1))
        }

        assert(first == second) { "Not equal" }
    }
}