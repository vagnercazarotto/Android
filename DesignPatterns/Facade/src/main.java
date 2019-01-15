
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ScheduleServer scheduleServer = new ScheduleServer();
		ScheduleServerFacade facadeServer = new ScheduleServerFacade(scheduleServer);
		
		facadeServer.startServer();
		System.out.println("Start working......");
		System.out.println("After work done.........");
		facadeServer.stopServer();
		
		
		System.out.println("ok");
	}

}
