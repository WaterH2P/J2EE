package tickets.dao.user;

import tickets.exception.AccountAccessException;

import java.util.List;

public interface UserAccountDao {
	
	List<String> selectAllEmails();
	
	boolean codeCheck(String email, String verificationCode) throws AccountAccessException;
	
	boolean accountIsExist(String email);
	
	boolean addAccount(String email, String verificationCode, String name);
	
	void updateAccountCode(String email, String verificationCode);
	
	void updateRegisterAccount(String email, String password);
	
	void deleteAccountVIP(String email);
	
}
