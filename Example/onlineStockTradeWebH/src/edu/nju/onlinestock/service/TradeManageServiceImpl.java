package edu.nju.onlinestock.service;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import edu.nju.onlinestock.dao.TradeDao;

import edu.nju.onlinestock.model.Trade;

import edu.nju.onlinestock.factory.DaoFactory;


/**
 * Session Bean implementation class UserManageServiceBean
 */

public class TradeManageServiceImpl implements TradeManageService {
	
	private static TradeDao tradeDao=DaoFactory.getTradeDao();

	private static TradeManageServiceImpl tradeService=new TradeManageServiceImpl();
	   /**
     * Default constructor. 
     */
	private TradeManageServiceImpl()
	{
		
	}
	
	public static TradeManageService getInstance()
	{
		return tradeService;
	}

	/**
	 * 进行一次股票交易
	 * @param trade
	 * @return 更新后的User
	 * 
	 * @throws ResourceNotEnoughException 资源(钱，股票)不足时抛出该异常
	 */
	public String trade(Trade trade) {
	
		String message=null;
		System.out.println(" Ready to trade");
			
			tradeDao.insertTrade(trade);
				
			return message;
		}

	/**
	 * 获得某一个用户的股票交易记录
	 * @param userid
	 * @throws RemoteException
	 */
	public ArrayList findTradeLogByUserid(String userid) {
		return null;
	}

	
	
	public void sentErrorMessage(String message,HttpServletRequest req) 
					throws ServletException,IOException
	{
		req.setAttribute("message",message);
//		RequestDispatcher dispater=req.getRequestDispatcher(resp.encodeURL("/error/error.jsp"));
//		dispater.forward(req,resp);
	}
	

	public void sentMessage(String message,HttpServletRequest req) 
					throws ServletException,IOException
	{
		req.setAttribute("message",message);
//		RequestDispatcher dispater=req.getRequestDispatcher(resp.encodeURL("/message/message.jsp"));
//		dispater.forward(req,resp);
	}
	

	public void forwardPage(String page,HttpServletRequest req,HttpServletResponse resp) 
					throws ServletException,IOException
	{
		RequestDispatcher dispater=req.getRequestDispatcher(resp.encodeURL(page));
		dispater.forward(req,resp);
	}

	@Override
	public String InsertTrade(Long id, int sId, Long uId, int num, Date tTime) {
		// TODO Auto-generated method stub
		String message=null;
		tradeDao.insertTradeRe(id, sId, uId, num, tTime);
				
		return message;
	}




}
