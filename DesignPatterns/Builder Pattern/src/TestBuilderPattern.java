
public class TestBuilderPattern {
	
	public static void main(String[] args) {
		
		/**
		 * The approach to use the Builder Pattern is flexible enough to add any new type of a car in the future without changing any of the
		 * existing code. All we need is to create a new builder according to the specification of the new car and provide it to the Director to build.
		 */
		
		CarBuilder carBuilder = new SedanCarBuilder();
		CarDirector director = new CarDirector(carBuilder);
		director.build();

		Car car = carBuilder.getCar();
		System.out.println(car);
		
		
		carBuilder = new SportCarBuilder();
		director = new CarDirector(carBuilder);
		
		director.build();
		
		car = carBuilder.getCar();
		System.out.println(car);
		
		
		}
}
