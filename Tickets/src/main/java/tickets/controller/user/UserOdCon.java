package tickets.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.controller.CommonCon;
import tickets.daoImpl.ParaName;
import tickets.model.Result;
import tickets.model.user.UserOd;
import tickets.model.user.UserOdSeat;
import tickets.model.venue.VenueBaseInfo;
import tickets.model.venue.VenueHall;
import tickets.model.venue.VenuePlan;
import tickets.model.venue.VenuePlanSeat;
import tickets.service.user.UserOdService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserOdCon {
	
	@Autowired
	HttpServletRequest request;
	
	@Resource(name = "userOdService" )
	private UserOdService userOdService;
	
	@RequestMapping(value = "/User/UserOrder")
	public String OrderMainPage(){
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				return ParaNameUser.toUserOrderPage();
			}
			else{
				return CommonCon.redirectToInfoPage();
			}
		}
		else {
			return CommonCon.redirectToLoginPage();
		}
	}
	
	@RequestMapping(value = "/User/UserBuyTicket", method = RequestMethod.GET)
	public String userBuyTicket(){
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				return ParaNameUser.toUserBuyTicketOnlinePage();
			}
			else{
				return CommonCon.redirectToInfoPage();
			}
		}
		else {
			return CommonCon.redirectToLoginPage();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/User/SearchVenuePlanByPlanName", method = RequestMethod.POST)
	public List<VenuePlan> searchVenuePlanByPlanName(String planName){
		List<VenuePlan> venuePlans = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				venuePlans = userOdService.searchPlanByPlanName(planName);
			}
		}
		return venuePlans;
	}
	
	@ResponseBody
	@RequestMapping(value = "/User/GetPlanHallSeatInfo", method = RequestMethod.POST)
	public List<VenuePlanSeat> getPlanHallSeatInfo(String hallID){
		List<VenuePlanSeat> venuePlanSeats = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				venuePlanSeats = userOdService.getPlanHallSeatInfo(hallID);
			}
		}
		return venuePlanSeats;
	}
	
	@ResponseBody
	@RequestMapping(value = "/User/GetPlanHallInfo", method = RequestMethod.POST)
	public VenueHall getPlanHallInfo(String planID){
		VenueHall venueHall = new VenueHall();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				venueHall = userOdService.getPlanHallInfo(planID);
			}
		}
		return venueHall;
	}
	
	@ResponseBody
	@RequestMapping(value = "/User/GetPlanVenueInfo", method = RequestMethod.POST)
	public VenueBaseInfo getPlanVenueInfo(String planID){
		VenueBaseInfo venueBaseInfo = new VenueBaseInfo();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				venueBaseInfo = userOdService.getPlanVenueInfo(planID);
			}
		}
		return venueBaseInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/User/MakeNewOdSeated", method = RequestMethod.POST)
	public Result makeNewOdSeated(String planID, String seatSelected, String totalPrice){
		Result result = new Result();
		result.setResult(false);
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				final boolean isOnline = true;
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
	@RequestMapping(value = "/User/MakeNewOdUnseated", method = RequestMethod.POST)
	public Result makeNewOdUnseated(String planID, String numOfTSelected){
		Result result = new Result();
		result.setResult(false);
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				final boolean isOnline = true;
				String OdID = userOdService.makeNewOrderUnseated(email, planID, numOfTSelected, isOnline);
				result = CommonCon.strCheck(OdID, ParaName.return_false);
			}
		}
		if( !result.getResult() && result.getMessage().length()==0 ){
			result.setMessage(ParaName.message_ownNoAuthority);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/User/PayOrder", method = RequestMethod.POST)
	public Result payOrder(String OdID, String couponID){
		Result result = new Result();
		result.setResult(false);
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
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
	@RequestMapping(value = "/User/DeleteOrder", method = RequestMethod.POST)
	public Result deleteOrder(String OdID){
		Result result = new Result();
		result.setResult(false);
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
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
	@RequestMapping(value = "/User/GetAllHistoricalUserOd", method = RequestMethod.POST)
	public List<UserOd> getAllHistoricalUserOd(){
		List<UserOd> userOds = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				userOds = userOdService.getAllHistoricalUserOd(email);
			}
		}
		return userOds;
	}
	
	@ResponseBody
	@RequestMapping(value = "/User/GetAllUserOdTimeout", method = RequestMethod.POST)
	public List<UserOd> getAllUserOdTimeOut(){
		List<UserOd> userOds = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				userOds = userOdService.getAllUserOdTimeout(email);
			}
		}
		return userOds;
	}
	
	@ResponseBody
	@RequestMapping(value = "/User/GetAllUserOdDeleted", method = RequestMethod.POST)
	public List<UserOd> getAllUserOdDeleted(){
		List<UserOd> userOds = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				userOds = userOdService.getAllUserOdDeleted(email);
			}
		}
		return userOds;
	}
	
	@ResponseBody
	@RequestMapping(value = "/User/GetAllFutureUserOd", method = RequestMethod.POST)
	public List<UserOd> getAllFutureUserOd(){
		List<UserOd> userOds = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				userOds = userOdService.getAllFutureUserOd(email);
			}
		}
		return userOds;
	}
	
	@ResponseBody
	@RequestMapping(value = "/User/GetAllUserOdUnfinished", method = RequestMethod.POST)
	public List<UserOd> getAllUserOdUnfinished(){
		List<UserOd> userOds = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				userOds = userOdService.getAllUserOdUnfinished(email);
			}
		}
		return userOds;
	}
	
	@ResponseBody
	@RequestMapping(value = "/User/GetUserOdPlanInfo", method = RequestMethod.POST)
	public VenuePlan getUserOdPlanInfo(String planID){
		VenuePlan venuePlan = new VenuePlan();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				venuePlan = userOdService.getUserOdPlanInfo(planID);
			}
		}
		return venuePlan;
	}
	
	@ResponseBody
	@RequestMapping(value = "/User/GetUserOdAllSeatSelectedInfo", method = RequestMethod.POST)
	public List<UserOdSeat> getUserOdAllSeatSelectedInfo(String OdID){
		List<UserOdSeat> userOdSeats = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				userOdSeats = userOdService.getUserOdAllSeatSelectedInfo(OdID);
			}
		}
		return userOdSeats;
	}
	
}
