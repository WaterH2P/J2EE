package tickets.service.user;

import tickets.model.UserInfo;

public interface UserInfoService {
	
	UserInfo getUserInfo(String email);
	
	void changeUserName(String email, String name);
	
}
