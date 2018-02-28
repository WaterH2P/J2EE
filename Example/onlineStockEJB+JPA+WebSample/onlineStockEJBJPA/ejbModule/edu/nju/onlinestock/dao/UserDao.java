package edu.nju.onlinestock.dao;

import javax.ejb.Local;
import javax.ejb.Remote;

import edu.nju.onlinestock.model.User;
@Remote
public interface UserDao
{
	/*
	 * 像数据库中插入一条user记录
	 */
	public void save(User user);
	
	
	/*
	 * 根据参数列名和这个列上的值查找user对象,如果找到则返回这个对象,否则返回null
	 * column 列名
	 * value 列值
	 */
	public User find(String column,String value);
	
	
	/*
	 * 根据id更新user表的一条记录
	 */
	public void updateByUserid(User user);
	
	/*
	 * 根据id查找user表的一条记录
	 */
	public User FindUserById(Long id);
	
}
