package tickets.service;

import tickets.model.UserInfo;

public interface UserService {
	
	UserInfo getUserInfo(String email);
}
