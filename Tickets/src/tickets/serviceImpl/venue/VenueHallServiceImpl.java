package tickets.serviceImpl.venue;

import org.springframework.stereotype.Service;
import tickets.dao.venue.VenueHallDao;
import tickets.model.venue.VenueHall;
import tickets.model.venue.VenueHallSeat;
import tickets.service.venue.VenueHallService;
import tickets.serviceImpl.CommonService;

import javax.annotation.Resource;
import java.util.*;

@Service("venueHallService")
public class VenueHallServiceImpl implements VenueHallService {
	
	@Resource(name = "venueHallDao")
	private VenueHallDao venueHallDao;
	
	@Override
	public VenueHall addNewVenueHall(VenueHall venueHall, String seatData, String seatLevel){
		String seatUnify = CommonService.unifyStr(seatData);
		String seatDist = CommonService.oneZeroToHexadecimal(seatUnify);
		venueHall.setSeatDist(seatDist);
		
		String venueID = venueHall.getVenueID();
		List<String> hallIDs = venueHallDao.selectAllVenueHallIDs(venueID);
		List<String> hallIDBacks = new ArrayList<>();
		for( String hallID : hallIDs ){
			hallIDBacks.add(hallID.split("-")[1]);
		}
		final int hallIDLen = 7;
		String hallID = venueID + "-" + CommonService.getRandomString(hallIDLen, hallIDBacks);
		venueHall.setHallID(hallID);
		
		Map<Integer, String> seatRowLevel = new HashMap<>();
		String[] temp1 = seatLevel.split("==");
		for( int i=0; i<temp1.length; i++ ){
			String[] temp2 = temp1[i].split("--");
			if( temp2.length==2 ){
				String[] temp3 = temp2[1].split("_");
				for( int j=0; j<temp3.length; j++ ){
					seatRowLevel.put(Integer.valueOf(temp3[j]), temp2[0]);
				}
			}
		}
		List<VenueHallSeat> venueHallSeats = new ArrayList<>();
		for( int i=0; i<venueHall.getNumOfRow(); i++ ){
			for( int j=0; j<venueHall.getNumOfCol(); j++ ){
				int row = i + 1;
				int col = j + 1;
				VenueHallSeat venueHallSeat = new VenueHallSeat();
				venueHallSeat.setHallID( venueHall.getHallID() );
				venueHallSeat.setRow( row );
				venueHallSeat.setCol( col );
				venueHallSeat.setSeatID( seatRowLevel.get(row) );
				if( seatUnify.charAt(i*venueHallSeat.getCol()+j)=='1' ){
					venueHallSeat.setState( CommonVenueService.state_available );
				}
				else {
					venueHallSeat.setState( CommonVenueService.state_none );
				}
				venueHallSeats.add(venueHallSeat);
			}
		}
		
		if( venueHallDao.insertNewVenueHall(venueHall, venueHallSeats) ){
			VenueHall venueHallUnifyBack = new VenueHall();
			venueHallUnifyBack = venueHallUnifyBack( venueHall );
			return venueHallUnifyBack;
		}
		else {
			return new VenueHall();
		}
	}
	
	@Override
	public List<VenueHall> getAllVenueHalls(String venueID){
		List<VenueHall> venueHalls = venueHallDao.selectAllVenueHalls(venueID);
		if( venueHalls.size()>0 ){
			for( VenueHall venueHall : venueHalls ){
				venueHall = venueHallUnifyBack( venueHall );
			}
		}
		return venueHalls;
	}
	
	@Override
	public void deleteVenueHall(String hallID){
		venueHallDao.deleteVenueHall(hallID);
	}
	
	private static VenueHall venueHallUnifyBack(VenueHall venueHall){
		int numOfSeat = venueHall.getNumOfRow() * venueHall.getNumOfCol();
		String seatData = CommonService.hexadecimalToOneZero(venueHall.getSeatDist());
		seatData = seatData.substring(0, numOfSeat);
		seatData = CommonService.unifyStrBack(seatData);
		venueHall.setSeatDist(seatData);
		return venueHall;
	}
}
