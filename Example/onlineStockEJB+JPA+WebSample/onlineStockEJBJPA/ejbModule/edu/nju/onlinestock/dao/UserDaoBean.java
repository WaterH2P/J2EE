package edu.nju.onlinestock.dao;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.nju.onlinestock.model.User;


@Stateless
public class UserDaoBean implements UserDao
{
	@PersistenceContext
	protected EntityManager em;

	
	//protected Logger log = Logger.getLogger(this.getClass());

   
	/*
	 * 像数据库中插入一条user记录
	 */
	public void save(User user)
	{
		try {
			em.persist(user); //保存Entity到数据库中
		}catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	
	/*
	 * 根据参数列名和这个列上的值查找user对象,如果找到则返回这个对象,否则返回null
	 * column 列名
	 * value 列值
	 */
	public User find(String column,String value)
	{
	//
		return null;
	}
	
	/*
	 * 根据id更新user表的一条记录
	 */
	public void updateByUserid(User user)
	{
	//
		
	}
	/*
	 * 根据id查找user表的一条记录
	 */
	public User FindUserById(Long id){
		try {
			User user = em.find(User.class, id); //查找Entity
			return user;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
