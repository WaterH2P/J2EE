package edu.nju.onlinestock.servlets;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class Aservlet extends HttpServlet {
	private String message;
	
	public void init() throws ServletException{
		message = "hello world!";
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 设置响应内容类型
		response.setContentType("text/html");
		// 实际的逻辑是在这里
		PrintWriter out = response.getWriter();
		out.println("<h1>" + message + "</h1>");
	}
}
