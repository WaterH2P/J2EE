package tickets.controller.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.controller.Common;
import tickets.daoImpl.ParaName;
import tickets.model.Result;
import tickets.model.VenueInfo;
import tickets.model.VenueInfoChange;
import tickets.service.venue.VenueInfoService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class VenueInfoController {
	
	@Resource(name = "venueInfoService" )
	private VenueInfoService venueInfoService;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value = "/VenueInfo", method = RequestMethod.GET)
	public String venueInfoPage(ModelMap model){
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( Common.isVenue(venueID) ){
				return CommonVenue.toVenueInfoPage();
			}
			else{
				return Common.redirectToInfoPage();
			}
		}
		else {
			return Common.redirectToLoginPage();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/GetVenueInfo", method = RequestMethod.POST)
	public VenueInfo getVenueInfo(){
		VenueInfo venueInfo = new VenueInfo();
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( Common.isVenue(venueID) ){
				venueInfo = venueInfoService.getVenueInfo(venueID);
			}
		}
		return venueInfo;
	}
	
	@RequestMapping(value = "/VenueInfoIsChanging", method = RequestMethod.POST)
	public Result venueInfoIsChanging(){
		HttpSession session = request.getSession(false);
		Result result = new Result();
		if( Common.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( Common.isVenue(venueID) ){
			
			}
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ChangeVenueInfo", method = RequestMethod.POST)
	public Result changeVenueInfo(@ModelAttribute("venueInfo") VenueInfoChange venueInfo){
		Result result = new Result();
		result.setResult(false);
		String message = "";
		HttpSession session = request.getSession(false);
		String venueID = (String)session.getAttribute(ParaName.VerificationCode);
		if( Common.hasLogin(session) && Common.isVenue(venueID) ){
			if( venueInfoService.preChangeVenueInfo(venueInfo) ){
				message = "修改信息需要审核，请耐心等待！";
				result.setResult(true);
				result.setMessage(message);
			}
			else {
				message = "提交审核失败，请重新尝试！";
				result.setMessage(message);
			}
		}
		return result;
	}
}
