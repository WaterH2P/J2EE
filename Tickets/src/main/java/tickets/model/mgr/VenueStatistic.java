package tickets.model.mgr;

public class VenueStatistic {
	
	private String venueID = "";
	private String address = "";
	private String telephone = "";
	private int numOfHall = 0;
	private int numOfPlan = 0;
	private int numOfPlanIsChecking = 0;
	private int numOfPlanIsChecked = 0;
	private double totalPrice = 0;
	
	public String getVenueID(){
		return venueID;
	}
	
	public void setVenueID(String venueID){
		this.venueID = venueID;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getTelephone(){
		return telephone;
	}
	
	public void setTelephone(String telephone){
		this.telephone = telephone;
	}
	
	public int getNumOfHall(){
		return numOfHall;
	}
	
	public void setNumOfHall(int numOfHall){
		this.numOfHall = numOfHall;
	}
	
	public int getNumOfPlan(){
		return numOfPlan;
	}
	
	public void setNumOfPlan(int numOfPlan){
		this.numOfPlan = numOfPlan;
	}
	
	public int getNumOfPlanIsChecking(){
		return numOfPlanIsChecking;
	}
	
	public void setNumOfPlanIsChecking(int numOfPlanIsChecking){
		this.numOfPlanIsChecking = numOfPlanIsChecking;
	}
	
	public int getNumOfPlanIsChecked(){
		return numOfPlanIsChecked;
	}
	
	public void setNumOfPlanIsChecked(int numOfPlanIsChecked){
		this.numOfPlanIsChecked = numOfPlanIsChecked;
	}
	
	public double getTotalPrice(){
		return totalPrice;
	}
	
	public void setTotalPrice(double totalPrice){
		this.totalPrice = totalPrice;
	}
	
}
