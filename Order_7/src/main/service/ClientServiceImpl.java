package main.service;

import main.dao.ClientDao;
import main.factory.DaoFactory;

public class ClientServiceImpl implements ClientService {
	private static ClientServiceImpl clientService = new ClientServiceImpl();
	
	private ClientServiceImpl(){};
	
	public static ClientServiceImpl getInstance(){
		return clientService;
	}
	
	@Override
	public boolean login(String username, String password){
		ClientDao clientDao = DaoFactory.getClientDao();
		return clientDao.checkLogin(username, password);
	}
}
