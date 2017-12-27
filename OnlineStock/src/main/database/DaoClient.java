package main.database;

import main.model.Order;
import main.servlets.ParaName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class DaoClient {
	
	private DataSource datasource = DaoSource.getInstance();
	
	public boolean checkLogin(String username, String password){
		Connection connection = getConnection();
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
		Connection connection = getConnection();

		ArrayList orderList = new ArrayList();
		try {
			String userID = (String)requset.getAttribute( ParaName.reqUserName );
			System.out.println( userID + " check order");
			
			// 订单ID  商品ID / 名称 / 数量 / 单价  订单总价  订单时间  商品存货
			String sql = " SELECT orderID, orderInfo.goodID, goodName, goodNum, storeNum, goodPrice, totalPrice, orderDate  " +
					" FROM orderInfo, goodInfo WHERE userID=? AND orderInfo.goodID=goodInfo.goodID ";
			PreparedStatement stmt = connection.prepareStatement( sql );
			stmt.setString(1, userID );
			
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				Order order = new Order();
				order.setOrderID( result.getString(1) );
				order.setGoodID( result.getString(2) );
				order.setGoodName( result.getString(3) );
				order.setGoodNum( result.getInt(4) );
				order.setStoreNum( result.getInt(5) );
				order.setGoodPrice( result.getFloat(6) );
				order.setTotalPrice( result.getFloat(7) );
				order.setOrderDate( result.getString(8) );
				
				orderList.add(order);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		requset.setAttribute("orderList", orderList);
	}
	
	private Connection getConnection(){
		Connection connection = null;
		try {
			connection = datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
