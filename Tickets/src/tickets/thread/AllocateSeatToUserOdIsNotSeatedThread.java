package tickets.thread;

import tickets.service.venue.VenuePlanService;
import tickets.serviceImpl.venue.VenuePlanServiceImpl;

public class AllocateSeatToUserOdIsNotSeatedThread extends Thread {
	
	private VenuePlanService venuePlanService = new VenuePlanServiceImpl();
	
	public void run(){
		int time = -60;
		while( !this.isInterrupted() ){
			time += 60;
			System.out.println("check plan to allocate seat to user : " + time + " s");
			venuePlanService.allocateSeatToUserOd_IsPaid_IsNotSeated();
			try{
				Thread.sleep(60 * 1000);
			}catch( InterruptedException e ){
				e.printStackTrace();
			}
		}
	}
	
}
