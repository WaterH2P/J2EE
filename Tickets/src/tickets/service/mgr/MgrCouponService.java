package tickets.service.mgr;

import tickets.model.mgr.CouponInfo;

import java.util.List;

public interface MgrCouponService {
	
	/*
	return couponID
	 */
	String addNewCoupon(CouponInfo couponInfo);
	
	void deleteCoupon(String couponID);
	
	List<CouponInfo> getAllCouponInfos();
}
