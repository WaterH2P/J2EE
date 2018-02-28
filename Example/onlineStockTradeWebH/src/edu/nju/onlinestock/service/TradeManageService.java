package edu.nju.onlinestock.service;

import java.sql.Date;
import java.util.ArrayList;


import edu.nju.onlinestock.model.Trade;


public interface TradeManageService {
	
/**
 * 进行一次股票交易
 * @param trade
 * @return 更新后的User
 * 
 * @throws ResourceNotEnoughException 资源(钱，股票)不足时抛出该异常
 */
public String trade(Trade trade);

public String InsertTrade(Long id, int sId, Long uId, int num, Date tTime);

/**
 * 获得某一个用户的股票交易记录
 * @param userid
 * @throws RemoteException
 */
public ArrayList findTradeLogByUserid(String userid) ;

}
