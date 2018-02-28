package edu.nju.onlinestock.dao;

import java.util.List;

import edu.nju.onlinestock.model.User;

public interface UserDao {

	
	public void save(User user);
	
	public List find(String column,String value);
	
	public List find(String name);
	
	public void updateById(User user);
	
	public List find();
	
	public List findType();

}
