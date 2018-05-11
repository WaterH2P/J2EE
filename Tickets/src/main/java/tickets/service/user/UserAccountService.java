package tickets.service.user;

import tickets.exception.AccountAccessException;

public interface UserAccountService {
	
	boolean accountExist(String email);
	
	boolean preRegister(String email, String name) throws AccountAccessException;
	
	boolean register(String email, String password, String verificationCode) throws AccountAccessException;
	
	void cancelAccountVIP(String email);
}
