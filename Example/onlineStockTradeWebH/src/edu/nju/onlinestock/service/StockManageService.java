package edu.nju.onlinestock.service;

import java.io.IOException;
import java.sql.Date;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.onlinestock.model.Stock;



public interface StockManageService {
	
	
	public void sentErrorMessage(String message,HttpServletRequest req) 
					throws ServletException,IOException;

	public void sentMessage(String message,HttpServletRequest req) 
					throws ServletException,IOException;
	
	public void forwardPage(String page,HttpServletRequest req,HttpServletResponse resp) 
					throws ServletException,IOException;
	
	public List getStockList();
	
	public List getMyStockList(Long uId);	
	
	public Stock getStockById(String id);
	
	public void InsertStock(Stock stock);
	
	public void DeleteStock(Stock stock);
	
	public String test();

}
