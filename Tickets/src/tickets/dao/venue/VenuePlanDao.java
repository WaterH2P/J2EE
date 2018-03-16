package tickets.dao.venue;

import tickets.model.venue.VenuePlan;

import java.util.List;

public interface VenuePlanDao {
	
	List<String> selectAllVenuePlanIDs(String venueID);
	
	void insertNewVenuePlan(VenuePlan venuePlan);
	
	List<VenuePlan> selectAllVenuePlansByVenueID(String venueID);
	
	List<VenuePlan> selectAllVenuePlansByPlanName(String planName);
	
}
