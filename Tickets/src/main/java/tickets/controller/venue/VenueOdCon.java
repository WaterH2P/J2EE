package tickets.controller.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.controller.CommonCon;
import tickets.daoImpl.ParaName;
import tickets.model.Result;
import tickets.model.mgr.VIPLevelInfo;
import tickets.model.user.UserCoupon;
import tickets.model.user.UserOd;
import tickets.model.user.UserOdSeat;
import tickets.service.user.UserOdService;
import tickets.service.venue.VenueOdService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class VenueOdCon {
	
	@Autowired
	HttpServletRequest request;
	
	@Resource(name = "userOdService")
	private UserOdService userOdService;
	
	@Resource(name = "venueOdService")
	private VenueOdService venueOdService;
	
	@ResponseBody
	@RequestMapping(value = "/Venue/MakeNewOdSeated", method = RequestMethod.POST)
	public Result makeNewOdSeated(String email, String planID, String seatSelected, String totalPrice){
		Result result = new Result();
		result.setResult(false);
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				if( email==null || email.length()==0 ){
					email = ParaName.account_fictitious;
				}
				else {
					email = email.toLowerCase();
				}
				final boolean isOnline = false;
				String OdID = userOdService.makeNewOrderSeated(email, planID, seatSelected, totalPrice, isOnline);
				result = CommonCon.strCheck(OdID, ParaName.return_false);
			}
		}
		if( !result.getResult() && result.getMessage().length()==0 ){
			result.setMessage(ParaName.message_ownNoAuthority);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Venue/PayOrder", method = RequestMethod.POST)
	public Result payOrder(String email, String OdID, String couponID){
		Result result = new Result();
		result.setResult(false);
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				if( email==null || email.length()==0 ){
					email = ParaName.account_fictitious;
				}
				else {
					email = email.toLowerCase();
				}
				if( userOdService.payOd(email, OdID, couponID) ){
					result.setResult(true);
					String message = "支付成功！";
					result.setMessage(message);
				}
				else {
					result.setResult(false);
					String message = "支付出错！";
					result.setMessage(message);
				}
			}
		}
		if( !result.getResult() && result.getMessage().length()==0 ){
			result.setMessage(ParaName.message_ownNoAuthority);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Venue/CancelOrder", method = RequestMethod.POST)
	public Result cancelOrder(String email, String OdID){
		Result result = new Result();
		result.setResult(false);
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				if( email==null || email.length()==0 ){
					email = ParaName.account_fictitious;
				}
				else {
					email = email.toLowerCase();
				}
				if( userOdService.deleteOd(email, OdID) ){
					result.setResult(true);
					String message = "退单成功！";
					result.setMessage(message);
				}
			}
		}
		if( !result.getResult() && result.getMessage().length()==0 ){
			result.setMessage(ParaName.message_ownNoAuthority);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Venue/GetUserVIPDiscount", method = RequestMethod.POST)
	public VIPLevelInfo getUserVIPDiscount(String email){
		VIPLevelInfo vipLevelInfo = new VIPLevelInfo();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String) session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				if( email==null || email.length()==0 ){
					email = ParaName.account_fictitious;
				}
				else {
					email = email.toLowerCase();
				}
				vipLevelInfo = venueOdService.getUserVIPDiscount(email);
			}
		}
		return vipLevelInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Venue/GetAllUserCoupons", method = RequestMethod.POST)
	public List<UserCoupon> getAllUserCoupons(String email){
		List<UserCoupon> userCoupons = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				if( email==null || email.length()==0 ){
					return userCoupons;
				}
				else {
					email = email.toLowerCase();
				}
				userCoupons = venueOdService.getAllUserCoupons(email);
			}
		}
		return userCoupons;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Venue/VenuePlanCheckTicket", method = RequestMethod.POST)
	public Result venuePlanCheckTicket(String OdID, String planID){
		Result result = new Result();
		result.setResult(false);
		String message = "";
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				message = userOdService.checkUserOd(OdID, planID);
				if( message.equals(ParaName.return_true) ){
					result.setResult(true);
					message = "检票成功！";
					result.setMessage(message);
				}
				else {
					result.setMessage(message);
				}
			}
		}
		if( !result.getResult() && result.getMessage().length()==0 ){
			result.setMessage(ParaName.message_ownNoAuthority);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Venue/GetPlanUserOdCheckedSeatInfo", method = RequestMethod.POST)
	public List<UserOdSeat> getPlanUserOdCheckedSeatInfo(String OdID){
		List<UserOdSeat> userOdSeats = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				userOdSeats = userOdService.getPlanUserOdCheckedSeatInfo(OdID);
			}
		}
		return userOdSeats;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Venue/GetPlanUserOdInfo", method = RequestMethod.POST)
	public List<UserOd> getPlanUserOdInfo(String planID){
		List<UserOd> userOds = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				userOds = userOdService.getAllPlanUserOd(planID);
			}
		}
		return userOds;
	}
}
