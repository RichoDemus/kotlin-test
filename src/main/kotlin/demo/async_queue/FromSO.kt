import kotlinx.coroutines.experimental.asCoroutineDispatcher
import kotlinx.coroutines.experimental.channels.BroadcastChannel
import kotlinx.coroutines.experimental.channels.SubscriptionReceiveChannel
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

private val threadPool = Executors.newCachedThreadPool() as ExecutorService
val MyPool = threadPool.asCoroutineDispatcher()

fun main(args: Array<String>) {
    val packetChannel = BroadcastChannel<Packet>(1)
    (1..2).forEach {
        launch(MyPool) {
            receivePackets(it, packetChannel.openSubscription())
        }
    }
    runBlocking {
        (1..5).forEach {
            packetChannel.send(Packet(it))
            delay(100)
        }
    }
    threadPool.shutdown()
}

suspend fun receivePackets(index: Int, packetChannel: SubscriptionReceiveChannel<Packet>) {
    while (true) {
        println("Receiver $index got packet ${packetChannel.receive().value}")
    }
}

data class Packet(
        val value: Int
)
