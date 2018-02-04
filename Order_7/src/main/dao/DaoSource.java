package main.dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DaoSource {

//	private static final String URL = "jdbc:mysql://localhost:3306/J2EE?useSSL=true&characterEncoding=utf8";
//	private static final String UNAME = "root";
//	private static final String PWD = "hzp";
//	private static Connection connect = null;
//	static{
//		try{
//			Class.forName("com.mysql.jdbc.Driver");
//			//加载MySQL JDBC驱动程序
//			connect = DriverManager.getConnection(URL,UNAME,PWD);
//		}catch (ClassNotFoundException e){
//			e.printStackTrace();
//		}catch (SQLException e){
//			e.printStackTrace();
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//	}
//
//	protected static Connection getInstance(){
//		return connect;
//	}
	
	private static DataSource datasource = null;
	static {
		InitialContext jndiContext = null;
		
		Properties properties = new Properties();
		properties.put(javax.naming.Context.PROVIDER_URL, "jnp:///");
		properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
		try {
			jndiContext = new InitialContext(properties);
			datasource = (DataSource) jndiContext.lookup("java:comp/env/jdbc/onlinestock");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected static DataSource getInstance(){
		return datasource;
	}
	
	protected static Connection getConnection(){
		Connection connection = null;
		try {
			connection = datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
