package tickets.service.venue;

import tickets.model.VenueBaseInfo;
import tickets.model.VenueBaseInfoChange;
import tickets.model.VenueBaseInfoRedundancy;

import java.util.List;

public interface VenueBaseInfoService {
	
	void preRegister(VenueBaseInfo venueBaseInfo);
	
	List<VenueBaseInfo> getAllUnconfirmedVenueInfos();
	
	VenueBaseInfo getVenueInfo(String venueID);
	
	boolean preChangeVenueInfo(VenueBaseInfoChange venueInfo);
	
	List<VenueBaseInfoRedundancy> getAllVenueInfoRedundancies();
	
	boolean ChangeVenueInfo(String venueID);

}
