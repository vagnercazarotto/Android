//
//class Car (val make: String, val model: String, var color: String){
//
//    fun accelerate(){
//        println("vroom vroom")
//    }
//
//    fun details(){
//        println("This is a $color $make $model")
//    }
//
//}
//
//class Truck(val make: String, val model: String , val towingCapacity: Int){
//
//    fun tow(){
//        println("taking the horses to the rodeo")
//    }
//
//    fun details(){
//        println("The $make $model has a towing capacity og $towingCapacity lbs")
//    }
//}

open class Vehicle(val make:String, val model: String){

    open fun acceletare(){
        println("vroom vroom")
    }

    fun park(){
        println("parking the vehicle")
    }

    fun brake(){
        println("STOP")
    }

}

class Car(make:String, model: String, var color: String) : Vehicle (make, model){

    override fun acceletare() {
        println("We are going ludicrous mode!!")
    }

}

class Truck(make:String, model: String, val towingCapacity: Int) : Vehicle(make, model){

    fun tow(){
        println("Bye Bye")
    }

}


fun main(args: Array<String>) {
//    val car = Car( make = "Toyota", model = "Avalon", color = "Red")
//    println(car.make)
//    println(car.model)
//    car.accelerate()
//    car.details()
//
//    val truck = Truck("Ford","F-150",10000)
//    truck.tow()
//    truck.details()


    val tesla = Car("Tesla", "ModelS", "Red")
    tesla.acceletare()

    val truck = Truck("Ford", "F-150", 10000)
    truck.brake()
    truck.tow() 

}