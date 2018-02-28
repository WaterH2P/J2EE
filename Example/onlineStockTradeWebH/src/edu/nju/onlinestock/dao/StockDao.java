package edu.nju.onlinestock.dao;

import java.util.List;

import edu.nju.onlinestock.model.Stock;

public interface StockDao extends BaseDao{
	public void insertStock(Stock stock);
	public void deleteStock(Stock stock);
	public Stock getStockByID(String id);
	
	public String getStockNameByID(int id);
	public void updateStock(Stock stock);

	public List getStockList();
	public List getStockByUserId(Long uId);
}
