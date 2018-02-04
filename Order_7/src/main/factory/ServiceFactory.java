package main.factory;

import main.service.ClientService;
import main.service.OrderService;
import main.service.ClientServiceImpl;
import main.service.OrderServiceImpl;

public class ServiceFactory {
	
	public static ClientService getClientService(){
		return ClientServiceImpl.getInstance();
	}
	
	public static OrderService getOrderService(){
		return OrderServiceImpl.getInstance();
	}
}
