package edu.nju.userfile.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(name="users")
public class User implements Serializable {
	private static final long serialVersionUID = 6664053163880103363L;
	
	private long id;
	private String userid;
	private String name;
	private String password;
	private Date birthday;
	private String email;
	private String phone;	
	private String bankid;
	private double account;
	private Set<File> files=new HashSet<File>();

	public User() {
		super();
	}
	@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE,fetch=FetchType.LAZY)
	@OrderBy(value="id ASC")
	public Set<File> getFiles(){
		return files;
	}
	public void setFiles(Set<File> files){
		this.files=files;
	}

	public void addFile(File f){
		if(! this.files.contains(f)){
		this.files.add(f);
		f.setUser(this);
		}
		}

		public void removeFile(File f){
		f.setUser(null);
		this.files.remove(f);
		}
		@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public double getAccount() {
		return account;
	}
	public void setAccount(double account) {
		this.account = account;
	}
	public String getBankid() {
		return bankid;
	}
	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
//		System.out.println(this.name);
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

}
