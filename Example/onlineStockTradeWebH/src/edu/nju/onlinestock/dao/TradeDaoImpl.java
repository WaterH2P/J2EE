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
			em.clear();//�ڴ������ʵ���ʱ����������Ѿ��������ʵ���EntityManager�з���������������Ĵ������ڴ棻�˷��������ڴ����ܹ����ʵ��Bean����VM������������
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
			//����Entity�����ݿ���
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;*/
		
	}
}
