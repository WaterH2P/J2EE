package edu.nju.onlinestock.dao;


import java.util.List;


import edu.nju.onlinestock.dao.StockDao;
import edu.nju.onlinestock.model.Stock;


public class StockDaoImpl extends BaseDaoImpl implements StockDao{
/*	private Configuration config;
	private ServiceRegistry serviceRegistry;
	private SessionFactory sessionFactory;
	private Session session;*/
	private static StockDaoImpl stockDao = new StockDaoImpl();

	private StockDaoImpl() {

	}

	public static StockDaoImpl getInstance() {
		return stockDao;
	}

	@Override
	public void insertStock(Stock stock) {
		super.save(stock);
	}

	@Override
	public String getStockNameByID(int id) {
		String sId=Integer.toString(id);
		Stock stock= (Stock) super.load(Stock.class, sId);
		return stock.getCompanyName();
	}

	@Override
	public void updateStock(Stock stock) {
		//
	}

	@Override
	public Stock getStockByID(String id) {
		Stock stock= (Stock) super.load(Stock.class, id);
		return stock;
	}

	@Override
	public List getStockList() {
				return null;
			
	}

	@Override
	public List getStockByUserId(Long uId) {
		// TODO Auto-generated method stub
				return null;

	}

	@Override
	public void deleteStock(Stock stock) {
		// TODO Auto-generated method stub
		super.delete(stock);
	}

}
