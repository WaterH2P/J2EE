package tickets.model.venue;

public class VenueSeatLevel {
	
	private String seatID = "";
	private String venueID = "";
	private String name = "";
	private int percent = 0;
	
	public String getSeatID(){
		return seatID;
	}
	
	public void setSeatID(String seatID){
		this.seatID = seatID;
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
	
	public int getPercent(){
		return percent;
	}
	
	public void setPercent(int percent){
		this.percent = percent;
	}
	
}
