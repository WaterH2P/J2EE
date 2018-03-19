package tickets.service.venue;

import tickets.model.mgr.VIPLevelInfo;
import tickets.model.user.UserCoupon;

import java.util.List;

public interface VenueOdService {
	
	VIPLevelInfo getUserVIPDiscount(String email);
	
	List<UserCoupon> getAllUserCoupons(String email);
	
}
