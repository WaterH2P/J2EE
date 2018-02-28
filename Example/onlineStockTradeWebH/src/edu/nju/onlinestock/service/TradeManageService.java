package edu.nju.onlinestock.service;

import java.sql.Date;
import java.util.ArrayList;


import edu.nju.onlinestock.model.Trade;


public interface TradeManageService {
	
/**
 * ����һ�ι�Ʊ����
 * @param trade
 * @return ���º��User
 * 
 * @throws ResourceNotEnoughException ��Դ(Ǯ����Ʊ)����ʱ�׳����쳣
 */
public String trade(Trade trade);

public String InsertTrade(Long id, int sId, Long uId, int num, Date tTime);

/**
 * ���ĳһ���û��Ĺ�Ʊ���׼�¼
 * @param userid
 * @throws RemoteException
 */
public ArrayList findTradeLogByUserid(String userid) ;

}
