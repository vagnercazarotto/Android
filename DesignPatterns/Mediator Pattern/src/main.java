
public class main {

	public static void main(String[] args) {
		
		
		/**
		 * In the above class, we created the objects required, mediators (or we can say different wash programs), then we set the wash
		 * programs to the colleagues and vice-versa, and the we called the start() method on the button object to start the machine.
		 * The rest is done automatically without any human interaction.
		 */


		MachineMediator mediator = null;
		
		Sensor sensor = new Sensor();
		SoilRemoval soilRemoval = new SoilRemoval();
		Motor motor = new Motor();
		Machine machine = new Machine();
		Heater heater = new Heater();
		Valve valve = new Valve();
		Button button = new Button();
		
		mediator = new CottonMediator(machine, heater, motor, sensor, soilRemoval, valve);

		button.setMediator(mediator);
		machine.setMediator(mediator);
		heater.setMediator(mediator);
		valve.setMediator(mediator);
		
		button.press();


	}

}
