package tickets.service.venue;

import tickets.model.venue.VenueBaseInfo;

public interface VenueAccountService {

	String preRegister(VenueBaseInfo venueBaseInfo, String password);
	
}
