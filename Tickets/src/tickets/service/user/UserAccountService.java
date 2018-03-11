package tickets.service.user;

public interface UserAccountService {
	
	boolean accountExist(String email);
	
	boolean preRegister(String email, String name);
	
	boolean register(String email, String password, String verificationCode);
}
