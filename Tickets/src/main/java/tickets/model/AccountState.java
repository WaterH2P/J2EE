package tickets.model;

public class AccountState {
	
	private String password = "";
	private boolean isConfirmed = false;
	private boolean isDeleted = false;
	private boolean isPasswordRight = false;
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public boolean isConfirmed(){
		return isConfirmed;
	}
	
	public void setConfirmed(boolean confirmed){
		isConfirmed = confirmed;
	}
	
	public boolean isDeleted(){
		return isDeleted;
	}
	
	public void setDeleted(boolean deleted){
		isDeleted = deleted;
	}
	
	public boolean isPasswordRight(){
		return isPasswordRight;
	}
	
	public void checkPassword(String password){
		if( password.equals(this.password) ){
			isPasswordRight = true;
		}
		else {
			isPasswordRight = false;
		}
		this.password = null;
	}
}
