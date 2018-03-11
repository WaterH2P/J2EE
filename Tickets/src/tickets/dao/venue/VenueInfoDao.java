package tickets.dao.venue;

import tickets.model.VenueInfo;
import tickets.model.VenueInfoChange;
import tickets.model.VenueInfoRedundancy;

import java.util.List;

public interface VenueInfoDao {

	void addVenueInfo(VenueInfo venueInfo);
	
	List<VenueInfo> selectAllUnconfirmedVenues();
	
	VenueInfo selectVenueInfo(String venueID);
	
	boolean preUpdateVenueInfo(VenueInfoChange venueInfo);
	
	List<VenueInfoChange> selectAllVenueInfoChanges();
	
	List<VenueInfoRedundancy> selectAllVenueInfoRedundancies();
	
	boolean updateVenueInfo(String venueID);
	
	void updateVenueInfoIsChanging(String venueID, boolean isChanging);

}
