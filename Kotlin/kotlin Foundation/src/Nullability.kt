fun main(args: Array<String>) {

    var name: String = "JonnyB"
    // name =  null

    var nullableName : String? = "Do I really exist?"
    nullableName = null

    // Similar to iOS
    // Null Check
    var length = 0
    if(nullableName != null){
        length =  nullableName.length
    } else {
        length = -1
    }

    println(length)



    val l = if(nullableName != null) nullableName.length else -1

    println(l)

    // Second method Safe Call Operator ?

    println(nullableName?.length)

    // third method is Elvis Operator

    val len = nullableName?.length ?:  -1

    println(len)

    // !! -->> Force to unwrap a variable
    //println(nullableName!!.length)


}