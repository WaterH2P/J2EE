package tickets.service.user;

import tickets.model.mgr.CouponInfo;
import tickets.model.user.UserCoupon;

import java.util.List;

public interface UserCouponService {
	
	List<UserCoupon> getAllUserCoupons(String email);
	
	boolean exchangeCoupon(String email, String couponID);
	
	List<CouponInfo> getAllCouponInfosExchanged();
	
}
