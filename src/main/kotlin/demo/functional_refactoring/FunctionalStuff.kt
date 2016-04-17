package demo.functional_refactoring

internal class FunctionalStuff {
    private val countries = listOf("Sweden", "Norway", "Finland")
    private val cities = mapOf(
            Pair("Sweden", listOf("Stockholm", "Västerås")),
            Pair("Norway", listOf("Oslo")),
            Pair("Finland", listOf("Helsinki")))
    private val people = mapOf(
            Pair("Stockholm", listOf("Erik", "Lotta", "Lisa")),
            Pair("Västerås", listOf("Sven", "Nina")),
            Pair("Oslo", listOf("Yngwe")),
            Pair("Helsinki", listOf("Kim")))

    fun getAllCountries() = countries
    fun getCitiesByCountry(country: String) = cities.getOrElse(country, {listOf()})
    fun getInhabitantsByCity(city: String) = people.getOrElse(city, {listOf()})

    fun getAllPeople() = getAllCountries()
            .flatMap { getCitiesByCountry(it) }
            .flatMap { getInhabitantsByCity(it) }
}

internal fun main(args : Array<String>) = FunctionalStuff().getAllPeople().forEach { println(it) }
