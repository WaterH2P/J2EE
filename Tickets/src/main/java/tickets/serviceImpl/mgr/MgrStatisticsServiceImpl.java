package tickets.serviceImpl.mgr;

import org.springframework.stereotype.Service;
import tickets.dao.user.UserInfoDao;
import tickets.dao.user.UserOdDao;
import tickets.dao.venue.VenueBaseInfoDao;
import tickets.dao.venue.VenueHallDao;
import tickets.dao.venue.VenuePlanDao;
import tickets.model.mgr.UserStatistic;
import tickets.model.mgr.VenueStatistic;
import tickets.model.user.UserInfo;
import tickets.model.user.UserOd;
import tickets.model.venue.VenueBaseInfo;
import tickets.model.venue.VenueHall;
import tickets.model.venue.VenuePlan;
import tickets.service.mgr.MgrStatisticsService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("mgrStatisticsService")
public class MgrStatisticsServiceImpl implements MgrStatisticsService {
	
	@Resource(name = "userInfoDao")
	private UserInfoDao userInfoDao;
	
	@Resource(name = "userOdDao")
	private UserOdDao userOdDao;
	
	@Resource(name = "venueHallDao")
	private VenueHallDao venueHallDao;
	
	@Resource(name = "venueBaseInfoDao")
	private VenueBaseInfoDao venueBaseInfoDao;
	
	@Resource(name = "venuePlanDao")
	private VenuePlanDao venuePlanDao;
	
	@Override
	public List<UserStatistic> getAllUserStatistics(){
		List<UserStatistic> userStatistics = new ArrayList<>();
		List<UserInfo> userInfos = userInfoDao.selectAllUserInfo();
		for( UserInfo userInfo : userInfos ){
			UserStatistic userStatistic1 = new UserStatistic();
			userStatistic1.setEmail(userInfo.getEmail());
			userStatistic1.setName(userInfo.getName());
			userStatistic1.setVipLevel(userInfo.getVipLevel());
			userStatistic1.setTotalPoint(userInfo.getTotalPoint());
			String email = userInfo.getEmail();
			List<UserOd> userOds = userOdDao.selectUserAllOd(email);
			for( UserOd userOd : userOds ){
				userStatistic1.setNumOfTicket( userStatistic1.getNumOfTicket() + userOd.getNumOfTicket() );
				if( userOd.isPaid() && userOd.isChecked() ){
					userStatistic1.setTotalConsume( userStatistic1.getTotalConsume() + userOd.getTotalPrice() );
				}
				if( userOd.isTimeout() ){
					userStatistic1.setNumOfTTimeout( userStatistic1.getNumOfTTimeout() + userOd.getNumOfTicket() );
				}
				if( userOd.isDeleted() ){
					userStatistic1.setNumOfTDeleted( userStatistic1.getNumOfTDeleted() + userOd.getNumOfTicket() );
				}
				if( userOd.isOnline() ){
					userStatistic1.setNumOfTOnline( userStatistic1.getNumOfTOnline() + userOd.getNumOfTicket() );
				}
			}
			userStatistics.add(userStatistic1);
		}
		return userStatistics;
	}
	
	@Override
	public List<VenueStatistic> getAllVenueStatistics(){
		List<VenueStatistic> venueStatistics = new ArrayList<>();
		List<VenueBaseInfo> venueBaseInfos = venueBaseInfoDao.selectAllVenueInfo();
		for( VenueBaseInfo venueBaseInfo : venueBaseInfos ){
			VenueStatistic venueStatistic = new VenueStatistic();
			
			venueStatistic.setVenueID(venueBaseInfo.getVenueID());
			venueStatistic.setAddress(venueBaseInfo.getAddress());
			venueStatistic.setTelephone(venueBaseInfo.getTelephone());
			
			List<VenueHall> venueHalls = venueHallDao.selectAllVenueHalls(venueBaseInfo.getVenueID());
			venueStatistic.setNumOfHall(venueHalls.size());
			
			List<VenuePlan> venuePlans = venuePlanDao.selectVenueAllPlan(venueBaseInfo.getVenueID());
			venueStatistic.setNumOfPlan(venuePlans.size());
			for( VenuePlan venuePlan : venuePlans ){
				if( venuePlan.isChecking() ){
					venueStatistic.setNumOfPlanIsChecking(venueStatistic.getNumOfPlanIsChecking() + 1);
				}
				if( venuePlan.isChecked() ){
					venueStatistic.setNumOfPlanIsChecked(venueStatistic.getNumOfPlanIsChecked() + 1);
				}
				
				List<UserOd> userOds = userOdDao.selectAllPlanUserOd(venuePlan.getPlanID());
				for( UserOd userOd : userOds ){
					if( userOd.isChecked() ){
						venueStatistic.setTotalPrice(venueStatistic.getTotalPrice()+userOd.getTotalPay());
					}
				}
			}
			
			venueStatistics.add(venueStatistic);
		}
		return venueStatistics;
	}
	
}
