package main.dao;

import javax.ejb.Remote;

@Remote
public interface ClientDao {
	
	boolean checkLogin(String username, String password);
	
}
