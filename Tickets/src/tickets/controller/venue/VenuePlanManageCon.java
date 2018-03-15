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
	
	@RequestMapping(value = "/VenuePlanManage", method = RequestMethod.GET)
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
		if( !result.getResult() ){
			message = "很抱歉你没有权限！";
			result.setMessage(message);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Venue/GetAllVenuePlans", method = RequestMethod.POST)
	public List<VenuePlan> getAllVenuePlan(){
		List<VenuePlan> venuePlan = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				venuePlan = venuePlanService.getAllVenuePlans(venueID);
			}
		}
		return venuePlan;
	}
	
}
