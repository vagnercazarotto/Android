import java.rmi.Naming;

public class ReportGeneratorClient {
	
	/**
	 * should use rmic and start rmiregistry tools
	 * @param args
	 */

	public static void main(String[] args) { 
		new ReportGeneratorClient().generateReport();
	}
	
	
	public void generateReport(){
		try {
			ReportGenerator reportGenerator = (ReportGenerator) Naming.lookup("PizzaCoRemoteGenerator");
			System.out.println(reportGenerator.generateDailyReport());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
