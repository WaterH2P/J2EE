package main.service;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface OrderService {
	List getOrders(String userID);
}
