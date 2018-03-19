package tickets.serviceImpl.venue;

import org.springframework.stereotype.Service;
import tickets.dao.venue.VenueHallDao;
import tickets.dao.venue.VenuePlanDao;
import tickets.daoImpl.Common;
import tickets.daoImpl.ParaName;
import tickets.model.user.UserOdSeat;
import tickets.model.venue.VenueHall;
import tickets.model.venue.VenuePlan;
import tickets.service.venue.VenuePlanService;
import tickets.serviceImpl.CommonService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("venuePlanService")
public class VenuePlanServiceImpl implements VenuePlanService {
	
	@Resource(name = "venuePlanDao")
	private VenuePlanDao venuePlanDao;
	
	@Resource(name = "venueHallDao")
	private VenueHallDao venueHallDao;
	
	@Override
	public String addNewVenuePlan(VenuePlan venuePlan){
		final int numOfTLeft = venuePlan.getNumOfTicket();
		final int numOfTSeated = 0;
		final int numOfTUnallocated = 0;
		venuePlan.setNumOfTLeft(numOfTLeft);
		venuePlan.setNumOfTSeated(numOfTSeated);
		venuePlan.setNumOfTUnallocated(numOfTUnallocated);
		
		String venueID = venuePlan.getVenueID();
		List<String> planIDs = venuePlanDao.selectAllVenuePlanIDs( venuePlan.getVenueID() );
		List<String> planIDBacks = new ArrayList<>();
		for( String planID : planIDs ){
			planIDBacks.add(planID.split("-")[1]);
		}
		final int planIDLen = 7;
		String planID = venueID + "-" + CommonService.getRandomString(planIDLen, planIDBacks);
		venuePlan.setPlanID(planID);
		
		VenueHall venueHall = venueHallDao.selectVenueHall(venuePlan.getHallID());
		String seatDist = venueHall.getSeatDist();
		seatDist = CommonService.hexadecimalToOneZero(seatDist);
		seatDist = CommonService.fourToHexadecimal(seatDist);
		venuePlan.setSeatDist(seatDist);
		venuePlanDao.insertNewVenuePlan(venuePlan);
		
		return planID;
	}
	
	@Override
	public List<VenuePlan> getAllVenuePlansByVenueID(String venueID){
		List<VenuePlan> venuePlans = venuePlanDao.selectAllVenuePlansByVenueID(venueID);
		List<VenuePlan> venuePlanUnifyBack = CommonService.venuePlanUnifyBack(venuePlans);
		return venuePlanUnifyBack;
	}
	
	@Override
	public void updateVenuePlanSeatDist(String planID, List<UserOdSeat> userOdSeats, String stateExchangeTo){
		VenuePlan venuePlan = venuePlanDao.selectVenuePlanInfo(planID);
		int col = venuePlan.getNumOfCol();
		String seatDist = venuePlan.getSeatDist();
		seatDist = CommonService.hexadecimalToFour(seatDist);
		for( UserOdSeat userOdSeat : userOdSeats ){
			int seatRow = userOdSeat.getRow();
			int seatCol = userOdSeat.getCol();
			String front = seatDist.substring(0, (seatRow-1)*col + (seatCol-1));
			String back = seatDist.substring((seatRow-1)*col + seatCol, seatDist.length());
//			seatDist = front + ParaName.seat_unavailable + back;
			seatDist = front + stateExchangeTo + back;
		}
		seatDist = CommonService.fourToHexadecimal(seatDist);
		venuePlanDao.updateVenuePlanSeatDist(planID, seatDist);
	}
	
	
}
