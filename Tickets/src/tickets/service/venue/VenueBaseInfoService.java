package tickets.service.venue;

import tickets.model.venue.VenueBaseInfo;
import tickets.model.venue.VenueBaseInfoChange;
import tickets.model.venue.VenueBaseInfoRedundancy;

import java.util.List;

public interface VenueBaseInfoService {
	
	void preRegister(VenueBaseInfo venueBaseInfo);
	
	List<VenueBaseInfo> getAllUnconfirmedVenueInfos();
	
	VenueBaseInfo getVenueInfo(String venueID);
	
	boolean preChangeVenueInfo(VenueBaseInfoChange venueInfo);
	
	List<VenueBaseInfoRedundancy> getAllVenueInfoRedundancies();
	
	void ChangeVenueInfo(String venueID);

}
