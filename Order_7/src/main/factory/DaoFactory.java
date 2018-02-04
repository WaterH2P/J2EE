package main.factory;

import main.dao.ClientDao;
import main.dao.OrderDao;
import main.dao.ClientDaoImpl;
import main.dao.OrderDaoImpl;

public class DaoFactory {
	
	public static ClientDao getClientDao(){
		return ClientDaoImpl.getInstance();
	}
	
	public static OrderDao getOrderDao(){
		return OrderDaoImpl.getInstance();
	}
}
