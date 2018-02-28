package edu.nju.onlinestock.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: Stock
 *
 */
@Entity
@Table(name="stock")
public class Stock implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Stock() {
		super();
	}

	private String id;
	private String companyName;
	private String type;
	private double price;
	private Date date;
	private Set<User> users = new HashSet<User>();



	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name="userstock",  //"trade"表只包含2个外键，“userstock”表还包含其他字段
			joinColumns=
			@JoinColumn(name="stockid", referencedColumnName="id"),
			inverseJoinColumns=
			@JoinColumn(name="userid", referencedColumnName="id")
			)
	public Set<User> getUsers(){
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users=users;
		}
	
	@Id
	public String getId() {
		return id;
	}	
	public void setId(String string) {
		this.id = string;
	}

	public String getCompanyName() {
		return companyName;
	}	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getType() {
		return type;
	}	
	public void setType(String type) {
		this.type = type;
	}
	
	public double getPrice() {
		return price;
	}	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Date getDate() {
		return date;
	}	
	public void setDate(Date date) {
		this.date = date;
	}
}
