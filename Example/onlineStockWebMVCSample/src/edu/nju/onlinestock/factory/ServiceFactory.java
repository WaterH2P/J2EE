package edu.nju.onlinestock.factory;

import edu.nju.onlinestock.service.StockManageService;
import edu.nju.onlinestock.service.impl.StockManageServiceImpl;

public class ServiceFactory {

	public static StockManageService getStockManageService()
	{
		return StockManageServiceImpl.getInstance();
	}
	
}
