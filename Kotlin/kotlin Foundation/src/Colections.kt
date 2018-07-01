fun main(args: Array<String>) {

    val imperials = listOf("Emperor", "Darth Vadder", "Boba Fett", "Tarkin")
    println(imperials.sorted())
    println(imperials[2])
    println(imperials.last())
    println(imperials.contains("Luke"))

    val rebels =  arrayListOf("Leiah","Luke","Han Solo", "Mon Mothma")
    println(rebels.size)
    rebels.add("Chewbacca")
    println(rebels)
    rebels.add(0, "Lando")
    println(rebels)
    println(rebels.indexOf("Luke"))

    rebels.remove("Han Solo")
    println(rebels)


    val rebelVehiclesMap = mapOf("Solo" to "Millenium Falcon", "Luke" to "Landspeeder")

    println(rebelVehiclesMap.keys)
    println(rebelVehiclesMap.values)

    println(rebelVehiclesMap["Luke"])
    println(rebelVehiclesMap.size)

    println(rebelVehiclesMap.getOrDefault(key = "Leiah", defaultValue = "This ship doesnt exist!!"))
    println(rebelVehiclesMap.values)

    val rebelVehicles = hashMapOf("Solo" to "Millenium Falcon", "Luke" to "Landspeeder", "Boba Fett" to "Rocket Pack")
    rebelVehicles["Luke"] = "XWing"
    rebelVehicles.put("Leiah", "Tantive IV")

    println(rebelVehicles)

    rebelVehicles.remove("Boba Fett")
    println(rebelVehicles)





}