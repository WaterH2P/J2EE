package edu.nju.onlinestock.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="userstock")
public class Trade implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Trade() {
		super();
	}

	private Integer id;
	private User user;
	private Stock stock;
//	private String type;
	private int number;
//	private double price;
	private Date date;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}	
	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userid")
	public User getUser(){
		return user;
	}
	public void setUser(User user) {
		this.user=user;
	}

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="stockid")
	public Stock getStock(){
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock=stock;
	}
	
	
	
/*	public String getType() {
		return type;
	}	
	public void setType(String type) {
		this.type = type;
	}*/
	@Column(name="number", nullable=true)
	public int getNumber() {
		return number;
	}	
	public void setNumber(int number) {
		this.number = number;
	}
	
	/*public double getPrice() {
		return price;
	}	
	public void setPrice(double price) {
		this.price = price;
	}*/
	
	public Date getDate() {
		return date;
	}	
	public void setDate(Date date) {
		this.date = date;
	}
}
