package edu.nju.onlinestock.dao;

import java.sql.Date;
import java.util.List;


import edu.nju.onlinestock.model.Stock;
import edu.nju.onlinestock.model.Trade;
import edu.nju.onlinestock.model.User;


public interface TradeDao  extends BaseDao{
	//public boolean insertTrade(String id,User user, Stock stock, int number, Date date);
	public void insertTrade(Trade trade);
	public void insertTradeRe(Long id, int sId, Long uId, int num, Date tTime);
	public void updateTrade(Trade trade);
	public Trade getTradeByID(String id);
	public List getTradeByUser(User user);	
	public List gettradeList();

}
