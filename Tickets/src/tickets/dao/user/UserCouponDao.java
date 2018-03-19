package tickets.dao.user;

import tickets.model.user.UserCoupon;

import java.util.List;

public interface UserCouponDao {
	
	List<UserCoupon> selectAllUserCoupon(String email);
	
	void insertOneUserCoupon(String email, String couponID);
	
	boolean deleteOneUserCoupon(String email, String couponID);
	
}
