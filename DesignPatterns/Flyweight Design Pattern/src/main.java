
public class main {

	public static void main(String[] args) {
		
		/**
		 * The intent of the Flyweight Pattern is to use shared objects to support large numbers of fine-grained objects efficiently.
		 * 
		 * If we reconsider that 2k users concurrently use the site, exactly 2k light weight Code object get created, and only 4 heavy weight
		 * platform objects are instantiated. Please note that, we are saying 4 platform objects by considering that there would be at least
		 * one user per language. If, for example, let say no user have coded using the Ruby, only 3 platform objects get created in that scenario.
		 */
		
		Code code = new Code();
		code.setCode("C Code...");
		Platform platform = PlatformFactory.getPlatformInstance("C");
		platform.execute(code);
		System.out.println("*************************");
		
		code = new Code();
		code.setCode("C Code2...");
		platform = PlatformFactory.getPlatformInstance("C");
		platform.execute(code);
		System.out.println("*************************");
		
		code = new Code();
		code.setCode("JAVA Code...");
		platform = PlatformFactory.getPlatformInstance("JAVA");
		platform.execute(code);
		System.out.println("*************************");
		
		code = new Code();
		code.setCode("JAVA Code2...");
		platform = PlatformFactory.getPlatformInstance("JAVA");
		platform.execute(code);
		System.out.println("*************************");
		
		code = new Code();
		code.setCode("RUBY Code...");
		platform = PlatformFactory.getPlatformInstance("RUBY");
		platform.execute(code);
		System.out.println("*************************");
		
		code = new Code();
		code.setCode("RUBY Code2...");
		platform = PlatformFactory.getPlatformInstance("RUBY");
		platform.execute(code);

	}

}
