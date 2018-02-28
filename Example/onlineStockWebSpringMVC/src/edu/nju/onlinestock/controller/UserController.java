package edu.nju.onlinestock.controller;


import java.sql.Date;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.nju.onlinestock.model.User;
import edu.nju.onlinestock.service.UserManageService;

/**
 * User Controller（登录、注册）
 */

@Controller //@Controller用于标注控制层组件(如struts中的action)
public class UserController{
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserManageService userService;



    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister() {
        return "register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    protected String doRegist(@RequestParam("year") String yearS,
    		@RequestParam("month") String monthS,
    		@RequestParam("day") String dayS,
    		@RequestParam("passwordOne") String ps1,
    		@RequestParam("passwordTwo") String ps2,
    		ModelMap model,
    		HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = null;
	//	ServletContext context = getServletContext();
    	User user=new User();  //controller默认是单例，如果作为成员变量有线程安全问题。
    	String message="";
		int year=0;
		int month=0;
		int day=0;
		try
		{
			year=Integer.valueOf(yearS).intValue();
			month=Integer.valueOf(monthS).intValue()-1;
			day=Integer.valueOf(dayS).intValue();
		}
		catch(Exception e)
		{
			message+="Birthday must be a Integer!<br>";
			//userService.sentErrorMessage(message, request,response);
			model.addAttribute("mess",message);
		//	context.getRequestDispatcher("/user/ErrorMessage.jsp").forward(request, response);
			return "ErrorMessage";
		}
		if(ps1.equals(ps2)){
			user.setPassword(ps1);
		}
		else{
			message+="Password not match!<br>";
	//		userService.sentErrorMessage(message, request,response);
			model.addAttribute("mess",message);
	//		context.getRequestDispatcher("/user/ErrorMessage.jsp").forward(request, response);
			return "ErrorMessage";
		}

		user.setIdByDate();
		user.setAccount(500000);
	//	user.setBankid(this.request().getParameter("bankid"));
	//	user.setEmail(this.request().getParameter("email"));
	//	user.setName(this.request().getParameter("name"));
	//	user.setPhone(this.request().getParameter("phone"));
	//	user.setUserid(this.request().getParameter("userid"));
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.YEAR,year);
		calendar.set(Calendar.MONTH,month);
		calendar.set(Calendar.DAY_OF_MONTH,day);
		Date date=new Date(calendar.getTimeInMillis());
		user.setBirthday(date);
		/*try {
			message=userService.validateRegister(user,message);
		} catch (UsernameExistException e1) {
		// TODO Auto-generated catch block
			userService.sentErrorMessage("UsernameExistException", this.request(), this.response());
		}*/

		userService.registerUser(user);
	//	if((message=userService.registerUser(user))!= null){
		/*if(message!="")
		{
			userService.sentErrorMessage(message, this.request(), this.response());
			return INPUT;
		}*/
		session = request.getSession(true);
		session.setAttribute("user", user);
	//	model.addAttribute("user", user);
	//	context.getRequestDispatcher("/user/RegUser.jsp").forward(request, response);
		return "RegUser";
	}
}
