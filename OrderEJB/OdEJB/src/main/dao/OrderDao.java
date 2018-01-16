package main.dao;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface OrderDao {
	
	List selectOrders(String userID);
	
}
