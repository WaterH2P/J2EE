package tickets.serviceImpl.venue;

import org.springframework.stereotype.Service;
import tickets.dao.venue.VenueSeatLevelDao;
import tickets.model.VenueSeatLevel;
import tickets.service.venue.VenueSeatLevelService;
import tickets.serviceImpl.CommonService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("venueSeatLevelService")
public class VenueSeatLevelServiceImpl implements VenueSeatLevelService{

	@Resource(name = "venueSeatLevelDao")
	private VenueSeatLevelDao venueSeatLevelDao;
	
	@Override
	public String addSeatLevel(VenueSeatLevel venueSeatLevel){
		String venueID = venueSeatLevel.getVenueID();
		List<VenueSeatLevel> venueSeatLevels = venueSeatLevelDao.selectAllSeatLevels(venueID);
		List<String> seatIDs = new ArrayList<>();
		for( VenueSeatLevel seatLevel : venueSeatLevels ){
			seatIDs.add( seatLevel.getSeatID().split("-")[1] );
		}
		final int seatIDLen = 7;
		String seatID = venueID + "_" +CommonService.getRandomString(seatIDLen, seatIDs);
		venueSeatLevel.setSeatID(seatID);
		if( venueSeatLevelDao.insertSeatLevel(venueSeatLevel) ){
			return seatID;
		}
		else {
			return "";
		}
	}
	
	@Override
	public boolean deleteSeatLevel(String seatID){
		return venueSeatLevelDao.deleteSeatLevel(seatID);
	}
	
	@Override
	public List<VenueSeatLevel> getAllSeatLevels(String venueID){
		return venueSeatLevelDao.selectAllSeatLevels(venueID);
	}
	
}
