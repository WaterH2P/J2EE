package main.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="orderInfo")
public class Order implements Serializable {
	private String orderID;
	private String goodID;
	private String goodName;
	private int goodNum;
	private int storeNum;
	private float goodPrice;
	private float totalPrice;
	private String orderDate;
	
	public void setOrderID(String orderID){
		this.orderID = orderID;
	}
	
	public void setGoodID(String goodID){
		this.goodID = goodID;
	}
	
	public void setGoodName(String goodName){
		this.goodName = goodName;
	}
	
	public void setGoodNum(int goodNum){
		this.goodNum = goodNum;
	}
	
	public void setStoreNum(int storeNum){
		this.storeNum = storeNum;
	}
	
	public void setGoodPrice(float goodPrice){
		this.goodPrice = goodPrice;
	}
	
	public void setTotalPrice(float totalPrice){
		this.totalPrice = totalPrice;
	}
	
	public void setOrderDate(String orderDate){
		this.orderDate = orderDate;
	}
	
	
	@Id
	public String getOrderID(){
		return orderID;
	}
	
	public String getGoodID(){
		return goodID;
	}
	
	public String getGoodName(){
		return goodName;
	}
	
	public int getGoodNum(){
		return goodNum;
	}
	
	public int getStoreNum(){
		return storeNum;
	}
	
	public float getGoodPrice(){
		return goodPrice;
	}
	
	public float getTotalPrice(){
		return totalPrice;
	}
	
	public String getOrderDate(){
		return orderDate;
	}
}
