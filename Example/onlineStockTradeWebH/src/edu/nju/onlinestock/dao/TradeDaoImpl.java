package edu.nju.onlinestock.dao;

import java.sql.Date;
import java.util.List;



import edu.nju.onlinestock.dao.TradeDao;
import edu.nju.onlinestock.model.Stock;
import edu.nju.onlinestock.model.Trade;
import edu.nju.onlinestock.model.User;



public class TradeDaoImpl extends BaseDaoImpl implements TradeDao{
	private static TradeDaoImpl tradeDao = new TradeDaoImpl();

	public static TradeDaoImpl getInstance() {
		return tradeDao;
	}

    /**
     * Default constructor. 
     */
    public TradeDaoImpl() {
        // TODO Auto-generated constructor stub
    }

	
    @Override
	public void insertTrade(Trade trade) {
    	super.save(trade);
	}
    
	@Override
	public void updateTrade(Trade trade) {
		//
	}

	@Override
	public Trade getTradeByID(String id) {
		Trade trade= (Trade) this.load(Trade.class, id);
		return trade;
	}

	@Override
	public List getTradeByUser(User user) {
		/*try{
			Query query = em.createQuery("from Trade t where t.user=user order by id asc");
			List list =query.getResultList();
			em.clear();//在处理大量实体的时候，如果不把已经处理过的实体从EntityManager中分离出来，将会消耗大量的内存；此方法分离内存中受管理的实体Bean，让VM进行垃圾回收
			return list;
			}catch(Exception e){
			e.printStackTrace();
			return null;
			}*/
		return null;
	}

	@Override
	public List gettradeList() {
		return null;
	}

	@Override
	public void insertTradeRe(Long id, int sId, Long uId, int num, Date tTime) {
		// TODO Auto-generated method stub
		/*try{
			Query query = em.createNativeQuery("insert into mystock(id, stockId,userId,number,date) values(?,?,?,?,?)");
			query.setParameter(1, id);
			query.setParameter(2, sId);	
			query.setParameter(3, uId);
			query.setParameter(4, num);
			query.setParameter(5, tTime);
			query.executeUpdate();
			//保存Entity到数据库中
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;*/
		
	}
}
