package main.listeners;

import main.servlets.ParaName;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

@WebListener()
public class UserCountListener implements ServletContextListener,
		HttpSessionListener, HttpSessionAttributeListener {
	private static final long serialVersionUID = 1L;
	
	private static ServletContext servletContext = null;
	
	// Public constructor is required by servlet spec
	public UserCountListener(){
		super();
	}
	
	// -------------------------------------------------------
	// ServletContextListener implementation
	// -------------------------------------------------------
	public void contextInitialized(ServletContextEvent sce){
		/* This method is called when the servlet context is
			initialized(when the Web application is deployed).
			You can initialize servlet context related data here.
		*/
		servletContext = sce.getServletContext();
		if( servletContext.getAttribute(ParaName.totalAttr)==null ){
			servletContext.setAttribute(ParaName.totalAttr, 0);
			servletContext.setAttribute(ParaName.onlineAttr, 0);
			servletContext.setAttribute(ParaName.visitorAttr, 0);
		}
		
		System.out.println("contextInitialized");
	}
	
	public void contextDestroyed(ServletContextEvent sce){
		/* This method is invoked when the Servlet Context
			(the Web application) is undeployed or
            Application Server shuts down.
        */
		System.out.println("contextDestroyed");
	}
	
	// -------------------------------------------------------
	// HttpSessionListener implementation
	// -------------------------------------------------------
	public void sessionCreated(HttpSessionEvent se){
		/* Session is created. */
		System.out.println("sessionCreated");
	}
	
	public void sessionDestroyed(HttpSessionEvent se){
		/* Session is destroyed. */
		System.out.println("sessionDestroyed");
	}
	
	// -------------------------------------------------------
	// HttpSessionAttributeListener implementation
	// -------------------------------------------------------
	
	public void attributeAdded(HttpSessionBindingEvent sbe){
      /* This method is called when an attribute 
         is added to a session.
      */
	}
	
	public void attributeRemoved(HttpSessionBindingEvent sbe){
      /* This method is called when an attribute
         is removed from a session.
      */
	}
	
	public void attributeReplaced(HttpSessionBindingEvent sbe){
      /* This method is invoked when an attibute
         is replaced in a session.
      */
	}
}
