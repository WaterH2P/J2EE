package tickets.factory;

import tickets.dao.UserDao;
import tickets.dao.UserDaoImpl;

public class DaoFactory {
	
	public static UserDao getUserDaoImpl(){
		return UserDaoImpl.getInstance();
	}
	
}
