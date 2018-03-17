package tickets.serviceImpl.mgr;

import org.springframework.stereotype.Service;
import tickets.dao.mgr.MgrCouponDao;
import tickets.model.mgr.CouponInfo;
import tickets.service.mgr.MgrCouponService;
import tickets.serviceImpl.CommonService;

import javax.annotation.Resource;
import java.util.List;

@Service("mgrCouponService")
public class MgrCouponServiceImpl implements MgrCouponService {
	
	@Resource(name = "mgrCouponDao")
	private MgrCouponDao mgrCouponDao;
	
	@Override
	public String addNewCoupon(CouponInfo couponInfo){
		List<String> couponIDs = mgrCouponDao.selectAllCouponIDs();
		final int couponIDLen = 7;
		String couponID = CommonService.getRandomString(couponIDLen, couponIDs);
		couponInfo.setCouponID(couponID);
		mgrCouponDao.insertNewCoupon(couponInfo);
		return couponID;
	}
	
	@Override
	public void deleteCoupon(String couponID){
		mgrCouponDao.deleteCoupon(couponID);
	}
	
	@Override
	public List<CouponInfo> getAllCouponInfos(){
		return mgrCouponDao.selectAllCouponInfosExchanged();
	}
}
