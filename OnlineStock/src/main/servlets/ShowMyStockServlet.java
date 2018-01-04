package main.servlets;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.factory.ServiceFactory;
import main.javabean.UserCountBean;
import main.model.Order;
import main.service.OrderService;

/**
 * Servlet implementation class StockListServlet
 */
@WebServlet("/ShowMyStockServlet")
public class ShowMyStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowMyStockServlet() {
		super();
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
		
		HttpSession session = request.getSession(false);
		if( session==null ){
			response.sendRedirect("/LoginServlet");
		}
		else{
			if( session.getAttribute(ParaName.reqUserName)==null ){
				String username = request.getParameter(ParaName.reqUserName);
				String password = request.getParameter(ParaName.password);
				System.out.println( username + " is trying login");
				if( ServiceFactory.getClientService().login(username, password) ){
					// 登陆成功，增加一个在线者，减少一个游客
					ServletContext servletContext = getServletContext();
					UserCountBean userCount = (UserCountBean) servletContext.getAttribute(ParaName.userCountBean);
					userCount.onlineAddOne();
					userCount.visitorDeleteOne();
					servletContext.setAttribute(ParaName.userCountBean, userCount);
					
					checkCookieUserName(request, response);
					processRequest(request, response);
				}
				else{
					System.out.println("username or password is wrong");
					RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/views/log/loginFail.jsp");
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
		
		// 设置 UserAccountBean
		LoginServlet.setUserAccountBean(request);
		
		HttpSession session = request.getSession(false);
		request.setAttribute( ParaName.reqUserName, session.getAttribute(ParaName.reqUserName) );
		
		// 获得订单列表
		OrderService orderService = ServiceFactory.getOrderService();
		ArrayList<Order> orders = new ArrayList<>( orderService.getOrders( (String)session.getAttribute(ParaName.reqUserName) ) );
		request.setAttribute(ParaName.orderList, orders);
		
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/views/myOrders.jsp");
		try{
			requestDispatcher.forward(request, response);
		}catch( ServletException e ){
			System.out.println( " ShowMyStockServlet.java gotoLogin cause ServletException " );
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
