package tickets.service.venue;

import tickets.model.VenueInfo;

public interface VenueAccountService {

	String preRegister(VenueInfo venueInfo, String password);
	
}
