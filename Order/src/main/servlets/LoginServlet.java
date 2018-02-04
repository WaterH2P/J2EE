package main.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 设置 UserAccount
		setUserAccount(request);
		
		HttpSession session = request.getSession(false);
		
		if( session==null ){
			session = request.getSession(true);
			
			// 600s 不活跃 session 失效
			session.setMaxInactiveInterval(600);
			
			gotoLogin(request, response);
		}
		else{
			// 用户是游客
			if( session.getAttribute(ParaName.reqUserName)==null ){
				gotoLogin(request, response);
			}
			else{
				response.sendRedirect("/ShowMyOrderServlet");
			}
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 设置 UserAccount
		setUserAccount(request);
		
		// Logout action removes session, but the cookie remains
		if( request.getParameter(ParaName.logout)!=null ){
			HttpSession session = request.getSession(false);
			if( session != null && session.getAttribute(ParaName.reqUserName)!=null ){
				session.invalidate();
				session = null;
			}
			
			response.sendRedirect("LoginServlet");
		}
	}
	
	private static String getCookieValue(Cookie[] cookies, String name){
		String value = "";
		Cookie cookie = null;
		if( cookies != null ){
			// Look through all the cookies and see if the
			// cookie with the login info is there.
			for( int i=0; i<cookies.length; i++ ){
				cookie = cookies[i];
				if( cookie.getName().equals(name) ){
					value = cookie.getValue();
					break;
				}
			}
		}
		return value;
	}
	
	public static void setUserAccount(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		
		// 设置 UserAccount
		String username = getCookieValue( cookies, ParaName.cookieUserName);
		request.setAttribute(ParaName.reqUserName, username);
	}
	
	private void gotoLogin(HttpServletRequest request, HttpServletResponse response){
		
		if( request.getAttribute(ParaName.reqUserName)==null ){
			setUserAccount(request);
		}
		
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/views/log/login_log.jsp");
		try{
			requestDispatcher.forward(request, response);
		}catch( ServletException e ){
			System.out.println( " LoginServlet.java gotoLogin cause ServletException " );
			e.printStackTrace();
		}catch( IOException e ){
			e.printStackTrace();
		}
	}
}
