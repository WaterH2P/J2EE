package main.dao.impl;

import main.dao.ClientDao;
import main.model.Order;
import main.servlets.ParaName;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class ClientDaoImpl implements ClientDao {
	
	private static ClientDaoImpl daoClient = new ClientDaoImpl();
	
	private ClientDaoImpl(){};
	
	public static ClientDaoImpl getInstance(){
		return daoClient;
	}
	
	public boolean checkLogin(String username, String password){
		Connection connection = DaoSource.getConnection();
		boolean loginResult = false;
		
		try {
			System.out.println( username + " login");
			String userID = username;
			
			// 订单ID  商品ID / 名称 / 数量 / 单价  订单总价  订单时间  商品存货
			String sql = "SELECT password FROM userInfo WHERE userID=?";
			PreparedStatement stmt = connection.prepareStatement( sql );
			stmt.setString(1, userID );
			
			ResultSet result = stmt.executeQuery();
			if( result.next() ){
				if( password.equals(result.getString(1)) ){
					loginResult = true;
				}
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return loginResult;
	}
	
	public void getOrders(HttpServletRequest requset) {
	
	}
}
