package tickets.dao.venue;

import tickets.model.VenueBaseInfo;
import tickets.model.VenueBaseInfoChange;
import tickets.model.VenueBaseInfoRedundancy;

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

}
