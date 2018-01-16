package main.service;

import javax.ejb.Remote;

@Remote
public interface ClientService {
	
	boolean login(String username, String password);
	
}
