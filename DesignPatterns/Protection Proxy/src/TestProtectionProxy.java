
public class TestProtectionProxy {

	public static void main(String[] args) {
		
		Owner owner = new Owner();
		ReportGeneratorProxy reportGenerator = new ReportGeneratorProtectionProxy(owner);
		owner.setReportGenerator(reportGenerator);
		
		Employee employee = new Employee();
		reportGenerator = new ReportGeneratorProtectionProxy(employee);
		employee.setReportGenerator(reportGenerator);
		System.out.println("For owner:");
		System.out.println(owner.generateDailyReport());
		System.out.println("For employee:");
		System.out.println(employee.generateDailyReport());
		
		/** Another type of proxy reference:
		 * 
		 * 
		 * • Cache Proxy/Server Proxy: To provide the functionality required to store the results of most frequently used target operations.
		 * The proxy object stores these results in some kind of a repository. When a client object requests the same operation, the proxy
		 * returns the operation results from the storage area without actually accessing the target object.
		 * • Firewall Proxy: The primary use of a firewall proxy is to protect target objects from bad clients. A firewall proxy can also be
		 * used to provide the functionality required to prevent clients from accessing harmful targets. 
		 * • Synchronization Proxy: To provide the required functionality to allow safe concurrent accesses to a target object by different
		 * client objects.
		 * • Smart Reference Proxy: To provide the functionality to prevent the accidental disposal/deletion of the target object when
		 * there are clients currently with references to it. To accomplish this, the proxy keeps a count of the number of references to the
		 * target object. The proxy deletes the target object if and when there are no references to it.
		 * • Counting Proxy: To provide some kind of audit mechanism before executing a method on the target object.
		 */
		
	}

}
