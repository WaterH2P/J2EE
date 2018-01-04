package main.factory;

import main.dao.ClientDao;
import main.dao.OrderDao;
import main.dao.impl.ClientDaoImpl;
import main.dao.impl.OrderDaoImpl;

public class DaoFactory {
	
	public static ClientDao getClientDao(){
		return ClientDaoImpl.getInstance();
	}
	
	public static OrderDao getOrderDao(){
		return OrderDaoImpl.getInstance();
	}
}
