
public class main {

	public static void main(String[] args) {
		
		DisplayService service = new FeedbackXMLDisplayService();
		service.display();
		
		service = new ErrorXMLDisplayService();
		service.display();
		
		service = new OrderXMLDisplayService();
		service.display();
		
		service = new ResponseXMLDisplayService();
		service.display();

	}

}
