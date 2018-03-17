package tickets.dao.user;

import tickets.model.user.UserCoupon;

import java.util.List;

public interface UserCouponDao {
	
	List<UserCoupon> selectAllUserCoupon(String email);
	
	void insertUserCoupon(String email, String couponID);
	
	boolean deleteUserCoupon(String email, String couponID);
	
}
