package tickets.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;

@Repository("daoHelper")
public class DaoHelperImpl implements DaoHelper {
	
	private static DataSource datasource;
	private static ApplicationContext ctx;
	private static JdbcTemplate jdbcTemplate;
	static {
		InitialContext jndiContext = null;

		Properties properties = new Properties();
		properties.put(javax.naming.Context.PROVIDER_URL, "jnp:///");
		properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
		try {
			jndiContext = new InitialContext(properties);
			datasource = (DataSource) jndiContext.lookup("java:comp/env/jdbc/Tickets");
			jdbcTemplate = new JdbcTemplate(datasource);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static JdbcTemplate getJdbcTemplate(){
		return jdbcTemplate;
	}
	
	@Override
	public Connection getConnection(){
		Connection connection = null;
		try {
			connection = datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
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
