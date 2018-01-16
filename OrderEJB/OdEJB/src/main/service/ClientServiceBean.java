package main.service;

import main.dao.ClientDao;
import main.factory.EJBFactory;

import javax.ejb.Stateless;

@Stateless
public class ClientServiceBean implements ClientService {
	
	@Override
	public boolean login(String username, String password){
		ClientDao clientDao = (ClientDao) EJBFactory.getEJB(
				"ejb:/OdEJBClient/ClientDaoBean!main.dao.ClientDao"
		);
		return clientDao.checkLogin(username, password);
	}
	
}
