package tickets.dao;

public interface CommonUVAccountDao {
	
	boolean accountIsConfirmed(String emailOrID);
	
	boolean accountIsDeleted(String emailOrID);
	
}
