package tickets.dao.venue;

import tickets.model.venue.VenueBaseInfo;
import tickets.model.venue.VenueBaseInfoChange;
import tickets.model.venue.VenueBaseInfoRedundancy;

import java.util.List;

public interface VenueBaseInfoDao {

	void addVenueInfo(VenueBaseInfo venueBaseInfo);
	
	List<VenueBaseInfo> selectAllUnconfirmedVenues();
	
	VenueBaseInfo selectVenueInfo(String venueID);
	
	boolean preUpdateVenueInfo(VenueBaseInfoChange venueInfo);
	
	List<VenueBaseInfoChange> selectAllVenueInfoChanges();
	
	List<VenueBaseInfoRedundancy> selectAllVenueInfoRedundancies();
	
	boolean updateVenueInfo(String venueID);
	
	void updateVenueInfoIsChanging(String venueID, boolean isChanging);

	List<VenueBaseInfo> selectAllVenueInfo();
}
