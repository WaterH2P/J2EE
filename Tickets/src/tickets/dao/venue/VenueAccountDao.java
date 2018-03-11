package tickets.dao.venue;

import tickets.model.VenueInfo;

import java.util.List;

public interface VenueAccountDao {
	
	List<String> getAllVenusIDs();
	
	void addAccount(String venueID, String password);
	
	boolean updateVenueIsConfirmedToTrue(String venueID);
	
}
