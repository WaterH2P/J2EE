package tickets.controller.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.controller.CommonCon;
import tickets.daoImpl.ParaName;
import tickets.model.Result;
import tickets.model.venue.VenueBaseInfo;
import tickets.model.venue.VenueBaseInfoChange;
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
	
	@RequestMapping(value = "/Venue/VenueBaseInfo", method = RequestMethod.GET)
	public String venueInfoPage(ModelMap model){
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				return CommonVenue.toVenueBaseInfoPage();
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
	@RequestMapping(value = "/Venue/GetVenueBaseInfo", method = RequestMethod.POST)
	public VenueBaseInfo getVenueInfo(){
		VenueBaseInfo venueBaseInfo = new VenueBaseInfo();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				venueBaseInfo = venueBaseInfoService.getVenueInfo(venueID);
			}
		}
		return venueBaseInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Venue/ChangeVenueBaseInfo", method = RequestMethod.POST)
	public Result changeVenueInfo(@ModelAttribute("venueInfo") VenueBaseInfoChange venueInfo){
		Result result = new Result();
		result.setResult(false);
		String message = "";
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				if( venueBaseInfoService.preChangeVenueInfo(venueInfo) ){
					message = "修改信息需要审核，请耐心等待！";
					result.setResult(true);
					result.setMessage(message);
				} else{
					message = "提交审核失败，请重新尝试！";
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
