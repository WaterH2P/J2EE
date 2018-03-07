package tickets.model;

public class UserInfo {
	
	private String email = "";
	private String name = "";
	private String vipLevel = "";
	private int balance = 0;
	private boolean isConfirmed = false;
	private boolean isDeleted = false;
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setVipLevel(String vipLevel){
		this.vipLevel = vipLevel;
	}
	
	public void setBalance(int balance){
		this.balance = balance;
	}
	
	public void setConfirmed(boolean confirmed){
		isConfirmed = confirmed;
	}
	
	public void setDeleted(boolean deleted){
		isDeleted = deleted;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getName(){
		return name;
	}
	
	public String getVipLevel(){
		return vipLevel;
	}
	
	public int getBalance(){
		return balance;
	}
	
	public boolean isConfirmed(){
		return isConfirmed;
	}
	
	public boolean isDeleted(){
		return isDeleted;
	}
}
