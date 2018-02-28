package edu.nju.onlinestock.service;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.onlinestock.dao.UserDao;
import edu.nju.onlinestock.model.User;
import edu.nju.onlinestock.factory.EJBFactory;

/**
 * Session Bean implementation class UserManageServiceBean
 */
@Stateless
public class UserManageServiceBean implements UserManageService {

    /**
     * Default constructor. 
     */
	
//	@EJB UserDao userDao;
	UserDao userDao = (UserDao) EJBFactory
			.getEJB("ejb:/onlineStockEJBJPA/UserDaoBean!edu.nju.onlinestock.dao.UserDao");
	
/*public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
		System.out.println(" set userDao");
	}*/





	public Integer validateNumber(String number)
	{
		Integer num=null;
		try
		{
			num=Integer.valueOf(number);
		}
		catch(Exception e)
		{
			return null;
		}
		
		if(num.intValue()<=0)
		{
			return null;
		}
		
		return num;
	}
	
	public void sentErrorMessage(String message,HttpServletRequest req) 
					throws ServletException,IOException
	{
		req.setAttribute("message",message);
//		RequestDispatcher dispater=req.getRequestDispatcher(resp.encodeURL("/error/error.jsp"));
//		dispater.forward(req,resp);
	}
	

	public void sentMessage(String message,HttpServletRequest req) 
					throws ServletException,IOException
	{
		req.setAttribute("message",message);
//		RequestDispatcher dispater=req.getRequestDispatcher(resp.encodeURL("/message/message.jsp"));
//		dispater.forward(req,resp);
	}
	

	public void forwardPage(String page,HttpServletRequest req,HttpServletResponse resp) 
					throws ServletException,IOException
	{
		RequestDispatcher dispater=req.getRequestDispatcher(resp.encodeURL(page));
		dispater.forward(req,resp);
	}

	public String registerUser(User user) {
		String message=null;
	/*	if(validateUser(user.getUserid(), user.getPasswordOne())!=null ){
			message="username exist";
			return message;
		}
/*		else if(validateUpdateUser(user, message)!=null){
			message="All the content must be filled!";
			return message;
		}*/
	//	else{
		System.out.println(" Ready to save user");
		if (userDao != null)
			System.out.println("Obtained a remote stateless session bean userDao for invocation");
		userDao.save(user);
			
		return message;
	//	}
	}
	public String test(){
		System.out.println(" test");
		return "test";

	}

	@Override
	public User validateUser(Long userid) {
		String message=null;
		User user = userDao.FindUserById(userid);
		if (user==null){
			message="not find user";
			return null;
		}
		return user;
		
	}


}
