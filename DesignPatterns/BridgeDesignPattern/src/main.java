


public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // Let's print the classloader name of current class.   
        //Application/System classloader will load this class  
        Class c= main.class;  
        System.out.println(c.getClassLoader());  
        //If we print the classloader name of String, it will print null because it is an  
        //in-built class which is found in rt.jar, so it is loaded by Bootstrap classloader  
        System.out.println(String.class.getClassLoader()); 
	}

}
