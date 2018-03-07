package tickets.dao;

import java.util.List;

public interface AccountDao {
	
	List<String> selectAllEmails();
	
	boolean loginCheck(String email, String password);
	
	boolean accountExist(String email);
	
	boolean addAccount(String email, String password, String name);
	
}
