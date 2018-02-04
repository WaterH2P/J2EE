package main.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {
	List getOrders(String userID);
}
