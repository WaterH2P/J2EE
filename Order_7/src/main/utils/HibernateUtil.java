package main.utils;

import main.model.Good;
import main.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory = null;
	
	static{
		try {
			Configuration config = new Configuration().configure();
			// 编程配置映射
			config.addAnnotatedClass(Order.class);
			config.addAnnotatedClass(Good.class);
			
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			sessionFactory = config.buildSessionFactory(serviceRegistry);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* gerCurrentSession 会自动关闭 session，使用的是当前的 session 事务 @return */
	public static Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
}
