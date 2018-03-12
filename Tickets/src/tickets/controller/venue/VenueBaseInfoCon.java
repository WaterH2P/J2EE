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
import tickets.model.VenueBaseInfo;
import tickets.model.VenueBaseInfoChange;
import tickets.service.venue.VenueBaseInfoService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class VenueBaseInfoCon {
	
	@Resource(name = "venueBaseInfoService" )
	private VenueBaseInfoService venueBaseInfoService;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value = "/VenueBaseInfo", method = RequestMethod.GET)
	public String venueInfoPage(ModelMap model){
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( Common.isVenue(venueID) ){
				return CommonVenue.toVenueBaseInfoPage();
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
	@RequestMapping(value = "/GetVenueBaseInfo", method = RequestMethod.POST)
	public VenueBaseInfo getVenueInfo(){
		VenueBaseInfo venueBaseInfo = new VenueBaseInfo();
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( Common.isVenue(venueID) ){
				venueBaseInfo = venueBaseInfoService.getVenueInfo(venueID);
			}
		}
		return venueBaseInfo;
	}
	
	@RequestMapping(value = "/VenueBaseInfoIsChanging", method = RequestMethod.POST)
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
	@RequestMapping(value = "/ChangeVenueBaseInfo", method = RequestMethod.POST)
	public Result changeVenueInfo(@ModelAttribute("venueInfo") VenueBaseInfoChange venueInfo){
		Result result = new Result();
		result.setResult(false);
		String message = "";
		HttpSession session = request.getSession(false);
		String venueID = (String)session.getAttribute(ParaName.VerificationCode);
		if( Common.hasLogin(session) && Common.isVenue(venueID) ){
			if( venueBaseInfoService.preChangeVenueInfo(venueInfo) ){
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
