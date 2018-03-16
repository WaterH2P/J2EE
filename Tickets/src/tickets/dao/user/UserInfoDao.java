package tickets.dao.user;

import tickets.model.user.UserInfo;

public interface UserInfoDao {
	
	UserInfo selectUserInfo(String email);
	
	void updateUserName(String email, String name);
	
}
