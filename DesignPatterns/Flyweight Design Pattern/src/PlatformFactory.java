import java.util.HashMap;
import java.util.Map;

public class PlatformFactory {
	
	/**
	 * The above class contains a static map which holds a String object as key and a Platform type object as its value. We don’t
	 * want to create the instance of this class so just kept its constructor private and throw an AssertionError just to avoid any
	 * accidental creation of the object even within the class.
	 */
	
	private static Map<String, Platform> map = new HashMap<>();
	
	private PlatformFactory() { 
		throw new AssertionError("Cannot instantiate the class");
	}
	
	public static synchronized Platform getPlatformInstance(String platformType) { 
		Platform platform = map.get(platformType);
		if(platform==null) { 
			switch(platformType) { 
			case "C" : platform = new CPlatform();
				break;
			case "CPP" : platform = new CPPPlatform();
				break;
			case "JAVA" : platform = new JavaPlatform();
				break;
			case "RUBY" : platform = new RubyPlatform();
				break;
			}
			map.put(platformType, platform);
		}
		return platform;
	}

}
