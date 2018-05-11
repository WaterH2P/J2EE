package tickets.dao;

import tickets.exception.AccountAccessException;
import tickets.model.AccountState;

public interface CommonAccountDao {
	
	AccountState selectAccountSateInfo(String emailOrID, String password) throws AccountAccessException;
	
	boolean selectAccountIsConfirmed(String emailOrID) throws AccountAccessException;
	
	boolean selectAccountIsDeleted(String emailOrID) throws AccountAccessException;
}
