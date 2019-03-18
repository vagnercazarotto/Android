
public abstract class DisplayService {

	public void display(){
		XMLParser parser = getParser();
		String msg = parser.parser();
		System.out.println(msg);
	}

	protected abstract XMLParser getParser();
	
}
