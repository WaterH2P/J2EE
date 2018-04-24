package tickets.model.venue;

public class VenueHall {
	
	private String hallID = "";
	private String venueID = "";
	private String name = "";
	private int numOfRow = 0;
	private int numOfCol = 0;
	private String seatDist = "";
	
	public String getHallID(){
		return hallID;
	}
	
	public void setHallID(String hallID){
		this.hallID = hallID;
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
	
	public String getSeatDist(){
		return seatDist;
	}
	
	public void setSeatDist(String seatDist){
		this.seatDist = seatDist;
	}
	
}
