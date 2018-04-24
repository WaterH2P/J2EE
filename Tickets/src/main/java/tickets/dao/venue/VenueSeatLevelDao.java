package tickets.dao.venue;

import tickets.model.venue.VenueSeatLevel;

import java.util.List;

public interface VenueSeatLevelDao {
	
	boolean insertSeatLevel(VenueSeatLevel venueSeatLevel);
	
	void deleteSeatLevel(String seatID);
	
	List<VenueSeatLevel> selectAllSeatLevels(String venueID);
	
}
