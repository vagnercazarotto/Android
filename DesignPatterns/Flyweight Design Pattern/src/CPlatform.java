
public class CPlatform implements Platform {

	@Override
	public void execute(Code code) {
		System.out.println("Compiling and executing C code.");
	}

	public CPlatform() { 
		System.out.println("CPlatform object created");
	}
	
}
