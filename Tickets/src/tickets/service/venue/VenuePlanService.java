package tickets.service.venue;

import tickets.model.user.UserOdSeat;
import tickets.model.venue.VenuePlan;
import tickets.model.venue.VenuePlanSeat;

import java.util.List;

public interface VenuePlanService {
	
	String addNewVenuePlan(VenuePlan venuePlan);
	
	List<VenuePlan> getAllVenuePlansIsNotCheckedByVenueID(String venueID);
	
	List<VenuePlan> getAllVenuePlansIsCheckingByVenueID(String venueID);
	
	List<VenuePlan> getAllVenuePlansIsCheckedByVenueID(String venueID);
	
	void updateVenuePlanSeatDist(String planID, List<UserOdSeat> userOdSeats, String stateExchangeTo);
	
	List<VenuePlanSeat> getPlanHallSeatInfo(String hallID);
	
}
