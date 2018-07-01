fun main(args: Array<String>) {

    fun forceChoke(){
        println("You have failed me for the last time admiral!...")
    }

    forceChoke()

    fun makeAnEntrance(line: String){
        println(line)
    }

    makeAnEntrance(line = "I find your lack of faith disturbing")

    fun calculateNumberGoodGuys(rebels: Int, ewoks: Int):Int{
        val goodGuys = rebels + ewoks
        return goodGuys
    }

    val rebels = calculateNumberGoodGuys(rebels = 5, ewoks = 4)

    println(rebels)

}