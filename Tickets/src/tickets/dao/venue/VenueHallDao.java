package tickets.dao.venue;

import tickets.model.venue.VenueHall;
import tickets.model.venue.VenueHallSeat;

import java.util.List;

public interface VenueHallDao {
	
	boolean insertNewVenueHall(VenueHall venueHall, List<VenueHallSeat> venueHallSeats);
	
	List<String> selectAllVenueHallIDs(String venueID);
	
	List<VenueHall> selectAllVenueHalls(String venueID);
	
	void deleteVenueHall(String hallID);
	
	VenueHall selectVenueHall(String hallID);
	
}
