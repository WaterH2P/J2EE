package edu.nju.onlinestock.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.nju.onlinestock.model.User;
@Repository
public class UserDaoImpl implements UserDao
{
	//protected Logger log = Logger.getLogger(this.getClass());
	
	private static DaoHelper daoHelper=DaoHelperImpl.getBaseDaoInstance();
	
	/*private static UserDaoImpl userDao=new UserDaoImpl();
	private UserDaoImpl()
	{
		
	}
	
	public static UserDaoImpl getInstance()
	{
		return userDao;
	}*/
	
	
	public void save(User user)
	{
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		
		try 
		{	
			stmt=con.prepareStatement("insert into users(id,userid,password,name,birthday,phone,email,bankid,account) values(?,?,?,?,?,?,?,?,?)");
			stmt.setLong(1,user.getId());
			stmt.setString(2,user.getUserid());
			stmt.setString(3,user.getPassword());
			stmt.setString(4,user.getName());
			stmt.setDate(5,user.getBirthday());
			stmt.setString(6,user.getPhone());
			stmt.setString(7,user.getEmail());
			stmt.setString(8,user.getBankid());
			stmt.setDouble(9,user.getAccount());
			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
		}
	}
	
	public List find()
	{
		return null;
	}

	public List find(String column,String value)
	{
		return null;
	}
	
	public List find(String name)
	{
		return null;
	}

	public List findType()
	{
		return null;
	}
	
	
	public void updateById(User user)
	{
				
	}


}
