package demo.higher_order_function


fun main(args: Array<String>) {
    val id = 10
    val dao = { it: Int -> mapOf(Pair(80, 8080), Pair(443, 8443)).get(it) }
    val port = 80

    idToContainer(id)

}


//fun idToContainer(dao: KFunction1<Int, Container>):(Int) -> Container {
//fun idToContainer(dao: KFunction1<Id, Container>):(Int) -> Container {
//    return {id:Int -> dao(id)}
//}

fun idToContainer(id:Int) = Id(id)
fun containerDao(id:Id) = Container(id)


data class Container(val id:Id)

data class Id(val id:Int)