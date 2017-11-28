package demo.async

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import org.slf4j.LoggerFactory
import java.lang.Thread.sleep
import java.time.Duration
import kotlin.system.measureTimeMillis


val logger = LoggerFactory.getLogger("Main")!!

/**
 * <pre>
 * For best result, add some threads to the ForkJoinPool
 * -Djava.util.concurrent.ForkJoinPool.common.parallelism=5000
 * </pre>
 */
fun main(args: Array<String>) {

    logger.info("Start")
    val time = measureTimeMillis {


        val deffers = (1..3000).map {
            "string-$it"
        }.map {
            async {
                val res = expensiveOperation1(it)
                expensiveOperation2(res)
            }
        }

        runBlocking {
            val strings = deffers.map { it.await() }
            logger.info("done converting ${strings.size} strings")
        }
    }

    logger.info("End")
    logger.info("Took ${Duration.ofMillis(time).pretty()} seconds")

}


fun expensiveOperation1(str: String): String {
    sleep(1_000L)
    return "$str hello"
}

fun expensiveOperation2(str: String): String {
    sleep(1_000L)
    return str.toUpperCase()
}

private fun Duration.pretty() = String.format("%02d:%02d", (this.seconds % 3600) / 60, (this.seconds % 60))
