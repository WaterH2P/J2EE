package tickets.listener;

import tickets.thread.AllocateSeatToUserOdIsNotSeatedThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AllocateSeatToUserOdIsNotSeatedListener implements ServletContextListener {
	
	private AllocateSeatToUserOdIsNotSeatedThread allocateSeatToUserOdIsNotSeatedThread;
	
	public void contextDestroyed(ServletContextEvent e) {
		if( allocateSeatToUserOdIsNotSeatedThread!=null && allocateSeatToUserOdIsNotSeatedThread.isInterrupted() ){
			allocateSeatToUserOdIsNotSeatedThread.interrupt();
		}
	}
	public void contextInitialized(ServletContextEvent e) {
		if( allocateSeatToUserOdIsNotSeatedThread== null ){
//			allocateSeatToUserOdIsNotSeatedThread = new AllocateSeatToUserOdIsNotSeatedThread();
//			allocateSeatToUserOdIsNotSeatedThread.start();
		}
	}
	
}
