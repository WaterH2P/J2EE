package main.dao;

import main.model.Order;
import main.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
	
	private static OrderDaoImpl orderDao = new OrderDaoImpl();
	
	private OrderDaoImpl(){};
	
	public static OrderDaoImpl getInstance(){
		return orderDao;
	}
	
//	@Override
//	public List selectOrders(String userID){
//		Connection connection = DaoSource.getConnection();
//
//		ArrayList orderList = new ArrayList();
//		try {
//			System.out.println( userID + " check order");
//
//			// 订单ID  商品ID / 名称 / 数量 / 单价  订单总价  订单时间  商品存货
//			String sql = " SELECT orderID, orderInfo.goodID, goodName, goodNum, storeNum, goodPrice, totalPrice, orderDate  " +
//					" FROM orderInfo, goodInfo WHERE userID=? AND orderInfo.goodID=goodInfo.goodID ";
//			PreparedStatement stmt = connection.prepareStatement( sql );
//			stmt.setString(1, userID );
//
//			ResultSet result = stmt.executeQuery();
//			while (result.next()) {
//				Order order = new Order();
//				order.setOrderID( result.getString(1) );
//				order.setGoodID( result.getString(2) );
//				order.setGoodName( result.getString(3) );
//				order.setGoodNum( result.getInt(4) );
//				order.setStoreNum( result.getInt(5) );
//				order.setGoodPrice( result.getFloat(6) );
//				order.setTotalPrice( result.getFloat(7) );
//				order.setOrderDate( result.getString(8) );
//
//				orderList.add(order);
//			}
//			connection.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
////		requset.setAttribute("orderList", orderList);
//		return orderList;
//	}
	
	@Override
	public List selectOrders(String userID){
		Session session = HibernateUtil.getSession();
		String hql =  "SELECT Od.orderID, Od.userID, Od.goodID, Gd.goodName, Od.goodNum, Gd.storeNum, Gd.goodPrice, Od.totalPrice, Od.orderDate " +
					" FROM main.model.Order as Od, main.model.Good as Gd " +
					" WHERE Od.userID=? AND Od.goodID=Gd.goodID";
		Query query = session.createQuery(hql);
		query.setParameter(0, userID);
		
		List<Object[]> objects = query.list();
		ArrayList<Order> orders = new ArrayList<>();
		for( Object[] object : objects ){
			orders.add( new Order(object) );
		}
		return  orders;
	}
}
