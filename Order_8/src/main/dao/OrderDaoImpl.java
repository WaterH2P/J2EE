package main.dao;

import main.model.Order;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {
	
	private static DaoHelper daoHelper = DaoHelperImpl.getInstance();
	
	@Override
	public List selectOrders(String userID){
		
		ArrayList orderList = new ArrayList();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			System.out.println( userID + " check order");
			
			// 订单ID  商品ID / 名称 / 数量 / 单价  订单总价  订单时间  商品存货
			String sql = " SELECT orderID, orderInfo.goodID, goodName, goodNum, storeNum, goodPrice, totalPrice, orderDate  " +
					" FROM orderInfo, goodInfo WHERE userID=? AND orderInfo.goodID=goodInfo.goodID ";
			
			connection = daoHelper.getConnection();
			stmt = connection.prepareStatement( sql );
			stmt.setString(1, userID );
			
			result = stmt.executeQuery();
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
		
//		requset.setAttribute("orderList", orderList);
		return orderList;
	}
	
}
