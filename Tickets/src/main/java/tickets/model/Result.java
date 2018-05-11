package tickets.model;

public class Result {
	
	private boolean result = false;
	
	private String message = "";
	
	public Result(){}
	
	public Result(boolean result){
		this.result = result;
	}
	
	public boolean getResult(){
		return result;
	}
	
	public void setResult(boolean result){
		this.result = result;
	}
	
	public String getMessage(){
		return message;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
}
