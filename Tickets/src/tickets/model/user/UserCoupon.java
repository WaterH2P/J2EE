package tickets.model.user;

public class UserCoupon {
	
	private String couponID = "";
	private String name = "";
	private double discount = 0.0;
	private int number = 0;
	
	public String getCouponID(){
		return couponID;
	}
	
	public void setCouponID(String couponID){
		this.couponID = couponID;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public double getDiscount(){
		return discount;
	}
	
	public void setDiscount(double discount){
		this.discount = discount;
	}
	
	public int getNumber(){
		return number;
	}
	
	public void setNumber(int number){
		this.number = number;
	}
	
}
