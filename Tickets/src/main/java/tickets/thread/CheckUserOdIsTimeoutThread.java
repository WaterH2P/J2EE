package tickets.thread;

import tickets.service.user.UserOdService;
import tickets.serviceImpl.user.UserOdServiceImpl;

public class CheckUserOdIsTimeoutThread extends Thread {
	
	private UserOdService userOdService = new UserOdServiceImpl();
	
	public void run(){
		int time = -5;
		while( !this.isInterrupted() ){
			time += 5;
			System.out.println("check user orders is timeout : " + time + " s");
			userOdService.checkUserOdIsTimeout();
			try{
				Thread.sleep(5 * 1000);
			}catch( InterruptedException e ){
				e.printStackTrace();
			}
		}
	}
	
}
