
public class SingletonEager { 
	
	public static SingletonEager sc = new SingletonEager();
	
	private SingletonEager() { }
	
	public static SingletonEager getInstance() { 
		if(sc == null) { 
			sc = new SingletonEager();
		}
		return sc;
	}
}
