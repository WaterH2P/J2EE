package edu.nju.onlinestock.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.onlinestock.dao.StockDao;
import edu.nju.onlinestock.factory.DaoFactory;
import edu.nju.onlinestock.model.Stock;

/**
 * Session Bean implementation class UserManageServiceBean
 */

public class StockManageServiceImpl implements StockManageService {


	private static StockDao stockDao=DaoFactory.getStockDao();
	
	private static StockManageServiceImpl stockService=new StockManageServiceImpl();
    /**
     * Default constructor. 
     */	
	private StockManageServiceImpl()
	{
		
	}
	
	public static StockManageService getInstance()
	{
		return stockService;
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

	@Override
	public List getStockList(){
		return stockDao.getStockList();
	}


	@Override
	public Stock getStockById(String id) {
		return stockDao.getStockByID(id);
	}


	@Override
	public List getMyStockList(Long uId) {
		// TODO Auto-generated method stub
		return stockDao.getStockByUserId(uId);
	}

	@Override
	public void InsertStock(Stock stock) {
		// TODO Auto-generated method stub
		stockDao.save(stock);
	}

	@Override
	public void DeleteStock(Stock stock) {
		// TODO Auto-generated method stub
		stockDao.delete(stock);
	}


}
