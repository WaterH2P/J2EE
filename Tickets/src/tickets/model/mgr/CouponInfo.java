package tickets.model.mgr;

public class CouponInfo {
	
	private String couponID = "";
	private String name = "";
	private double discount = 0.0;
	private int point = 0;
	
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
	
	public int getPoint(){
		return point;
	}
	
	public void setPoint(int point){
		this.point = point;
	}
	
}
