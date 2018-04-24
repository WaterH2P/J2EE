package tickets.model.venue;

import java.util.Date;

public class VenuePlan {
	
	private String planID = "";
	private String venueID = "";
	private String name = "";
	private String type = "";
	private Date beginTime;
	private Date endTime;
	private String hallID = "";
	private String hallName = "";
	private int numOfRow = 0;
	private int numOfCol = 0;
	private int numOfTicket = 0;
	private int numOfTLeft = 0;
	private int numOfTSeated = 0;
	private int numOfTUnallocated = 0;
	private double price = 0.0;
	private String description = "";
	private String seatDist = "";
	private boolean isChecking = false;
	private boolean isChecked = false;
	
	public String getPlanID(){
		return planID;
	}
	
	public void setPlanID(String planID){
		this.planID = planID;
	}
	
	public String getVenueID(){
		return venueID;
	}
	
	public void setVenueID(String venueID){
		this.venueID = venueID;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public Date getBeginTime(){
		return beginTime;
	}
	
	public void setBeginTime(Date beginTime){
		this.beginTime = beginTime;
	}
	
	public Date getEndTime(){
		return endTime;
	}
	
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	
	public String getHallID(){
		return hallID;
	}
	
	public void setHallID(String hallID){
		this.hallID = hallID;
	}
	
	public String getHallName(){
		return hallName;
	}
	
	public void setHallName(String hallName){
		this.hallName = hallName;
	}
	
	public int getNumOfRow(){
		return numOfRow;
	}
	
	public void setNumOfRow(int numOfRow){
		this.numOfRow = numOfRow;
	}
	
	public int getNumOfCol(){
		return numOfCol;
	}
	
	public void setNumOfCol(int numOfCol){
		this.numOfCol = numOfCol;
	}
	
	public int getNumOfTicket(){
		return numOfTicket;
	}
	
	public void setNumOfTicket(int numOfTicket){
		this.numOfTicket = numOfTicket;
	}
	
	public int getNumOfTLeft(){
		return numOfTLeft;
	}
	
	public void setNumOfTLeft(int numOfTLeft){
		this.numOfTLeft = numOfTLeft;
	}
	
	public int getNumOfTSeated(){
		return numOfTSeated;
	}
	
	public void setNumOfTSeated(int numOfTSeated){
		this.numOfTSeated = numOfTSeated;
	}
	
	public int getNumOfTUnallocated(){
		return numOfTUnallocated;
	}
	
	public void setNumOfTUnallocated(int numOfTUnallocated){
		this.numOfTUnallocated = numOfTUnallocated;
	}
	
	public double getPrice(){
		return price;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getSeatDist(){
		return seatDist;
	}
	
	public void setSeatDist(String seatDist){
		this.seatDist = seatDist;
	}
	
	public boolean isChecking(){
		return isChecking;
	}
	
	public void setChecking(boolean checking){
		isChecking = checking;
	}
	
	public boolean isChecked(){
		return isChecked;
	}
	
	public void setChecked(boolean checked){
		isChecked = checked;
	}
	
}
