package main.service;

import main.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
	private static OrderServiceImpl orderService = new OrderServiceImpl();
	
	private OrderServiceImpl(){};
	
	public static OrderServiceImpl getInstance(){
		return orderService;
	}
	
	@Resource(name = "orderDao")
	private OrderDao orderDao;
	
	@Override
	public List getOrders(String userID){
		
		return orderDao.selectOrders( userID );
	}
}
