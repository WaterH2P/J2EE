package edu.nju.onlinestock.factory;

import edu.nju.onlinestock.dao.MystockDao;
import edu.nju.onlinestock.dao.StockDao;
import edu.nju.onlinestock.dao.impl.MystockDaoImpl;
import edu.nju.onlinestock.dao.impl.StockDaoImpl;


public class DaoFactory {
/*	public static UserDao getUserDao()
	{
		return UserDaoImpl.getInstance();
	}*/
	public static StockDao getStockDao()
	{
		return StockDaoImpl.getInstance();
	}
	
	public static MystockDao getMystockDao()
	{
		return MystockDaoImpl.getInstance();
	}
	/*	public static TradeDao getTradeDao()
	{
		return TradeDaoImpl.getInstance();
	}*/
}
