package main.dao;

import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("clientDao")
public class ClientDaoImpl implements ClientDao {
	
	private static DaoHelper daoHelper = DaoHelperImpl.getInstance();
	
	public boolean checkLogin(String username, String password){
		
		boolean loginResult = false;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			System.out.println( username + " login");
			String userID = username;
			
			// 订单ID  商品ID / 名称 / 数量 / 单价  订单总价  订单时间  商品存货
			String sql = "SELECT password FROM userInfo WHERE userID=?";
			connection = daoHelper.getConnection();
			stmt = connection.prepareStatement( sql );
			stmt.setString(1, userID );
			
			result = stmt.executeQuery();
			if( result.next() ){
				if( password.equals(result.getString(1)) ){
					loginResult = true;
				}
			}
			
			result.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			daoHelper.closeResult( result );
			daoHelper.closePreparedStatement( stmt );
			daoHelper.closeConnection( connection );
		}
		
		return loginResult;
	}
	
}
