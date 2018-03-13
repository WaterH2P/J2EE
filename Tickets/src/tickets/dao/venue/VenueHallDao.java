package tickets.dao.venue;

import tickets.model.VenueHall;

import java.util.List;
import java.util.Map;

public interface VenueHallDao {
	
	boolean insertNewHall(VenueHall venueHall, Map<Integer, String> seatRowLevel);
	
	List<String> selectAllVenueHallIDs(String venueID);
	
}
