package tickets.factory;

import tickets.service.UserService;
import tickets.service.UserServiceImpl;

public class ServiceFactory {
	
	public static UserService getUserServiceImpl(){
		return UserServiceImpl.getInstance();
	}
	
}
