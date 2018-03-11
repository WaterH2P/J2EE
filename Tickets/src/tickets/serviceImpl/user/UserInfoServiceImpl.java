package tickets.serviceImpl.user;

import org.springframework.stereotype.Service;
import tickets.dao.user.UserInfoDao;
import tickets.model.UserInfo;
import tickets.service.user.UserInfoService;

import javax.annotation.Resource;

@Service("userInfoService" )
public class UserInfoServiceImpl implements UserInfoService {
	
	@Resource(name = "userInfoDao" )
	private UserInfoDao userInfoDao;
	
	@Override
	public UserInfo getUserInfo(String email){
		return userInfoDao.selectUserInfo(email);
	}
	
	@Override
	public void changeUserName(String email, String name){
		userInfoDao.updateUserName(email, name);
	}
	
}
