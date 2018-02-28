package edu.nju.userfile.dao;
import java.sql.Date;
import java.util.List;

import javax.ejb.Remote;

import edu.nju.userfile.model.User;



@Remote
public interface UserDao {
	public boolean insertUser(long id,String userID, String password, String name, Date birthday, String phone, String email,String bankID,double account);
	public String getUserNameByID(long id);
	public boolean updateUser(User user);
	public User getUserByID(long id);
	public List getUserList();

}
