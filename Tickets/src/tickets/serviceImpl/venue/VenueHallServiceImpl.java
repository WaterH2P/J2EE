package tickets.serviceImpl.venue;

import org.springframework.stereotype.Service;
import tickets.dao.venue.VenueHallDao;
import tickets.model.VenueHall;
import tickets.service.venue.VenueHallService;
import tickets.serviceImpl.CommonService;

import javax.annotation.Resource;
import java.util.*;

@Service("venueHallService")
public class VenueHallServiceImpl implements VenueHallService {
	
	@Resource(name = "venueHallDao")
	private VenueHallDao venueHallDao;
	
	@Override
	public boolean addNewHall(VenueHall venueHall, String seatLevel){
		Map<Integer, String> seatRowLevel = new HashMap<>();
		String[] temp1 = seatLevel.split("==");
		for( int i=0; i<temp1.length; i++ ){
			String[] temp2 = temp1[i].split("/+/+");
			if( temp2.length==2 ){
				String[] temp3 = temp2[1].split("_");
				for( int j=0; j<temp3.length; j++ ){
					seatRowLevel.put(Integer.valueOf(temp3[j]), temp2[0]);
				}
			}
		}
		
		String venueID = venueHall.getVenueID();
		List<String> hallIDs = venueHallDao.selectAllVenueHallIDs(venueID);
		List<String> hallIDBacks = new ArrayList<>();
		for( String hallID : hallIDs ){
			hallIDBacks.add(hallID.split("-")[1]);
		}
		final int hallIDLen = 7;
		String hallID = venueID + "-" + CommonService.getRandomString(hallIDLen, hallIDBacks);
		venueHall.setHallID(hallID);
		
		return venueHallDao.insertNewHall(venueHall, seatRowLevel);
	}
	
}
