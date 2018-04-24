package tickets.service.user;

import tickets.model.mgr.VIPLevelInfo;
import tickets.model.user.UserInfo;

public interface UserInfoService {
	
	UserInfo getUserInfo(String email);
	
	void changeUserName(String email, String name);
	
	VIPLevelInfo getUserVIPDiscount(String email);
	
	void updateUserVIPLevel(String email);
	
}
