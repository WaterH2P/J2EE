package tickets.service.venue;

import tickets.model.venue.VenuePlan;

import java.util.List;

public interface VenuePlanService {
	
	String addNewVenuePlan(VenuePlan venuePlan);
	
	List<VenuePlan> getAllVenuePlansByVenueID(String venueID);
	
	void updateVenuePlanSeatDist(String planID, String[] seats);
	
}
