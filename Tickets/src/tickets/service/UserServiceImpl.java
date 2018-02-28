package tickets.service;

import tickets.factory.DaoFactory;
import org.springframework.stereotype.Service;
import tickets.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {
	
	private static UserServiceImpl userService = new UserServiceImpl();
	
	private UserServiceImpl(){};
	
	public static UserServiceImpl getInstance(){
		return userService;
	}
	
	private static UserDao userDao = DaoFactory.getUserDaoImpl();
	
	@Override
	public boolean login(String userID, String password){
		return userDao.loginCheck(userID, password);
	}
	
}
