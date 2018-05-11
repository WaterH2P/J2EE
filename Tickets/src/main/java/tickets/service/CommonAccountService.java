package tickets.service;

import tickets.exception.AccountAccessException;

public interface CommonAccountService {
	
	boolean login(String EmailORID, String password) throws AccountAccessException;
	
}
