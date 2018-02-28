package main.service;

import main.dao.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ClientServiceImpl implements ClientService {
	
	private static ClientServiceImpl clientService = new ClientServiceImpl();
	
	private ClientServiceImpl(){};
	
	public static ClientServiceImpl getInstance(){
		return clientService;
	}
	
	@Resource(name = "clientDao")
	private ClientDao clientDao;
	
	@Override
	public boolean login(String username, String password){
		
		return clientDao.checkLogin(username, password);
	}
	
}
