package tickets.service.user;

import tickets.model.venue.VenuePlan;
import tickets.model.venue.VenuePlanSeat;

import java.util.List;

public interface UserOdService {
	
	List<VenuePlan> searchPlanByPlanName(String planName);
	
	List<VenuePlanSeat> getPlanHallSeatInfo(String hallID);
	
	String makeNewOrder(String email, String planID, String seatSelected, String totalPrice);
	
	boolean payOd(String email, String OdID, String couponID);
	
}
