package edu.nju.onlinestock.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.nju.onlinestock.dao.DaoHelper;
import edu.nju.onlinestock.dao.StockDao;
import edu.nju.onlinestock.model.Stock;

public class StockDaoImpl implements StockDao
{
	//protected Logger log = Logger.getLogger(this.getClass());
	private static StockDaoImpl stockDao=new StockDaoImpl();
	private static DaoHelper daoHelper=DaoHelperImpl.getBaseDaoInstance();
	
	private StockDaoImpl()
	{
		
	}
	
	public static StockDaoImpl getInstance()
	{
		return stockDao;
	}
	
	public Stock find(int id) {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		Stock stock=new Stock();
		try 
		{
			stmt=con.prepareStatement("select * from stock where id = ?");
			stmt.setInt(1, id);
			result=stmt.executeQuery();
			while(result.next())
			{
				stock.setId(result.getInt(1));
				stock.setCompanyName(result.getString(2));
				stock.setType(result.getString(3));
				stock.setPrice(result.getDouble(4));
				stock.setDate(result.getDate(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
		}
		return stock;
	}
	
	public void save(Stock stock)
	{
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		
		try 
		{	
			stmt=con.prepareStatement("insert into stock(id,companyName,type,price,date) values(?,?,?,?,?)");
			stmt.setInt(1,stock.getId());
			stmt.setString(2,stock.getCompanyName());
			stmt.setString(3,stock.getType());
			stmt.setDouble(4,stock.getPrice());
			stmt.setDate(5,stock.getDate());
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
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		ArrayList list=new ArrayList();
		try 
		{
			stmt=con.prepareStatement("select * from stock");
			result=stmt.executeQuery();
			while(result.next())
			{
				Stock stock=new Stock();
				stock.setId(result.getInt(1));
				stock.setCompanyName(result.getString(2));
				stock.setType(result.getString(3));
				stock.setPrice(result.getDouble(4));
				stock.setDate(result.getDate(5));
				list.add(stock);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
		}
		return list;
	}

	public List find(String column,String value)
	{
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		ArrayList list=new ArrayList();
		try 
		{
			stmt=con.prepareStatement("select * from stock where "+column+"=?");
			stmt.setString(1,value);
			result=stmt.executeQuery();
			while(result.next())
			{
				Stock stock=new Stock();
				stock.setId(result.getInt(1));
				stock.setCompanyName(result.getString(2));
				stock.setType(result.getString(3));
				stock.setPrice(result.getDouble(4));
				stock.setDate(result.getDate(5));
				list.add(stock);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
		}
		return list;
	}
	
	public List find(String name)
	{
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		ArrayList list=new ArrayList();
		try 
		{
			stmt=con.prepareStatement("select * from stock where companyName like ?");
			name="%"+name+"%";
			stmt.setString(1,name);
			result=stmt.executeQuery();
			while(result.next())
			{
				Stock stock=new Stock();
				stock.setId(result.getInt(1));
				stock.setCompanyName(result.getString(2));
				stock.setType(result.getString(3));
				stock.setPrice(result.getDouble(4));
				stock.setDate(result.getDate(5));
				list.add(stock);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
		}
		return list;
	}

	public List findType()
	{
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		ArrayList list=new ArrayList();
		try 
		{
			stmt=con.prepareStatement("select distinct type from stock");
			
			result=stmt.executeQuery();
			while(result.next())
			{
				String type=result.getString(1);
				list.add(type);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
		}
		return list;
	}
	
	
	public void updateById(Stock stock)
	{
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		try 
		{
			stmt=con.prepareStatement("update stock set companyName=?,type=?," +
						"price=?,date=? where id=?");
			stmt.setString(1,stock.getCompanyName());
			stmt.setString(2,stock.getType());
			stmt.setDouble(3,stock.getPrice());
			stmt.setDate(4,stock.getDate());
			stmt.setInt(5,stock.getId());
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




}
