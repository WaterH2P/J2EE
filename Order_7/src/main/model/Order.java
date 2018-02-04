package main.model;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "orderInfo")
public class Order implements Serializable {
	
	public Order(Object[] object){
		super();
		if( object.length!=8 ){
			orderID = (String) object[0];
			userID = (String) object[1];
			goodID = (String) object[2];
			goodName = (String) object[3];
			goodNum = (int) object[4];
			storeNum = (int) object[5];
			goodPrice = (float) object[6];
			totalPrice = (float) object[7];
			orderDate = (String) object[8];
		}
	}
	
	private String orderID = "";
	private String userID = "";
	private String goodID = "";
	private String goodName = "";
	private int goodNum;
	private int storeNum;
	private float goodPrice;
	private float totalPrice;
	private String orderDate = "";
	
	public void setOrderID(String orderID){
		this.orderID = orderID;
	}
	
	public void setUserID(String userID){
		this.userID = userID;
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
	
	public String getUserID(){
		return userID;
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
