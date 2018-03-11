package tickets.dao;

public interface CommonAccountDao {
	
	boolean loginCheck(String emailOrID, String password);
	
}
