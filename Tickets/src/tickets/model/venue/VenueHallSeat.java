package tickets.model.venue;

public class VenueHallSeat {
	
	private String hallID = "";
	private int row = 0;
	private int col = 0;
	private String seatID = "";
	private String state = "";
	
	public String getHallID(){
		return hallID;
	}
	
	public void setHallID(String hallID){
		this.hallID = hallID;
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
	
	public String getSeatID(){
		return seatID;
	}
	
	public void setSeatID(String seatID){
		this.seatID = seatID;
	}
	
	public String getState(){
		return state;
	}
	
	public void setState(String state){
		this.state = state;
	}
	
}
