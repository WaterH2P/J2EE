package tickets.dao.mgr;

import tickets.model.mgr.CouponInfo;

import java.util.List;

public interface MgrCouponDao {
	
	void insertNewCoupon(CouponInfo couponInfo);
	
	void deleteCoupon(String couponID);
	
	List<String> selectAllCouponIDs();
	
	List<CouponInfo> selectAllCouponInfosExist();
	
}
