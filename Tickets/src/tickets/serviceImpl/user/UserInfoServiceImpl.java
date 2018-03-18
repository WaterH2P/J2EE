package tickets.serviceImpl.user;

import org.springframework.stereotype.Service;
import tickets.dao.mgr.MgrVIPLevelDao;
import tickets.dao.user.UserInfoDao;
import tickets.model.mgr.VIPLevelInfo;
import tickets.model.user.UserInfo;
import tickets.service.user.UserInfoService;

import javax.annotation.Resource;

@Service("userInfoService" )
public class UserInfoServiceImpl implements UserInfoService {
	
	@Resource(name = "userInfoDao" )
	private UserInfoDao userInfoDao;
	
	@Resource(name = "mgrVIPLevelDao" )
	private MgrVIPLevelDao mgrVIPLevelDao;
	
	@Override
	public UserInfo getUserInfo(String email){
		return userInfoDao.selectUserInfo(email);
	}
	
	@Override
	public void changeUserName(String email, String name){
		userInfoDao.updateUserName(email, name);
	}
	
	@Override
	public VIPLevelInfo getUserVIPDiscount(String email){
		UserInfo userInfo = userInfoDao.selectUserInfo(email);
		String vipLevel = userInfo.getVipLevel();
		System.out.println(vipLevel);
		VIPLevelInfo vipLevelInfo = mgrVIPLevelDao.selectVIPInfoByLevel(vipLevel);
		return vipLevelInfo;
	}
	
}
