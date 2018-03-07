package tickets.service;

public interface AccountService {
	
	boolean login(String email, String password);
	
	boolean register(String email, String password, String name);
	
}
