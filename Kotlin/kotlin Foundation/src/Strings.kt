fun main(args: Array<String>) {
    val str = "may thr force\b  \$ \nbe with you!!"
    println(str)

    val rawCrawl = """ A long time ago,
        in a galaxy
        .....
        .... """.trimMargin()

    println(rawCrawl)

//    for(char in str){
//        println(char)
//    }

    var contentEquals =  str.contentEquals("may thr force\b  \$ \n" + "be with you!!")
    println(contentEquals)

    val contains = str.contains("Force", false)
    println(contains)

    val uppercase = str.toUpperCase()
    val lower =  str.toLowerCase()

    println(uppercase)
    println(lower)

    val num = 6
    val stringNum = num.toString()

    println(stringNum)

    val subsequence = str.subSequence(4,13)
    println(subsequence)

    val luke = "Luke Skywalker"
    val vehicle = "landspeeder"

    println("$luke has a $vehicle")



}