package main.dao;

import main.model.Order;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface OrderDao {
	
	List selectOrders(String userID);
	
	Order findOdByID(String id);
}