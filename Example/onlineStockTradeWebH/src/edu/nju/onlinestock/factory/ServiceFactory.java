package edu.nju.onlinestock.factory;

import edu.nju.onlinestock.service.StockManageService;
import edu.nju.onlinestock.service.StockManageServiceImpl;
import edu.nju.onlinestock.service.TradeManageService;
import edu.nju.onlinestock.service.TradeManageServiceImpl;
import edu.nju.onlinestock.service.UserManageService;
import edu.nju.onlinestock.service.UserManageServiceImpl;

public class ServiceFactory {

	public static UserManageService getUserManageService()
	{
		return UserManageServiceImpl.getInstance();
	}
	public static StockManageService getStockManageService()
	{
		return StockManageServiceImpl.getInstance();
	}
	public static TradeManageService getTradeManageService()
	{
		return TradeManageServiceImpl.getInstance();
	}
	

}
