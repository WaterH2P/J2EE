package tickets.model.venue;

public class VenuePlanSeat {
	
	private String hallID = "";
	private int row = 0;
	private int col = 0;
	private int percent = 0;
	
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
	
	public int getPercent(){
		return percent;
	}
	
	public void setPercent(int percent){
		this.percent = percent;
	}
	
}
