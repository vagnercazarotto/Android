
public class XpayToPayDAdapter implements PayD {
	
	private String custCardNo;
	private String cardOwnerName;
	private String cardExpMonthDate;
	private Integer cVVNo;
	private Double totalAmount;
	
	// We will create an adapter which will be of type PayD, and it wraps an Xpay object (the type it supposes to be adapted).
	private final Xpay xpay;
	
	public XpayToPayDAdapter(Xpay xpay) { 
		this.xpay = xpay;
		setProp();
	}
	
	@Override
	public String getCustCardNo() {
		// TODO Auto-generated method stub
		return custCardNo;
	}
	@Override
	public String getCardOwnerName() {
		// TODO Auto-generated method stub
		return cardOwnerName;
	}
	@Override
	public String getCardExpMonthDate() {
		// TODO Auto-generated method stub
		return cardExpMonthDate;
	}
	@Override
	public Integer getCVVNo() {
		// TODO Auto-generated method stub
		return cVVNo;
	}
	@Override
	public Double getTotalAmount() {
		// TODO Auto-generated method stub
		return totalAmount;
	}
	@Override
	public void setCustCardNo(String custCardNo) {
		// TODO Auto-generated method stub
		this.custCardNo = custCardNo;
	}
	@Override
	public void setCardOwnerName(String cardOwnerName) {
		// TODO Auto-generated method stub
		this.cardOwnerName = cardOwnerName;
		
	}
	@Override
	public void setCardExpMonthDate(String cardExpMonthDate) {
		// TODO Auto-generated method stub
		this.cardExpMonthDate = cardExpMonthDate;
		
	}
	@Override
	public void setCVVNo(Integer cVVNo) {
		// TODO Auto-generated method stub
		this.cVVNo = cVVNo;
	}
	@Override
	public void setTotalAmount(Double totalAmount) {
		// TODO Auto-generated method stub
		this.totalAmount = totalAmount;
		
	}
	
	private void setProp() { 
		setCardOwnerName(this.xpay.getCustomerName());
		setCustCardNo(this.xpay.getCreditCardNo());
		setCardExpMonthDate(this.xpay.getCardExpMonth()+"/"+this.xpay.getCardExpYear());
		setCVVNo(this.xpay.getCardCVVNo().intValue());
		setTotalAmount(this.xpay.getAmount());
	}	
}
