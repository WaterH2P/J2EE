package main.service;

import main.factory.DaoFactory;

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
