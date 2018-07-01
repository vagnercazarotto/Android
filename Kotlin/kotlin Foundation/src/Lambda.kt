fun main(args: Array<String>) {
    val printMessage =  {message: String -> println(message)}

    printMessage("Hello World!!")

    val sumA = {x:Int, y:Int -> x+y}
    val sumB : (Int,Int) -> Int = {x,y -> x+y}

    println(sumA(4,5))

    fun downloadData(url: String, completion: () -> Unit){
        // sent a download request
        // we got back data
        // there were no network errors
        completion()
    }

    // call downloadDataFunction
    downloadData("fakeUrl.com", {
        println("the code in this block will ony run after the completion()")
    })


    fun downloadCarData(url:String, completion: (Car) -> Unit ){
        // send download request
        // we got back car data
        val car = Car("Tesla", "ModelX", "Blue")
        completion(car)

    }

    downloadCarData("fakeurl.com"){car ->
        // return a car data
        println(car.color)
        println(car.model)
    }


    fun downloadTruckData(url:String,completion:(Truck?, Boolean) -> Unit){
        // make web request
        // results back
        val webrequestSuccess = true
        if(webrequestSuccess){
            // receive truck data
            val truck = Truck("FOrd", "F-150",10000)
        } else
        {
            completion(null, false)
        }
    }


    downloadTruckData("fakeurl.com"){truck,success ->
        if (success == true){
            truck?.tow()
        } else {
            println("Something went wrong!!")
        }
    }



}

