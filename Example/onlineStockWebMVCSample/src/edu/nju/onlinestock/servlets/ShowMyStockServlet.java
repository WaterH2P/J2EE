package edu.nju.onlinestock.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import edu.nju.onlinestock.action.business.StockListBean;
import edu.nju.onlinestock.factory.ServiceFactory;
import edu.nju.onlinestock.model.Stock;

/*
 ��Servletδʹ��DAO��MVC��ƣ�Ҫ��ֻ���ѵ�¼�Ŀͻ����ܲ鿴�Լ�����Ĺ�Ʊ(����Դ)��
 ʵ�֣��ѵ�¼�û������ܲ鿴��
            δ��¼�û���תȥ��¼��
            �ӵ�¼�ύ���˵��û�������session�����ٵ�¼״̬����������Գ��ε�¼���û����򴴽�cookie�����鿴�Լ�����Ĺ�Ʊ��
            ͨ��ˢ��ҳ��/���Ѵ���session��ҳ����ʣ���鿴�Լ�����Ĺ�Ʊ��
 */

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
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession(false);
		boolean cookieFound = false;
		System.out.println(req.getParameter("login") + " req");
		Cookie cookie = null;
		Cookie[] cookies = req.getCookies();
		if (null != cookies) {
			// Look through all the cookies and see if the
			// cookie with the login info is there.
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getName().equals("LoginCookie")) {
					cookieFound = true;
					break;
				}
			}
		}

		if (session == null) {
			String loginValue = req.getParameter("login");
			boolean isLoginAction = (null == loginValue) ? false : true;

			System.out.println(loginValue + " session null");
			if (isLoginAction) { // User is logging in
				if (cookieFound) { // If the cookie exists update the value only
					// if changed
					if (!loginValue.equals(cookie.getValue())) {
						cookie.setValue(loginValue);
						resp.addCookie(cookie);
					}
				} else {
					// If the cookie does not exist, create it and set value
					cookie = new Cookie("LoginCookie", loginValue);
					cookie.setMaxAge(Integer.MAX_VALUE);
					System.out.println("Add cookie");
					resp.addCookie(cookie);
				}

				// create a session to show that we are logged in
				session = req.getSession(true);
				session.setAttribute("login", loginValue);

				req.setAttribute("login", loginValue);
				getMyStockList(req, resp);


			} else {
				System.out.println(loginValue + " session null");
				// Display the login page. If the cookie exists, set login
				resp.sendRedirect(req.getContextPath() + "/Login");
			}
		} else {
			// ��δע�������¼��ظ�ҳ�棬session��Ϊ��
			String loginValue = (String) session.getAttribute("login");
			System.out.println(loginValue + " session");

			req.setAttribute("login", loginValue);
			getMyStockList(req, resp);

		}

	}

	public void getMyStockList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession(true);
		ServletContext context = getServletContext();
		
		StockListBean listStock = new StockListBean();
		String loginName=(String) req.getAttribute("login");
		//listStock.setStockList(DaoFactory.getStockDao().find());
		listStock.setStockList(ServiceFactory.getStockManageService().getMystock(loginName));
		try {
			if (listStock.getStockList().size() < 1) {
				context.getRequestDispatcher("/stock/noListStock.jsp").forward(
						req, resp);
			} else {
				session.setAttribute("listStock", listStock);
				context.getRequestDispatcher("/stock/listStock.jsp").forward(
						req, resp);
			}
		} catch (ServletException e) {
			// System error - report error 500 and message
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"This is a ServletException.");
		}

	}



}
