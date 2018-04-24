package tickets.serviceImpl.venue;

import org.springframework.stereotype.Service;
import tickets.dao.mgr.MgrVIPLevelDao;
import tickets.dao.user.UserAccountDao;
import tickets.dao.user.UserCouponDao;
import tickets.dao.user.UserInfoDao;
import tickets.model.mgr.VIPLevelInfo;
import tickets.model.user.UserCoupon;
import tickets.model.user.UserInfo;
import tickets.service.venue.VenueOdService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("venueOdService")
public class VenueOdServiceImpl implements VenueOdService {
	
	@Resource(name = "userInfoDao" )
	private UserInfoDao userInfoDao;
	
	@Resource(name = "userAccountDao")
	private UserAccountDao userAccountDao;
	
	@Resource(name = "userCouponDao")
	private UserCouponDao userCouponDao;
	
	@Resource(name = "mgrVIPLevelDao" )
	private MgrVIPLevelDao mgrVIPLevelDao;
	
	@Override
	public VIPLevelInfo getUserVIPDiscount(String email){
		VIPLevelInfo vipLevelInfo = new VIPLevelInfo();
		if( email.length()>0 && userAccountDao.accountIsExist(email) ){
			UserInfo userInfo = userInfoDao.selectUserInfo(email);
			String vipLevel = userInfo.getVipLevel();
			vipLevelInfo = mgrVIPLevelDao.selectVIPInfoByLevel(vipLevel);
		}
		return vipLevelInfo;
	}
	
	@Override
	public List<UserCoupon> getAllUserCoupons(String email){
		List<UserCoupon> userCoupons = new ArrayList<>();
		if( email.length()>0 && userAccountDao.accountIsExist(email) ){
			userCoupons = userCouponDao.selectAllUserCoupon(email);
		}
		return userCoupons;
	}
	
}
