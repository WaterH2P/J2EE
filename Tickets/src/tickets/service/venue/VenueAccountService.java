package tickets.service.venue;

import tickets.model.VenueBaseInfo;

public interface VenueAccountService {

	String preRegister(VenueBaseInfo venueBaseInfo, String password);
	
}
