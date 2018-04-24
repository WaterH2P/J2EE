package tickets.serviceImpl.user;

import org.springframework.stereotype.Service;
import tickets.dao.mgr.MgrCouponDao;
import tickets.dao.user.UserCouponDao;
import tickets.dao.user.UserInfoDao;
import tickets.model.mgr.CouponInfo;
import tickets.model.user.UserCoupon;
import tickets.model.user.UserInfo;
import tickets.service.user.UserCouponService;

import javax.annotation.Resource;
import java.util.List;

@Service("userCouponService")
public class UserCouponServiceImpl implements UserCouponService {
	
	@Resource(name = "userCouponDao")
	private UserCouponDao userCouponDao;
	
	@Resource(name = "userInfoDao")
	private UserInfoDao userInfoDao;
	
	@Resource(name = "mgrCouponDao")
	private MgrCouponDao mgrCouponDao;
	
	@Override
	public List<UserCoupon> getAllUserCoupons(String email){
		return userCouponDao.selectAllUserCoupon(email);
	}
	
	@Override
	public boolean exchangeCoupon(String email, String couponID){
		UserInfo userInfo = userInfoDao.selectUserInfo(email);
		CouponInfo couponInfo = mgrCouponDao.selectCouponInfo(couponID);
		int point = couponInfo.getPoint();
		if( userInfo.getPoint()>point ){
			userCouponDao.insertOneUserCoupon(email, couponID);
			return userInfoDao.updateUserPoint(email, -point);
		}
		return false;
	}
	
	@Override
	public List<CouponInfo> getAllCouponInfosExchanged(){
		return mgrCouponDao.selectAllCouponInfosExchanged();
	}
	
}
