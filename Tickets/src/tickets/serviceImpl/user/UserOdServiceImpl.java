package tickets.serviceImpl.user;

import org.springframework.stereotype.Service;
import tickets.dao.mgr.MgrVIPLevelDao;
import tickets.dao.user.UserCouponDao;
import tickets.dao.user.UserInfoDao;
import tickets.dao.user.UserOdDao;
import tickets.dao.venue.VenueHallDao;
import tickets.dao.venue.VenuePlanDao;
import tickets.daoImpl.ParaName;
import tickets.model.mgr.VIPLevelInfo;
import tickets.model.user.UserCoupon;
import tickets.model.user.UserInfo;
import tickets.model.user.UserOd;
import tickets.model.user.UserOdSeat;
import tickets.model.venue.VenuePlan;
import tickets.model.venue.VenuePlanSeat;
import tickets.service.user.UserInfoService;
import tickets.service.user.UserOdService;
import tickets.service.venue.VenuePlanService;
import tickets.serviceImpl.CommonService;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service( "userOdService" )
public class UserOdServiceImpl implements UserOdService {
	
	@Resource(name = "venuePlanDao")
	private VenuePlanDao venuePlanDao;
	
	@Resource(name = "venueHallDao")
	private VenueHallDao venueHallDao;
	
	@Resource(name = "venuePlanService")
	private VenuePlanService venuePlanService;
	
	@Resource(name = "userOdDao")
	private UserOdDao userOdDao;
	
	@Resource(name = "userInfoService")
	private UserInfoService userInfoService;
	
	@Resource(name = "userCouponDao")
	private UserCouponDao userCouponDao;
	
	@Resource(name = "mgrVIPLevelDao")
	private MgrVIPLevelDao mgrVIPLevelDao;
	
	@Resource(name = "userInfoDao")
	private UserInfoDao userInfoDao;
	
	private DecimalFormat df = new DecimalFormat("#0.00");
	
	@Override
	public List<VenuePlan> searchPlanByPlanName(String planName){
		List<VenuePlan> venuePlans = venuePlanDao.selectAllVenuePlansByPlanName(planName);
		List<VenuePlan> venuePlanUnifyBack = CommonService.venuePlanUnifyBack(venuePlans);
		return venuePlanUnifyBack;
	}
	
	@Override
	public List<VenuePlanSeat> getPlanHallSeatInfo(String hallID){
		return venueHallDao.selectPlanHallSeatInfo(hallID);
	}
	
	@Override
	public String makeNewOrder(String email, String planID, String seatSelected, String totalPrice){
		UserOd userOd = new UserOd();
		List<UserOdSeat> userOdSeats = new ArrayList<>();
		
		final int OdIDLen = 14;
		List<String> OdIDs = userOdDao.selectAllUserOdIDs(email);
		String OdID = CommonService.getRandomString(OdIDLen, OdIDs);
		userOd.setOdID(OdID);
		
		userOd.setEmail(email);
		userOd.setPlanID(planID);
		
		int numOfTicket = 0;
		String[] seatRowAndCols = seatSelected.split("==");
		for( String seatRowAndCol : seatRowAndCols ){
			if( seatRowAndCol.length()>0 ){
				String temp[] = seatRowAndCol.split("--");
				numOfTicket++;
				UserOdSeat userOdSeat = new UserOdSeat();
				userOdSeat.setOdID(OdID);
				userOdSeat.setPlanID(planID);
				userOdSeat.setRow( Integer.valueOf(temp[0]) );
				userOdSeat.setCol( Integer.valueOf(temp[1]) );
				userOdSeat.setPrice( Double.valueOf(temp[2]) );
				userOdSeats.add(userOdSeat);
			}
		}
		userOd.setNumOfTicket(numOfTicket);
		userOd.setTotalPrice( Double.valueOf(totalPrice) );
		userOd.setMakeTime( new Date() );
		
		List<UserOdSeat> planOdSeats = userOdDao.selectPlanAllOdSeat(planID);
		boolean isExist = false;
		for( int i=0; i<planOdSeats.size(); i++ ){
			UserOdSeat planOdSeat = planOdSeats.get(i);
			for( int j=0; j<userOdSeats.size(); j++ ){
				UserOdSeat userOdSeat = userOdSeats.get(j);
				if( planOdSeat.getRow()==userOdSeat.getRow() && planOdSeat.getCol()==userOdSeat.getCol() ){
					isExist = true;
					break;
				}
			}
		}
		
		if( isExist ){
			return ParaName.return_false;
		}
		else {
			userOdDao.insertNewUserOdSeated(userOd, userOdSeats);
			venuePlanService.updateVenuePlanSeatDist(planID, seatRowAndCols);
			return OdID;
		}
	}
	
	@Override
	public boolean payOd(String email, String OdID, String couponID){
		UserInfo userInfo = new UserInfo();
		userInfo = userInfoService.getUserInfo(email);
		UserOd userOd = new UserOd();
		userOd = userOdDao.selectUserOdInfo(OdID);
		UserCoupon userCoupon = new UserCoupon();
		if( userInfo.getEmail().equals(userOd.getEmail()) ){
			List<UserCoupon> couponInfos = userCouponDao.selectAllUserCoupon(email);
			boolean isExist = false;
			for( int i=0; i<couponInfos.size(); i++ ){
				if( couponInfos.get(i).getCouponID().equals(couponID) ){
					userCoupon = couponInfos.get(i);
					isExist = true;
					break;
				}
			}
			if( isExist ){
				VIPLevelInfo vipLevelInfo = mgrVIPLevelDao.selectVIPInfoByLevel(userInfo.getVipLevel());
				int percent = vipLevelInfo.getPercent();
				double discount = (100 - percent) * userOd.getTotalPrice() / 100.0;
				discount = Double.valueOf( df.format(discount) );
				double totalPay = userOd.getTotalPrice() - discount - userCoupon.getDiscount();
				if( userInfo.getBalance()>totalPay ){
					if( userInfoDao.updateUserBalance(email, totalPay) ){
						return true;
					}
					else {
						return false;
					}
				}
				else {
					return false;
				}
			}
			else{
				return true;
			}
		}
		else{
			return false;
		}
	}
	
}
