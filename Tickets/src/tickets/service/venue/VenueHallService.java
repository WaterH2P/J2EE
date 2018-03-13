package tickets.service.venue;

import tickets.model.VenueHall;

public interface VenueHallService {
	
	boolean addNewHall(VenueHall venueHall, String seatLevel);
}
