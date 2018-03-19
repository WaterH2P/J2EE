package tickets.service.user;

import tickets.model.user.UserOd;
import tickets.model.user.UserOdSeat;
import tickets.model.venue.VenuePlan;
import tickets.model.venue.VenuePlanSeat;

import java.util.List;

public interface UserOdService {
	
	List<VenuePlan> searchPlanByPlanName(String planName);
	
	List<VenuePlanSeat> getPlanHallSeatInfo(String hallID);
	
	String makeNewOrderSeated(String email, String planID, String seatSelected, String totalPrice, boolean isOnline);
	
	String makeNewOrderUnseated(String email, String planID, String numOfTBought, boolean isOnline);
	
	boolean payOd(String email, String OdID, String couponID);
	
	List<UserOd> getAllHistoricalUserOd(String email);
	
	List<UserOd> getAllUserOdTimeout(String email);
	
	List<UserOd> getAllUserOdDeleted(String email);
	
	List<UserOd> getAllFutureUserOd(String email);
	
	List<UserOd> getAllUserOdUnfinished(String email);
	
	VenuePlan getUserOdPlanInfo(String planID);
	
	List<UserOdSeat> getUserOdAllSeatSelectedInfo(String OdID);
}
