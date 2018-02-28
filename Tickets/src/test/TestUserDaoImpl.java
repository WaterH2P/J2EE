package test;

import tickets.factory.DaoFactory;
import tickets.dao.UserDao;

public class TestUserDaoImpl {
	
	private static UserDao userDao = DaoFactory.getUserDaoImpl();
	
	public static void main(String args[]){
		String userID = "123";
		String password = "123";
		if( userDao.loginCheck(userID, password) ){
			System.out.println("login successfully");
		}
		else{
			System.out.println("fail to login");
		}
	}
}
