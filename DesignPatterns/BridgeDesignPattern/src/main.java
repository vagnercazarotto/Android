


public class main {

	public static void main(String[] args) {

		
		/**
		 * • You want to avoid a permanent binding between an abstraction and its implementation. This might be the case, for example,
		 * when the implementation must be selected or switched at run-time.
		 * • Both the abstractions and their implementations should be extensible by sub-classing. In this case, the Bridge pattern lets you
		 * combine the different abstractions and implementations and extend them independently.
		 * • Changes in the implementation of an abstraction should have no impact on clients; that is, their code should not have to be recompiled.
         * • You want to share an implementation among multiple objects (perhaps using reference counting), and this fact should be hidden from the client.
		 */
		
		Product product = new CentralLocking("Central Locking System");
		Product product2 = new GearLocking("Gear Locking System");
		Car car = new BigWheel(product , "BigWheel xz model");
		car.produceProduct();
		car.assemble();
		car.printDetails();
		System.out.println();
		car = new BigWheel(product2 , "BigWheel xz model");
		car.produceProduct();
		car.assemble();
		car.printDetails();
		car = new Motoren(product, "Motoren lm model");
		car.produceProduct();
		car.assemble();
		car.printDetails();
		System.out.println();
		car = new Motoren(product2, "Motoren lm model");
		car.produceProduct();
		car.assemble();
		car.printDetails();
		
		
		
	}

}
