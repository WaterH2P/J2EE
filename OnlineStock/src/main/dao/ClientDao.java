package main.dao;

import javax.servlet.http.HttpServletRequest;

public interface ClientDao {
	
	boolean checkLogin(String username, String password);
	
}
