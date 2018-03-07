package tickets.dao;

import java.util.List;

public interface UserAccountDao {
	
	List<String> selectAllEmails();
	
	boolean loginCheck(String email, String password);
	
	boolean codeCheck(String email, String verificationCode);
	
	boolean accountIsExist(String email);
	
	boolean addAccount(String email, String verificationCode, String name);
	
	boolean accountIsConfirmed(String email);
	
	void updateAccountCode(String email, String verificationCode);
	
	void updateRegisterAccount(String email, String password);
	
}
