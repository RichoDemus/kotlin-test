package demo.json

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

class JsonSerialization {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            JsonSerialization().stuff()
        }
    }

    fun stuff() {
        val birka = User("Birka", 5)

        val mapper = ObjectMapper().registerModule(KotlinModule())

        val birkaJson = mapper.writeValueAsString(birka)


        val birkaParsed = mapper.readValue(birkaJson, User::class.java)


        println("original object: " + birka)
        println("json: " + birkaJson)
        println("parsed: " + birkaParsed)

        println("operator equals?: " + (birka == birkaParsed))
        println("Equal?: " + birka.equals(birkaParsed))

        printUsingLambda("Lambda equals", {birka == birkaParsed})
    }

    private fun printUsingLambda(msg: String, comparisonSupplier: () -> Boolean) {
        println(msg + " " + comparisonSupplier.invoke())
    }
}
