package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 设置 UserAccount
		setUserAccount(request);
		
		HttpSession session = request.getSession(false);
		
		if( session==null ){
			session = request.getSession(true);
			
			// 600s 不活跃 session 失效
			session.setMaxInactiveInterval(600);
			
			if( request.getAttribute(ParaName.reqUserName)==null ){
				setUserAccount(request);
			}
			return "log/login_log";
		}
		else{
			// 用户是游客
			if( session.getAttribute(ParaName.reqUserName)==null ){
				setUserAccount(request);
				return "log/login_log";
			}
			else{
				return Redirect.redirectToShowMyOrder();
			}
		}
	}
	
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	protected String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			
			return Redirect.redirectToLogin();
		}
		
		return Redirect.redirectToShowMyOrder();
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
}
