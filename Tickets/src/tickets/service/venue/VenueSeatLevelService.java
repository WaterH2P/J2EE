package tickets.service.venue;

import tickets.model.VenueSeatLevel;

import java.util.List;

public interface VenueSeatLevelService {
	
	String addSeatLevel(VenueSeatLevel venueSeatLevel);
	
	boolean deleteSeatLevel(String seatID);
	
	List<VenueSeatLevel> getAllSeatLevels(String venueID);
	
}
