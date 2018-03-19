package tickets.model.user;

import java.util.Date;

public class UserOd {

	private String OdID = "";
	private String email = "";
	private String planID = "";
	private int numOfTicket = 0;
	private double totalPrice = 0.0;
	private double vipDiscount = 0.0;
	private int couponDiscount = 0;
	private double totalPay = 0.0;
	private Date makeTime;
	private boolean isPaid = false;
	private boolean isTimeout = false;
	private boolean isDeleted = false;
	private boolean isSeated = false;
	private boolean isChecked = false;
	private boolean isOnline = false;
	
	public String getOdID(){
		return OdID;
	}
	
	public void setOdID(String odID){
		OdID = odID;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getPlanID(){
		return planID;
	}
	
	public void setPlanID(String planID){
		this.planID = planID;
	}
	
	public int getNumOfTicket(){
		return numOfTicket;
	}
	
	public void setNumOfTicket(int numOfTicket){
		this.numOfTicket = numOfTicket;
	}
	
	public double getTotalPrice(){
		return totalPrice;
	}
	
	public void setTotalPrice(double totalPrice){
		this.totalPrice = totalPrice;
	}
	
	public double getVipDiscount(){
		return vipDiscount;
	}
	
	public void setVipDiscount(double vipDiscount){
		this.vipDiscount = vipDiscount;
	}
	
	public int getCouponDiscount(){
		return couponDiscount;
	}
	
	public void setCouponDiscount(int couponDiscount){
		this.couponDiscount = couponDiscount;
	}
	
	public double getTotalPay(){
		return totalPay;
	}
	
	public void setTotalPay(double totalPay){
		this.totalPay = totalPay;
	}
	
	public Date getMakeTime(){
		return makeTime;
	}
	
	public void setMakeTime(Date makeTime){
		this.makeTime = makeTime;
	}
	
	public boolean isPaid(){
		return isPaid;
	}
	
	public void setPaid(boolean paid){
		isPaid = paid;
	}
	
	public boolean isTimeout(){
		return isTimeout;
	}
	
	public void setTimeout(boolean timeout){
		isTimeout = timeout;
	}
	
	public boolean isDeleted(){
		return isDeleted;
	}
	
	public void setDeleted(boolean deleted){
		isDeleted = deleted;
	}
	
	public boolean isSeated(){
		return isSeated;
	}
	
	public void setSeated(boolean seated){
		isSeated = seated;
	}
	
	public boolean isChecked(){
		return isChecked;
	}
	
	public void setChecked(boolean checked){
		isChecked = checked;
	}
	
	public boolean isOnline(){
		return isOnline;
	}
	
	public void setOnline(boolean online){
		isOnline = online;
	}
	
}
