package tickets.model;

public class UserInfo {
	
	private String email = "";
	private String name = "";
	private String vipLevel = "";
	private int balance = 0;
	private int point = 0;
	
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
	
	public int getBalance(){
		return balance;
	}
	
	public void setBalance(int balance){
		this.balance = balance;
	}
	
	public int getPoint(){
		return point;
	}
	
	public void setPoint(int point){
		this.point = point;
	}
	
}
