package tickets.dao;

import java.sql.*;

public class DaoHelperImpl implements DaoHelper {
	
	private DaoHelperImpl(){};
	
	private static DaoHelperImpl daoHelper = new DaoHelperImpl();
	
	public static DaoHelperImpl getInstance(){
		return daoHelper;
	}
	
	private static final String URL = "jdbc:mysql://localhost:3306/Tickets?useSSL=true&characterEncoding=utf8";
	private static final String UNAME = "root";
	private static final String PWD = "hzp";
	private static Connection connect = null;
	
	static{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//加载MySQL JDBC驱动程序
			connect = DriverManager.getConnection(URL,UNAME,PWD);
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch (SQLException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public Connection getConnection(){
		return connect;
	}
	
	@Override
	public void closeConnection(Connection con){
		if(con!=null){
			try{
				con.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void closePreparedStatement(PreparedStatement stmt){
		if(stmt!=null){
			try{
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void closeResult(ResultSet result){
		if(result!=null){
			try{
				result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
