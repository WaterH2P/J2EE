package test;

import tickets.factory.ServiceFactory;
import tickets.service.UserService;

public class TestUserServiceImpl {
	
	private static UserService userService = ServiceFactory.getUserServiceImpl();
	
	public static void main(String args[]){
		String userID = "123";
		String password = "123";
		if( userService.login(userID, password) ){
			System.out.println("login successfully");
		}
		else{
			System.out.println("fail to login");
		}
	}
	
}
