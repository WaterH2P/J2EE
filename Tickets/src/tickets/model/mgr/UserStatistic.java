package tickets.model.mgr;

public class UserStatistic {
	
	private String email = "";
	private String name = "";
	private String vipLevel = "";
	private int totalPoint = 0;
	private double totalConsume = 0.0;
	private int numOfTicket = 0;
	private int numOfTTimeout = 0;
	private int numOfTChecked = 0;
	private int numOfTDeleted = 0;
	private int numOfTOnline = 0;
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getVipLevel(){
		return vipLevel;
	}
	
	public void setVipLevel(String vipLevel){
		this.vipLevel = vipLevel;
	}
	
	public int getTotalPoint(){
		return totalPoint;
	}
	
	public void setTotalPoint(int totalPoint){
		this.totalPoint = totalPoint;
	}
	
	public double getTotalConsume(){
		return totalConsume;
	}
	
	public void setTotalConsume(double totalConsume){
		this.totalConsume = totalConsume;
	}
	
	public int getNumOfTicket(){
		return numOfTicket;
	}
	
	public void setNumOfTicket(int numOfTicket){
		this.numOfTicket = numOfTicket;
	}
	
	public int getNumOfTTimeout(){
		return numOfTTimeout;
	}
	
	public void setNumOfTTimeout(int numOfTTimeout){
		this.numOfTTimeout = numOfTTimeout;
	}
	
	public int getNumOfTChecked(){
		return numOfTChecked;
	}
	
	public void setNumOfTChecked(int numOfTChecked){
		this.numOfTChecked = numOfTChecked;
	}
	
	public int getNumOfTDeleted(){
		return numOfTDeleted;
	}
	
	public void setNumOfTDeleted(int numOfTDeleted){
		this.numOfTDeleted = numOfTDeleted;
	}
	
	public int getNumOfTOnline(){
		return numOfTOnline;
	}
	
	public void setNumOfTOnline(int numOfTOnline){
		this.numOfTOnline = numOfTOnline;
	}
	
}
