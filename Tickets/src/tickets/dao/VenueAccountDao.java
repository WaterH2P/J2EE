package tickets.dao;

import tickets.model.VenueInfo;

import java.util.List;

public interface VenueAccountDao {
	
	List<String> getAllVenusIDs();
	
	void addAccount(VenueInfo venueInfo, String password);
	
}
