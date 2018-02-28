package edu.nju.userfile.dao;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.nju.userfile.model.User;



/**
 * Session Bean implementation class UserDaoBean
 */
@Stateless
public class UserDaoBean implements UserDao {
	@PersistenceContext
	protected EntityManager em;

    /**
     * Default constructor. 
     */
    public UserDaoBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean insertUser(long id, String userID, String password,
			String name, Date birthday, String phone, String email,
			String bankID, double account) {
		try{
			User user = new User();
			user.setId(id);
			user.setUserid(userID);
			user.setPassword(password);
			user.setName(name);
			user.setBirthday(birthday);
			user.setPhone(phone);
			user.setEmail(email);
			user.setBankid(bankID);
			user.setAccount(account);
			em.persist(user); //����Entity�����ݿ���
			}catch(Exception e){
			e.printStackTrace();
			return false;
			}
			return true;
	}

	@Override
	public String getUserNameByID(long id) {
		User user = em.find(User.class, id);
		return user.getName();

	}

	@Override
	public boolean updateUser(User user) {
		try{
			em.merge(user); //��������flushʱ�����ݽ�ͬ�������ݿ���
			}catch(Exception e){
			e.printStackTrace();
			return false;
			}
			return true;

	}



	@Override
	public List getUserList() {
		try{
			Query query = em.createQuery("from User u left join fetch u.files order by u.id asc");
			List list =query.getResultList();
			em.clear();//�ڴ������ʵ���ʱ����������Ѿ��������ʵ���EntityManager�з���������������Ĵ������ڴ棻�˷��������ڴ����ܹ����ʵ��Bean����VM������������
			return list;
			}catch(Exception e){
			e.printStackTrace();
			return null;
			}

	}

	@Override
	public User getUserByID(long id) {
		User user = em.find(User.class, id);
		return user;

	}

}
