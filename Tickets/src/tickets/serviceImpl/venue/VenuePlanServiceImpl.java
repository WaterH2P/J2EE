package tickets.serviceImpl.venue;

import org.springframework.stereotype.Service;
import tickets.dao.user.UserOdDao;
import tickets.dao.venue.VenueHallDao;
import tickets.dao.venue.VenuePlanDao;
import tickets.daoImpl.Common;
import tickets.daoImpl.ParaName;
import tickets.daoImpl.user.UserOdDaoImpl;
import tickets.daoImpl.venue.VenueHallDaoImpl;
import tickets.daoImpl.venue.VenuePlanDaoImpl;
import tickets.model.user.UserOd;
import tickets.model.user.UserOdSeat;
import tickets.model.venue.VenueHall;
import tickets.model.venue.VenuePlan;
import tickets.model.venue.VenuePlanSeat;
import tickets.service.venue.VenuePlanService;
import tickets.serviceImpl.CommonService;

import javax.annotation.Resource;
import java.util.*;

@Service("venuePlanService")
public class VenuePlanServiceImpl implements VenuePlanService {
	
	@Resource(name = "venuePlanDao")
	private VenuePlanDao venuePlanDao = new VenuePlanDaoImpl();
	
	@Resource(name = "venueHallDao")
	private VenueHallDao venueHallDao = new VenueHallDaoImpl();
	
	private UserOdDao userOdDao = new UserOdDaoImpl();
	
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
	public List<VenuePlan> getAllVenuePlansIsNotCheckedByVenueID(String venueID){
		List<VenuePlan> venuePlans = venuePlanDao.selectAllVenuePlansIsNotCheckedByVenueID(venueID);
		List<VenuePlan> venuePlanUnifyBack = CommonService.venuePlanUnifyBack(venuePlans);
		return venuePlanUnifyBack;
	}
	
	@Override
	public List<VenuePlan> getAllVenuePlansIsCheckingByVenueID(String venueID){
		List<VenuePlan> venuePlans = venuePlanDao.selectAllVenuePlansIsCheckingByVenueID(venueID);
		List<VenuePlan> venuePlanUnifyBack = CommonService.venuePlanUnifyBack(venuePlans);
		return venuePlanUnifyBack;
	}
	
	@Override
	public List<VenuePlan> getAllVenuePlansIsCheckedByVenueID(String venueID){
		List<VenuePlan> venuePlans = venuePlanDao.selectAllVenuePlansIsCheckedByVenueID(venueID);
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
			seatDist = front + stateExchangeTo + back;
		}
		seatDist = CommonService.fourToHexadecimal(seatDist);
		venuePlanDao.updateVenuePlanSeatDist(planID, seatDist);
	}
	
	@Override
	public List<VenuePlanSeat> getPlanHallSeatInfo(String hallID){
		return venueHallDao.selectPlanHallSeatInfo(hallID);
	}
	
	@Override
	public boolean setVenuePlanIsChecking(String planID){
		VenuePlan venuePlan = venuePlanDao.selectVenuePlanInfo(planID);
		if( venuePlan.isChecking() || venuePlan.isChecked() ){
			return false;
		}
		else {
			venuePlanDao.updateVenuePlanIsChecking(planID);
			return true;
		}
	}
	
	@Override
	public boolean setVenuePlanIsChecked(String planID){
		VenuePlan venuePlan = venuePlanDao.selectVenuePlanInfo(planID);
		if( !venuePlan.isChecking() || venuePlan.isChecked() ){
			return false;
		}
		else {
			venuePlanDao.updateVenuePlanIsChecked(planID);
			return true;
		}
	}
	
	@Override
	public void allocateSeatToUserOd_IsPaid_IsNotSeated(){
		List<VenuePlan> venuePlans = venuePlanDao.selectAllFutureVenuePlan();
		for( VenuePlan venuePlan : venuePlans ){
			Date beginTime = venuePlan.getBeginTime();
			Date now = new Date();
			Date future = new Date(now.getTime() + 14 * 24 * 3600 * 1000 );
			if( beginTime.compareTo(future)<0 ){
				String seatDist = venuePlan.getSeatDist();
				seatDist = CommonService.hexadecimalToFour(seatDist);
				
				int col = venuePlan.getNumOfCol();
				
				String hallID = venuePlan.getHallID();
				List<VenuePlanSeat> venuePlanSeats = venueHallDao.selectPlanHallSeatInfo(hallID);
				Map<Integer, Integer> seatPer = new HashMap<>();
				for( VenuePlanSeat venuePlanSeat : venuePlanSeats ){
					int row = Integer.valueOf(venuePlanSeat.getRow());
					if( !seatPer.keySet().contains(row) ){
						int percent = Integer.valueOf( venuePlanSeat.getPercent() );
						seatPer.put( row, percent );
					}
				}
				
				double price = venuePlan.getPrice();
				String planID = venuePlan.getPlanID();
				List<UserOd> userOds = userOdDao.selectAllPlanUserOd_isPaid_isNotDeleted_isNotSeated(planID);
				List<UserOdSeat> userOdSeats = new ArrayList<>();
				int numOfTAllocated = 0;
				for( UserOd userOd : userOds ){
					if( userOd.isPaid() && !userOd.isDeleted() && !userOd.isSeated() ){
						int numOfTicket = userOd.getNumOfTicket();
						for( int i=0; i<numOfTicket; i++ ){
							numOfTAllocated++;
							
							int index = seatDist.indexOf('1');
							int seatRow = 0;
							int seatCol = (index + 1) % col;
							if( seatCol==0 ){
								seatRow = (index + 1) / col;
								seatCol = col;
							}
							else {
								seatRow = (index + 1) / col + 1;
							}
							double seatPrice = price * seatPer.get(seatRow);
							
							UserOdSeat userOdSeat = new UserOdSeat();
							userOdSeat.setOdID(userOd.getOdID());
							userOdSeat.setPlanID(planID);
							userOdSeat.setRow(seatRow);
							userOdSeat.setCol(seatCol);
							userOdSeat.setPrice(seatPrice);
							userOdSeats.add(userOdSeat);
							
							seatDist = seatDist.substring(0, index) + ParaName.seat_unavailable + seatDist.substring(index+1);
						}
//						更新用户订单状态
						userOdDao.updateUserOdIsSeated(userOd.getOdID());
					}
				}
//				在表中插入用户订单座位
				userOdDao.insertUserOdSeat(userOdSeats);
//				更新场馆计划座位数量信息
				venuePlanDao.updateVenuePlanNumOfTicket(planID, 0, numOfTAllocated, -numOfTAllocated);
				seatDist = CommonService.fourToHexadecimal(seatDist);
//				更新场馆座位座位图
				venuePlanDao.updateVenuePlanSeatDist(planID, seatDist);
			}
		}
	}
	
}
