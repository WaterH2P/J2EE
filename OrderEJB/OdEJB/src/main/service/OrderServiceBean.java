package main.service;

import main.dao.OrderDao;
import main.factory.EJBFactory;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class OrderServiceBean implements OrderService {
	
	@Override
	public List getOrders(String userID){
		OrderDao orderDao = (OrderDao) EJBFactory.getEJB(
				"ejb:/OdEJBClient/OrderDaoBean!main.dao.OrderDao"
		);
		return orderDao.selectOrders( userID );
	}
	
}
