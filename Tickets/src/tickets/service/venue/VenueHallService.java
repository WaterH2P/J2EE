package tickets.service.venue;

import tickets.model.venue.VenueHall;

import java.util.List;

public interface VenueHallService {
	
	VenueHall addNewVenueHall(VenueHall venueHall, String seatData, String seatLevel);
	
	List<VenueHall> getAllVenueHalls(String venueID);
	
	void deleteVenueHall(String hallID);
	
}
