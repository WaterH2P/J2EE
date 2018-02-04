package main.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "goodInfo")
public class Good implements Serializable {
	
	public Good(){
		super();
	}
	
	private String goodID;
	private int goodName;
	private float goodPrice;
	private int storeNum;
	
	public void setGoodID(String goodID){
		this.goodID = goodID;
	}
	
	public void setGoodName(int goodName){
		this.goodName = goodName;
	}
	
	public void setGoodPrice(float goodPrice){
		this.goodPrice = goodPrice;
	}
	
	public void setStoreNum(int storeNum){
		this.storeNum = storeNum;
	}
	
	@Id
	public String getGoodID(){
		return goodID;
	}
	
	public int getGoodName(){
		return goodName;
	}
	
	public float getGoodPrice(){
		return goodPrice;
	}
	
	public int getStoreNum(){
		return storeNum;
	}
}
