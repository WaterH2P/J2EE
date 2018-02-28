package edu.nju.onlinestock.factory;

import edu.nju.onlinestock.dao.StockDao;
import edu.nju.onlinestock.dao.StockDaoImpl;
import edu.nju.onlinestock.dao.TradeDao;
import edu.nju.onlinestock.dao.TradeDaoImpl;
import edu.nju.onlinestock.dao.UserDao;
import edu.nju.onlinestock.dao.UserDaoImpl;


public class DaoFactory {
	public static UserDao getUserDao()
	{
		return UserDaoImpl.getInstance();
	}
	public static StockDao getStockDao()
	{
		return StockDaoImpl.getInstance();
	}
	public static TradeDao getTradeDao()
	{
		return TradeDaoImpl.getInstance();
	}

	
}
