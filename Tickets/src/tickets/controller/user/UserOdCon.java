package tickets.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.controller.CommonCon;
import tickets.daoImpl.ParaName;
import tickets.model.Result;
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
				return CommonUser.toUserOrderPage();
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
				return CommonUser.toUserBuyTicketOnlinePage();
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
				result = strCheck(OdID, ParaName.return_false);
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
				result = strCheck(OdID, ParaName.return_false);
			}
		}
		if( !result.getResult() && result.getMessage().length()==0 ){
			result.setMessage(ParaName.message_ownNoAuthority);
		}
		return result;
	}
	
	private Result strCheck(String strChecked, String strChecking){
		Result result = new Result();
		if( !strChecked.equals(strChecking) ){
			result.setResult(true);
			result.setMessage(strChecked);
		}
		else {
			result.setResult(false);
			String message = "购票冲突，请刷新重试！";
			result.setMessage(message);
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
	
	
}
