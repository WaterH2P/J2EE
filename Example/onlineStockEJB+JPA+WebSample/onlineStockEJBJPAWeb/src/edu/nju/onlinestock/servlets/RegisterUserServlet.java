package edu.nju.onlinestock.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.onlinestock.model.User;
import edu.nju.onlinestock.service.UserManageService;

/**
 * Servlet implementation class RegisterUserServlet
 */
@WebServlet("/register.user")
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB UserManageService userService;      
	private User user= new User();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		execute(request,response);
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		execute(request,response);
	}

	private void execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = null;
		ServletContext context = getServletContext();
		String message="";
		int year=0;
		int month=0;
		int day=0;
		try
		{
			year=Integer.valueOf(request.getParameter("year")).intValue();
			month=Integer.valueOf(request.getParameter("month")).intValue()-1;
			day=Integer.valueOf(request.getParameter("day")).intValue();
		}
		catch(Exception e)
		{
			message+="Birthday must be a Integer!<br>";
			//userService.sentErrorMessage(message, request,response);
			request.setAttribute("mess",message);
			context.getRequestDispatcher("/user/ErrorMessage.jsp").forward(
					request, response);
			return;
		}
		if(request.getParameter("passwordOne").equals(request.getParameter("passwordTwo"))){
			user.setPassword(request.getParameter("passwordOne"));
		}
		else{
			message+="Password not match!<br>";
	//		userService.sentErrorMessage(message, request,response);
			request.setAttribute("mess",message);
			context.getRequestDispatcher("/user/ErrorMessage.jsp").forward(
					request, response);
			return;
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
		context.getRequestDispatcher("/user/RegUser.jsp").forward(
					request, response);
	}
}
