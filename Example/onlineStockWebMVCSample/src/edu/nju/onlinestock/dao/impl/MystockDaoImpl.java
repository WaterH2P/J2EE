package edu.nju.onlinestock.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.nju.onlinestock.dao.DaoHelper;
import edu.nju.onlinestock.dao.MystockDao;


public class MystockDaoImpl implements MystockDao
{
	//protected Logger log = Logger.getLogger(this.getClass());
	private static MystockDaoImpl mystockDao=new MystockDaoImpl();
	private static DaoHelper daoHelper=DaoHelperImpl.getBaseDaoInstance();

	private MystockDaoImpl()
	{
		
	}
	
	public static MystockDaoImpl getInstance()
	{
		return mystockDao;
	}
	

	
	public List findStockid(String name)
	{
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		ArrayList list=new ArrayList();
		try 
		{
			stmt=con.prepareStatement("select stockid from mystock where userid = ?");
			stmt.setString(1,name);
			result=stmt.executeQuery();
			while(result.next())
			{
				list.add(result.getInt("stockid"));
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


}
