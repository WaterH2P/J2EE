package main.service.impl;

import main.factory.DaoFactory;
import main.service.OrderService;
import main.servlets.ParaName;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OrderServiceImpl implements OrderService {
	private static OrderServiceImpl orderService = new OrderServiceImpl();
	
	private OrderServiceImpl(){};
	
	public static OrderServiceImpl getInstance(){
		return orderService;
	}
	
	@Override
	public List getOrders(String userID){
		return DaoFactory.getOrderDao().selectOrders( userID );
	}
}
