package edu.nju.onlinestock.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import edu.nju.onlinestock.dao.BaseDao;
import edu.nju.onlinestock.utils.HibernateUtil;


public class BaseDaoImpl implements BaseDao {
	
	public BaseDaoImpl() {
	}

	public void flush() {
		HibernateUtil.getSession().flush();
	}

	public void clear() {
		HibernateUtil.getSession().clear();
	}

	/** * 保存 * * @param bean * */
	public void save(Object bean) {
		try {
			System.out.println("ready to getsession");	
			Session session =HibernateUtil.getSession() ;
			Transaction tx=session.beginTransaction();
			session.merge(bean);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	
	/** * 根据 id 查询信息 * * @param id * @return */
	@SuppressWarnings("rawtypes")
	public Object load(Class c, String id) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			Object o=session.get(c, id);
			tx.commit();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	/** * 获取所有信息 * * @param c * * @return */

	public List getAllList(Class c) {
		return null;
		//

	}

	/** * 获取总数量 * * @param c * @return */

	public Long getTotalCount(Class c) {
		//
		return null;
	}



	/** * 更新 * * @param bean * */
	public void update(Object bean) {
		//

	}

	/** * 删除 * * @param bean * */
	public void delete(Object bean) {

		try {
		//	System.out.println("ready to getsession");	
			Session session =HibernateUtil.getSession() ;
			Transaction tx=session.beginTransaction();
			session.delete(bean);;
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/** * 根据ID删除 * * @param c 类 * * @param id ID * */
	@SuppressWarnings({ "rawtypes" })
	public void delete(Class c, String id) {
		//
	}

	/** * 批量删除 * * @param c 类 * * @param ids ID 集合 * */
	@SuppressWarnings({ "rawtypes" })
	public void delete(Class c, String[] ids) {
		//
	}
}
