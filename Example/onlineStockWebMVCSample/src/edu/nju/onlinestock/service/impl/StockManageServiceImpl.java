package edu.nju.onlinestock.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.onlinestock.dao.StockDao;
import edu.nju.onlinestock.factory.DaoFactory;
import edu.nju.onlinestock.model.Stock;
import edu.nju.onlinestock.service.StockManageService;


public class StockManageServiceImpl implements StockManageService {

    /**
     * Default constructor. 
     */
/*	private static UserManageServiceBean userService=new UserManageServiceBean();
	
	private UserManageServiceBean()
	{
		
	}*/
		
	private static StockManageService stockService=new StockManageServiceImpl();
	public static StockManageService getInstance()
	{
		return stockService;
	}

	

	public List getMystock(String name) {
		// TODO Auto-generated method stub
		ArrayList list1=new ArrayList();
		list1=(ArrayList) DaoFactory.getMystockDao().findStockid(name);
		
		ArrayList list2=new ArrayList();
		
		for(int i = 0;i < list1.size(); i ++)
		{
			Stock stock=null;
			int stockId=(int) list1.get(i);
			stock=DaoFactory.getStockDao().find(stockId);
			list2.add(stock);
		}
		
		return list2;
	}
	
	
	
	public List getStock()
	{
		return DaoFactory.getStockDao().find();
				
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

	public String test(){
		System.out.println(" test");
		return "test";

	}



}
