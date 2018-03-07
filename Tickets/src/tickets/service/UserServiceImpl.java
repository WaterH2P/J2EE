package tickets.service;

import org.springframework.stereotype.Service;
import tickets.dao.UserDao;
import tickets.model.UserInfo;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource(name = "userDao")
	private UserDao userDao;
	
	@Override
	public UserInfo getUserInfo(String email){
		return userDao.selectUserInfo(email);
	}
	
	@Override
	public void changeUserName(String email, String name){
		userDao.updateUserName(email, name);
	}
	
}
