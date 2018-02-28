package main.controller;

import main.javabean.OrderListBean;
import main.service.ClientService;
import main.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

@Controller
public class ShowMyOrderController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "ShowMyOrder", method = RequestMethod.GET)
	protected String doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		
		if( session==null || session.getAttribute(ParaName.reqUserName)==null ){
			return Redirect.redirectToLogin();
		}
		else {
			return processRequest(request, response);
		}
	}
	
	@RequestMapping(value = "ShowMyOrder", method = RequestMethod.POST)
	protected String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		if( session==null ){
			return Redirect.redirectToLogin();
		}
		else{
			if( session.getAttribute(ParaName.reqUserName)==null ){
				String username = request.getParameter(ParaName.reqUserName);
				String password = request.getParameter(ParaName.password);
				System.out.println( username + " is trying login");
				if( clientService.login(username, password) ){
					checkCookieUserName(request, response);
					return processRequest(request, response);
				}
				else{
					System.out.println("username or password is wrong");
					return Redirect.redirectToLogin();
				}
			}
			else {
				checkCookieUserName(request, response);
				return processRequest(request, response);
			}
		}
	}
	
	// deal with the GET or POST request
	private String processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession(false);
		request.setAttribute( ParaName.reqUserName, session.getAttribute(ParaName.reqUserName) );
		
		// 获得订单列表
		OrderListBean orders = new OrderListBean();
		orders.setOrders( orderService.getOrders( (String)session.getAttribute(ParaName.reqUserName) ) );
		request.setAttribute("orders", orders);
		
		return "order/myOrders";
	}
	
	private void checkCookieUserName(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter(ParaName.reqUserName);
		
		// judge if client owns Cookie
		boolean foundCookie = false;
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();
		if( cookies != null ) {
			// Look through all the cookies and see if the
			// cookie with the login info is there.
			for( int i=0; i<cookies.length; i++ ){
				cookie = cookies[i];
				if( cookie.getName().equals(ParaName.cookieUserName) ){
					foundCookie = true;
					break;
				}
			}
		}
		
		if (foundCookie) {
			// If the cookie exists update the value only
			// if changed
			if( !username.equals( cookie.getValue()) ){
				cookie.setValue(username);
				response.addCookie(cookie);
				
				System.out.println("Update cookie");
			}
		}
		else {
			// If the cookie does not exist, create it and set value
			cookie = new Cookie(ParaName.cookieUserName, username);
			cookie.setMaxAge( Integer.MAX_VALUE );
			response.addCookie(cookie);
			
			System.out.println("Add cookie");
		}
		
		HttpSession session = request.getSession(false);
		if( session.getAttribute(ParaName.reqUserName)==null ){
			session.setAttribute(ParaName.reqUserName, username);
		}
	}

}
