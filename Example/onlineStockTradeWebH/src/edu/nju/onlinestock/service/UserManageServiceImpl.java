package edu.nju.onlinestock.service;

import java.io.IOException;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.onlinestock.dao.UserDao;
import edu.nju.onlinestock.factory.DaoFactory;
import edu.nju.onlinestock.model.User;


/**
 * Session Bean implementation class UserManageServiceBean
 */

public class UserManageServiceImpl implements UserManageService {

    /**
     * Default constructor. 
     */
	
	
	private static UserManageServiceImpl userService=new UserManageServiceImpl();
	
	private UserManageServiceImpl()
	{
		
	}
	
	public static UserManageService getInstance()
	{
		return userService;
	}

	private static UserDao userDao=DaoFactory.getUserDao();
	
	public User validateUser(String userid,String password)
	{
		User user=userDao.find("userid",userid);
		if(user==null)
		{
			return null;
		}
		else if(!user.getPassword().equals(password))
		{
			return null;
		}
		
		return user;
	}


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
		userDao.save(user);
			
		return message;
	//	}
	}
	public String test(){
		System.out.println(" test");
		return "test";

	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		userDao.delete(user);
	}

	@Override
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		return userDao.FindUserById(id);
	}


}
