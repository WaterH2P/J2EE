package tickets.model.user;

public class UserOdSeat {
	
	private String OdID = "";
	private String planID = "";
	private int row = 0;
	private int col = 0;
	private double price = 0.0;
	
	public String getOdID(){
		return OdID;
	}
	
	public void setOdID(String odID){
		OdID = odID;
	}
	
	public String getPlanID(){
		return planID;
	}
	
	public void setPlanID(String planID){
		this.planID = planID;
	}
	
	public int getRow(){
		return row;
	}
	
	public void setRow(int row){
		this.row = row;
	}
	
	public int getCol(){
		return col;
	}
	
	public void setCol(int col){
		this.col = col;
	}
	
	public double getPrice(){
		return price;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
}
