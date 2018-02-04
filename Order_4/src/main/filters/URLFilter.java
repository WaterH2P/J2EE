package main.filters;

import main.servlets.ParaName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter( filterName = "URLFilter" )
public class URLFilter implements Filter {
	public void destroy(){
	}
	
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
//
//		String url = request.getRequestURI();
//		if( url!=null ){
//			if( url.endsWith("myOrders.jsp") ){
//				HttpSession session = request.getSession(false);
//				if( session==null || session.getAttribute(ParaName.reqUserName)==null ){
//					response.sendRedirect("/LoginServlet");
//					return;
//				}
//			}
//			else if( url.endsWith(".jsp") ){
//				HttpSession session = request.getSession(false);
//				if( session==null || session.getAttribute(ParaName.reqUserName)==null ){
//					response.sendRedirect("/LoginServlet");
//					return;
//				}
//				else if( session.getAttribute(ParaName.reqUserName)!=null ){
//					response.sendRedirect("/ShowMyOrderServlet");
//					return;
//				}
//			}
//		}
		
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig config) throws ServletException{
		
	}
	
}
