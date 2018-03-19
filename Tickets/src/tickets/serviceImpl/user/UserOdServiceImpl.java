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
		List<VenuePlan> venuePlansOwnBlankSeat = new ArrayList<>();
		for( VenuePlan venuePlan : venuePlanUnifyBack ){
			if( venuePlan.getNumOfTLeft()>0 ){
				venuePlansOwnBlankSeat.add(venuePlan);
			}
		}
		return venuePlansOwnBlankSeat;
	}
	
	@Override
	public List<VenuePlanSeat> getPlanHallSeatInfo(String hallID){
		return venueHallDao.selectPlanHallSeatInfo(hallID);
	}
	
	@Override
	public String makeNewOrderSeated(String email, String planID, String seatSelected, String totalPrice, boolean isOnline){
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
		userOd.setOnline(isOnline);
		
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
//			更新计划中座位数量信息
			venuePlanDao.updateVenuePlanNumOfT(planID, -numOfTicket, numOfTicket, 0);
//			更新计划的座位图
			venuePlanService.updateVenuePlanSeatDist(planID, seatRowAndCols);
			return OdID;
		}
	}
	
	@Override
	public String makeNewOrderUnseated(String email, String planID, String numOfTBought, boolean isOnline){
		UserOd userOd = new UserOd();
		
		final int OdIDLen = 14;
		List<String> OdIDs = userOdDao.selectAllUserOdIDs(email);
		String OdID = CommonService.getRandomString(OdIDLen, OdIDs);
		userOd.setOdID(OdID);
		
		userOd.setEmail(email);
		userOd.setPlanID(planID);
		
		VenuePlan venuePlan = venuePlanDao.selectVenuePlanInfo(planID);
		int numOfTicket = Integer.valueOf(numOfTBought);
		if( numOfTicket<=venuePlan.getNumOfTLeft() ){
			double totalPrice = numOfTicket * venuePlan.getPrice();
			
			userOd.setNumOfTicket(numOfTicket);
			userOd.setTotalPrice(totalPrice);
			userOd.setMakeTime(new Date());
			userOd.setOnline(isOnline);
			
			userOdDao.insertNewUserOdUnseated(userOd);
//			更新计划中座位数量信息
			venuePlanDao.updateVenuePlanNumOfT(planID, -numOfTicket, 0, numOfTicket);
			
			return OdID;
		}
		else {
			return ParaName.return_false;
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
				double vipDiscount = (100 - percent) * userOd.getTotalPrice() / 100.0;
				vipDiscount = Double.valueOf( df.format(vipDiscount) );
				int couponDiscount = userCoupon.getDiscount();
				double totalPay = userOd.getTotalPrice() - vipDiscount - couponDiscount;
				totalPay = Double.valueOf( df.format(totalPay) );
				if( totalPay<0 ){
					totalPay = 0;
				}
				if( userInfo.getBalance()>totalPay ){
					if( userInfoDao.updateUserBalance(email, -totalPay) ){
						userOdDao.updateUserOdIsPaid(OdID, vipDiscount, couponDiscount, totalPay);
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
				return false;
			}
		}
		else{
			return false;
		}
	}
	
}
