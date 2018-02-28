package tickets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
	
	private static UserDaoImpl userDao = new UserDaoImpl();
	
	private UserDaoImpl(){};
	
	public static UserDaoImpl getInstance(){
		return userDao;
	}
	
	@Override
	public boolean loginCheck(String userID, String password){
		boolean loginResult = false;
		
		DaoHelperImpl daoHelper = DaoHelperImpl.getInstance();
		Connection connection = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		String table = "userInfo";
		String sql = "SELECT password FROM " + table + " WHERE userID=?";
		try{
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, userID);
			
			result = stmt.executeQuery();
			if( result.next() ){
				if( password.equals(result.getString(1)) ){
					loginResult = true;
				}
				else{
					loginResult = false;
				}
			}
		}catch( SQLException e ){
			System.out.println(userID + " loginCheck cause a failure");
			loginResult = false;
		}finally{
			daoHelper.closeResult(result);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeConnection(connection);
		}
		return loginResult;
	}
	
}
