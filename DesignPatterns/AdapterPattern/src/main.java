
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Object for Xpay
		Xpay xpay = new XpayImpl();
		xpay.setCreditCardNo("4789565874102365");
		xpay.setCustomerName("Max Warner");
		xpay.setCardExpMonth("09");
		xpay.setCardExpYear("25");
		xpay.setCardCVVNo((short)235);
		xpay.setAmount(2565.23);
		PayD payD = new XpayToPayDAdapter(xpay);
		testPayD(payD);
		
		System.out.println("OK");
	}

	private static void testPayD(PayD payD){
		System.out.println(payD.getCardOwnerName());
		System.out.println(payD.getCustCardNo());
		System.out.println(payD.getCardExpMonthDate());
		System.out.println(payD.getCVVNo());
		System.out.println(payD.getTotalAmount());
	}
}
