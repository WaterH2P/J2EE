package main.javabean;

import java.io.Serializable;
import java.util.List;

public class OrderListBean implements Serializable {
	
	private List orders;
	
	public List getOrders(){
		return orders;
	}
	
	public void setOrders(List orders){
		this.orders = orders;
	}
}
