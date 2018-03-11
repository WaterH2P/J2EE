package tickets.dao.user;

import tickets.model.UserInfo;

public interface UserInfoDao {
	
	UserInfo selectUserInfo(String email);
	
	void updateUserName(String email, String name);
	
}
