package main.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.javabean.OrderListBean;
import main.service.ClientService;
import main.service.OrderService;

import main.factory.EJBFactory;

/**
 * Servlet implementation class StockListServlet
 */

public class ShowMyOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ClientService clientService = (ClientService)EJBFactory.getEJB(
			"ejb:/OdEJBClient/ClientServiceBean!main.service.ClientService"
	);
	
	@EJB
	private OrderService orderService = (OrderService)EJBFactory.getEJB(
			"ejb:/OdEJBClient/OrderServiceBean!main.service.OrderService"
	);
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowMyOrderServlet() {
//		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		
		if( session==null || session.getAttribute(ParaName.reqUserName)==null ){
			response.sendRedirect("/LoginServlet");
		}
		else {
			processRequest(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("fuck2");
		
		HttpSession session = request.getSession(false);
		if( session==null ){
			response.sendRedirect("/LoginServlet");
		}
		else{
			if( session.getAttribute(ParaName.reqUserName)==null ){
				String username = request.getParameter(ParaName.reqUserName);
				String password = request.getParameter(ParaName.password);
				System.out.println( username + " is trying login");
				
				if( clientService.login(username, password) ){
					checkCookieUserName(request, response);
					processRequest(request, response);
				}
				else{
					System.out.println("username or password is wrong");
					RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/views/log/loginFail_log.jsp");
					requestDispatcher.forward(request, response);
				}
			}
			else {
				checkCookieUserName(request, response);
				processRequest(request, response);
			}
		}
	}
	
	// deal with the GET or POST request
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession(false);
		request.setAttribute( ParaName.reqUserName, session.getAttribute(ParaName.reqUserName) );
		
		// 获得订单列表
		OrderListBean orders = new OrderListBean();
		orders.setOrders( orderService.getOrders( (String)session.getAttribute(ParaName.reqUserName) ) );
		request.setAttribute("orders", orders);
		
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/views/order/myOrders.jsp");
		try{
			requestDispatcher.forward(request, response);
		}catch( ServletException e ){
			System.out.println( " ShowMyOrderServlet.java gotoLogin cause ServletException " );
			e.printStackTrace();
		}catch( IOException e ){
			e.printStackTrace();
		}
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
