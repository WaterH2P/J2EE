package tickets.service;

import tickets.model.VenueInfo;

public interface VenueAccountService {

	void preRegister(VenueInfo venueInfo, String password);
	
}
