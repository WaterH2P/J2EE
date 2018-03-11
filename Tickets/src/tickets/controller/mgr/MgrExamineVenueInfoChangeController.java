package tickets.controller.mgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.controller.Common;
import tickets.daoImpl.ParaName;
import tickets.model.Result;
import tickets.model.VenueInfoRedundancy;
import tickets.service.mgr.MgrExamineVenueInfoChangeService;
import tickets.service.venue.VenueInfoService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MgrExamineVenueInfoChangeController {
	
	@Autowired
	private HttpServletRequest request;
	
	@Resource(name = "venueInfoService")
	private VenueInfoService venueInfoService;
	
	@Resource(name = "mgrExamineVenueInfoChangeService")
	private MgrExamineVenueInfoChangeService mgrExamineVenueInfoChangeService;

	@RequestMapping(value = "/MgrExamineVenueInfoChange", method = RequestMethod.GET)
	public String mgrExamineVenueInfoChangePage(){
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			String mgrID = (String)session.getAttribute(ParaName.VerificationCode);
			if( Common.isMgr(mgrID) ){
				return CommonMgr.toMgrExamineVenueInfoChangePage();
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
	@RequestMapping(value = "/GetAllVenueInfoRedundancies", method = RequestMethod.POST)
	public List<VenueInfoRedundancy> getAllVenueInfoRedundancies(){
		HttpSession session = request.getSession(false);
		List<VenueInfoRedundancy> venueInfoRedundancies = null;
		if( Common.hasLogin(session) ){
			String mgrID = (String)session.getAttribute(ParaName.VerificationCode);
			if( Common.isMgr(mgrID) ){
				venueInfoRedundancies = venueInfoService.getAllVenueInfoRedundancies();
			}
		}
		return venueInfoRedundancies;
	}
	
	@ResponseBody
	@RequestMapping(value = "/AgreeWithVenueInfoChange", method = RequestMethod.POST)
	public Result agreeWithVenueRegister(String venueID){
		Result result = new Result();
		result.setResult(false);
		
		HttpSession session = request.getSession(false);
		if(mgrExamineVenueInfoChangeService.agreeWithVenueInfoChange(venueID)){
			result.setResult(true);
		}
		return result;
	}

}
