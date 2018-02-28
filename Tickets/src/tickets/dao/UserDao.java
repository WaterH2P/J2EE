package tickets.dao;

public interface UserDao {
	
	boolean loginCheck(String userID, String password);
}
