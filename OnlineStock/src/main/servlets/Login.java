package main.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		Cookie[] cookies = request.getCookies();

		// Logout action removes session, but the cookie remains
		if( request.getParameter("Logout") != null ){
			if( session != null ){
				session.invalidate();
				session = null;
			}
			
			// logout, 减少一个 总人数 和 一个 登陆者
			System.out.println("Login 46 退出 delete a total and a visitor");
			ServletContext servletContext = getServletContext();
			servletContext.setAttribute(ParaName.totalAttr, (int)servletContext.getAttribute(ParaName.totalAttr)-1 );
			servletContext.setAttribute(ParaName.onlineAttr, (int)servletContext.getAttribute(ParaName.onlineAttr)-1 );
			
			response.sendRedirect("/Login");
		}
		else{
			if( session==null ){
				session = request.getSession(true);
				
				// 600s 不活跃 session 失效
				session.setMaxInactiveInterval(600);
				
				// 增加一个 总人数 和 一个 游客
				System.out.println("Login 61 访问 add a total and a visitor");
				ServletContext servletContext = getServletContext();
				servletContext.setAttribute(ParaName.totalAttr, (int)servletContext.getAttribute(ParaName.totalAttr)+1 );
				servletContext.setAttribute(ParaName.visitorAttr, (int)servletContext.getAttribute(ParaName.visitorAttr)+1 );
				
				showLoginPage(request, response, false);
			}
			else{
				if( session.getAttribute(ParaName.reqUserName)==null ){
					showLoginPage(request, response, false);
				}
				else {
					response.sendRedirect("/ShowMyStockServlet");
				}
			}
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	public void showLoginPage(HttpServletRequest request, HttpServletResponse response, boolean reLogin)throws ServletException, IOException{
		
		setHtmlResponseStart( response );
		
		Cookie[] cookies = request.getCookies();
		String cookieUsername = getCookieValue(cookies, "LoginCookie");
		
		PrintWriter out = response.getWriter();
		out.println("<form method='POST' action='/ShowMyStockServlet'>");
		out.println(
				"username: <input type='text' name='username' value='" + cookieUsername + "'>");
		out.println(
				"password: <input type='password' name='password' value=''>");
		out.println("<input type='submit' name='Submit' value='Submit'>");
		out.println("</form>");
		
		showUserCount( getServletContext(), response );
		
		if( reLogin ){
			out.println("<script>alert('fail to login')</script>");
		}
		
		setHtmlResponseEnd( response );
	}
	
	private String getCookieValue(Cookie[] cookies, String name){
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
	
	public static void setHtmlResponseStart(HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
	}
	
	public static void setHtmlResponseEnd(HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		out.println("</body></<html>");
	}
	
	public static void showUserCount(ServletContext servletContext, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		out.println("<br/>");
		out.println("<p>在线总人数：" + servletContext.getAttribute(ParaName.totalAttr) + "</p>");
		out.println("<p>已登陆总人数：" + servletContext.getAttribute(ParaName.onlineAttr) + "</p>");
		out.println("<p>游客总人数：" + servletContext.getAttribute(ParaName.visitorAttr) + "</p>");
		out.println("<br/>");
	}
}
