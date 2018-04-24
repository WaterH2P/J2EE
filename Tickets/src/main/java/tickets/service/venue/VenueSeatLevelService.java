package tickets.service.venue;

import tickets.model.venue.VenueSeatLevel;

import java.util.List;

public interface VenueSeatLevelService {
	
	String addSeatLevel(VenueSeatLevel venueSeatLevel);
	
	void deleteSeatLevel(String seatID);
	
	List<VenueSeatLevel> getAllSeatLevels(String venueID);
	
}
