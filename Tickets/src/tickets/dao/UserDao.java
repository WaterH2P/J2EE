package tickets.dao;

import tickets.model.UserInfo;

import java.util.List;

public interface UserDao {
	
	UserInfo selectUserInfo(String email);
	
}
