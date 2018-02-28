package edu.nju.onlinestock.action.business;

import java.io.Serializable;
import java.util.List;

import edu.nju.onlinestock.model.Stock;

public class StockListBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List stockList;

	
	public List getStockList() {
		return stockList;
	}

	
	public void setStockList(List stockList) {
		this.stockList = stockList;
	}
	
	
	public void setStockList(Stock stock, int index) {
		stockList.set(index, stock);
		}
	
	public Stock getStockList(int index) {
		return (Stock) stockList.get(index);
	}

}
