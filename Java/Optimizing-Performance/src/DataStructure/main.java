package DataStructure;

public class main {

	public static void main(String[] args) {

		Integer timeFromSQL = 0014;
		Integer timeNow = 2354;
		boolean specialCase = false;
		if (timeFromSQL < timeNow) {
			specialCase = true;
		}
		Integer totalMinutes = null;
		Integer hours = null;
		Integer minutes = null;
		hours = (timeFromSQL - timeNow) / 100;

		if (specialCase == true) {
			Integer timeBefore = (2400 - timeNow) - 40;
			System.out.println("TIME BEFORE::" + timeBefore);
			Integer timeAfter = timeFromSQL;
			System.out.println("TIME AFTER::" + timeAfter);
			totalMinutes = timeAfter + timeBefore;
			System.out.println("SPECIAL CASE::" + totalMinutes);

		} else if (hours > 0) {
			minutes = (Math.abs(timeFromSQL - timeNow) % 100);
			System.out.println("MINUTES::1::" + minutes);
			totalMinutes = (hours * 60) + Math.abs(minutes);

		} else if ((Math.abs(timeFromSQL - timeNow) % 100) < 40) {

			minutes = (Math.abs(timeFromSQL - timeNow) % 100);
			System.out.println("MINUTES::2.5::" + minutes);
			totalMinutes = Math.abs(minutes);

		} else {

			minutes = (Math.abs(timeFromSQL - timeNow) % 100);
			System.out.println("MINUTES::3::" + minutes);

			minutes = minutes - 40;
			totalMinutes = Math.abs(minutes);
		}

		System.out.println(totalMinutes);

	}

}