package tickets.service.venue;

import tickets.model.VenueInfo;
import tickets.model.VenueInfoChange;
import tickets.model.VenueInfoRedundancy;

import java.util.List;

public interface VenueInfoService {
	
	void preRegister(VenueInfo venueInfo);
	
	List<VenueInfo> getAllUnconfirmedVenueInfos();
	
	VenueInfo getVenueInfo(String venueID);
	
	boolean preChangeVenueInfo(VenueInfoChange venueInfo);
	
	List<VenueInfoRedundancy> getAllVenueInfoRedundancies();
	
	boolean ChangeVenueInfo(String venueID);

}
