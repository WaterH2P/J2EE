package tickets.controller.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.controller.CommonCon;
import tickets.daoImpl.ParaName;
import tickets.model.Result;
import tickets.model.venue.VenuePlan;
import tickets.model.venue.VenuePlanSeat;
import tickets.service.venue.VenuePlanService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class VenuePlanManageCon {
	
	@Autowired
	private HttpServletRequest request;
	
	@Resource(name = "venuePlanService")
	private VenuePlanService venuePlanService;
	
	@RequestMapping(value = "/Venue/VenuePlanManage", method = RequestMethod.GET)
	public String venuePlanManage(){
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				return CommonVenue.toVenuePlanManagePage();
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
	@RequestMapping(value = "/Venue/AddNewVenuePlan", method = RequestMethod.POST)
	public Result addNewVenuePlan(@ModelAttribute("venueHallPlan") VenuePlan venuePlan){
		Result result = new Result();
		result.setResult(false);
		String message = "";
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				venuePlan.setVenueID(venueID);
				message = venuePlanService.addNewVenuePlan(venuePlan);
				result.setResult(true);
				result.setMessage(message);
			}
		}
		if( !result.getResult() && result.getMessage().length()==0 ){
			result.setMessage(ParaName.message_ownNoAuthority);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Venue/GetAllVenuePlansIsNotChecked", method = RequestMethod.POST)
	public List<VenuePlan> getAllVenuePlansIsNotChecked(){
		List<VenuePlan> venuePlan = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				venuePlan = venuePlanService.getAllVenuePlansIsNotCheckedByVenueID(venueID);
			}
			else {
				System.out.println(venueID + " " + ParaName.message_ownNoAuthority);
			}
		}
		return venuePlan;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Venue/GetAllVenuePlansIsChecking", method = RequestMethod.POST)
	public List<VenuePlan> getAllVenuePlansIsChecking(){
		List<VenuePlan> venuePlan = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				venuePlan = venuePlanService.getAllVenuePlansIsCheckingByVenueID(venueID);
			}
			else {
				System.out.println(venueID + " " + ParaName.message_ownNoAuthority);
			}
		}
		return venuePlan;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Venue/GetAllVenuePlansIsChecked", method = RequestMethod.POST)
	public List<VenuePlan> getAllVenuePlansIsChecked(){
		List<VenuePlan> venuePlan = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				venuePlan = venuePlanService.getAllVenuePlansIsCheckedByVenueID(venueID);
			}
			else {
				System.out.println(venueID + " " + ParaName.message_ownNoAuthority);
			}
		}
		return venuePlan;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Venue/GetPlanHallSeatInfo", method = RequestMethod.POST)
	public List<VenuePlanSeat> getPlanHallSeatInfo(String hallID){
		List<VenuePlanSeat> venuePlanSeats = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				venuePlanSeats = venuePlanService.getPlanHallSeatInfo(hallID);
			}
			else {
				System.out.println(venueID + " " + ParaName.message_ownNoAuthority);
			}
		}
		return venuePlanSeats;
	}
	
}
