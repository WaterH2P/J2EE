package main.javabean;

import java.io.Serializable;

public class UserCountBean implements Serializable {
	
	private int total = 0;
	private int online = 0;
	private int visitor = 0;
	
	public int getTotal(){
		return total;
	}
	
	public int getOnline(){
		return online;
	}
	
	public int getVisitor(){
		return visitor;
	}
	
	public void setTotal(int total){
		this.total = total;
	}
	
	public void setOnline(int online){
		this.online = online;
	}
	
	public void setVisitor(int visitor){
		this.visitor = visitor;
	}
	
	public void totalAddOne(){
		this.total++;
	}
	public void onlineAddOne(){
		this.online++;
	}
	public void visitorAddOne(){
		this.visitor++;
	}
	
	public void totalDeleteOne(){
		this.total--;
	}
	public void onlineDeleteOne(){
		this.online--;
	}
	public void visitorDeleteOne(){
		this.visitor--;
	}
}
