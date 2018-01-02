package main.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.database.DaoClient;
import main.model.Order;

/**
 * Servlet implementation class StockListServlet
 */
@WebServlet("/ShowMyStockServlet")
public class ShowMyStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoClient daoClient = new DaoClient();
	
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
			response.sendRedirect("/Login");
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
			response.sendRedirect("/Login");
		}
		
		if( session.getAttribute(ParaName.reqUserName)==null ){
			String username = request.getParameter(ParaName.reqUserName);
			String password = request.getParameter(ParaName.password);
			System.out.println( username + " is trying login");
			if( daoClient.checkLogin(username, password) ){
				// 登陆成功，增加一个在线者，减少一个游客
				System.out.println("ShowMyStockServlet 72 add a online and delete a visitor");
				ServletContext servletContext = getServletContext();
				servletContext.setAttribute(ParaName.onlineAttr, (int)servletContext.getAttribute(ParaName.onlineAttr)+1 );
				servletContext.setAttribute(ParaName.visitorAttr, (int)servletContext.getAttribute(ParaName.visitorAttr)-1 );
				
				checkCookieUserName(request, response);
				processRequest(request, response);
			}
			else{
				System.out.println("username or password is wrong");
				new Login().showLoginPage(request, response, true);
			}
		}
		else {
			checkCookieUserName(request, response);
			processRequest(request, response);
		}
	}
	
	// deal with the GET or POST request
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		request.setAttribute( ParaName.reqUserName, session.getAttribute(ParaName.reqUserName) );
		
		// 页面显示
		Login.setHtmlResponseStart( response );
		
		daoClient.getOrders(request);
		displayMyOrders(request, response);
		displayLogoutPage(request, response);
		
		Login.showUserCount( getServletContext(), response );
		
		Login.setHtmlResponseEnd( response );
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
				if( cookie.getName().equals("LoginCookie") ){
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
			cookie = new Cookie("LoginCookie", username);
			cookie.setMaxAge( Integer.MAX_VALUE );
			response.addCookie(cookie);
			
			System.out.println("Add cookie");
		}
		
		HttpSession session = request.getSession(false);
		if( session.getAttribute(ParaName.reqUserName)==null ){
			session.setAttribute(ParaName.reqUserName, username);
		}
	}
	
	private void displayMyOrders(HttpServletRequest requset, HttpServletResponse response) throws IOException {
		ArrayList list = (ArrayList) requset.getAttribute("orderList");
		
		PrintWriter out = response.getWriter();
		out.println("<h2>用户  " + (String)requset.getAttribute(ParaName.reqUserName) + "  订单详情</h2>");
		out.println("<table style='padding:1px; text-align:center; margin:2px' width='auto' border='1' >");
		out.println("<tr>");
		out.println("<th>订单编号</th>" +
				"<th>商品编号</th>" +
				"<th>商品名称</th>" +
				"<th>订购数量</th>" +
				"<th>商品库存</th>" +
				"<th>商品单价</th>" +
				"<th>订单总价</th>" +
				"<th>订单日期</th>" +
				"<th>备注</th>");
		out.println("</tr>");
		
		for (int i = 0; i < list.size(); i++) {
			Order order = (Order) list.get(i);
			if( order.getGoodNum()>order.getStoreNum() ){
				out.println("<tr style='color:red;'>");
			}
			else {
				out.println("<tr>");
			}
			out.println("<td>"+order.getOrderID()+"</td>");
			out.println("<td>"+order.getGoodID()+"</td>");
			out.println("<td>"+order.getGoodName()+"</td>");
			out.println("<td>"+order.getGoodNum()+"</td>");
			out.println("<td>"+order.getStoreNum()+"</td>");
			out.println("<td>"+order.getGoodPrice()+"</td>");
			out.println("<td>"+order.getTotalPrice()+"</td>");
			out.println("<td>"+order.getOrderDate()+"</td>");
			if( order.getGoodNum()>order.getStoreNum() ){
				out.println("<td>库存不足</td>");
			}
			else {
				out.println("<td></td>");
			}
			out.println("</tr>");
		}
		out.println("</table>");
	}
	
	private void displayLogoutPage(HttpServletRequest requset, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		for( int i=0; i<5; i++ ){
			out.println("<br/>");
		}
		// Logout注销登录
		out.println("<form method='GET' action='" + response.encodeURL(requset.getContextPath() + "/Login") + "'>");
		out.println("</p>");
		out.println("<input type='submit' name='Logout' value='Logout'>");
		out.println("</form>");
		out.println("<p>Servlet is version @version@</p>");
	}

}
