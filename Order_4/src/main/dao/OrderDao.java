package main.dao;

import java.util.List;

public interface OrderDao {
	
	List selectOrders(String userID);
	
}
