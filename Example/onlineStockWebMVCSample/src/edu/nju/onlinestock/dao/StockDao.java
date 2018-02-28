package edu.nju.onlinestock.dao;

import java.util.List;

import edu.nju.onlinestock.model.Stock;

public interface StockDao {

	public Stock find(int id);
	
	public void save(Stock stock);
	
	public List find(String column,String value);
	
	public List find(String name);
	
	public void updateById(Stock stock);
	
	public List find();
	
	public List findType();

}
