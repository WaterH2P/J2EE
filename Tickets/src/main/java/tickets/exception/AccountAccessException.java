package tickets.exception;

public class AccountAccessException extends Exception {

	public AccountAccessException(){
		super();
	}
	
	public AccountAccessException(String message){
		super(message);
	}
	
	@Override
	public String getMessage(){
		return super.getMessage();
	}
	
	@Override
	public String toString(){
		return super.toString();
	}
}
