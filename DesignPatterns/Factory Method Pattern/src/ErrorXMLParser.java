
public class ErrorXMLParser implements XMLParser {

	@Override
	public String parser() {
		System.out.println("Parsing error XML...");
		return "Error XML Message";
	}

}
