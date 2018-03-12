package tickets.dao.venue;

import tickets.model.VenueSeatLevel;

import java.util.List;

public interface VenueSeatLevelDao {
	
	boolean insertSeatLevel(VenueSeatLevel venueSeatLevel);
	
	boolean deleteSeatLevel(String seatID);
	
	List<VenueSeatLevel> selectAllSeatLevels(String venueID);
	
}
