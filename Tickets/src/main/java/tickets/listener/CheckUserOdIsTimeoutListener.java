package tickets.listener;

import tickets.thread.AllocateSeatToUserOdIsNotSeatedThread;
import tickets.thread.CheckUserOdIsTimeoutThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CheckUserOdIsTimeoutListener implements ServletContextListener {
	
	private CheckUserOdIsTimeoutThread checkUserOdIsTimeoutThread;
	private AllocateSeatToUserOdIsNotSeatedThread allocateSeatToUserOdIsNotSeatedThread;
	
	public void contextDestroyed(ServletContextEvent e) {
		if( checkUserOdIsTimeoutThread!=null && checkUserOdIsTimeoutThread.isInterrupted() ){
			checkUserOdIsTimeoutThread.interrupt();
		}
	}
	public void contextInitialized(ServletContextEvent e) {
		if( checkUserOdIsTimeoutThread== null ){
//			checkUserOdIsTimeoutThread = new CheckUserOdIsTimeoutThread();
//			checkUserOdIsTimeoutThread.start();
		}
	}
}
