package tickets.controller.mgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.controller.Common;
import tickets.daoImpl.ParaName;
import tickets.model.Result;
import tickets.model.VenueBaseInfo;
import tickets.service.mgr.MgrExamineVenueRegisterService;
import tickets.service.venue.VenueBaseInfoService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MgrExamineVenueRegisterCon {
	
	@Autowired
	private HttpServletRequest request;
	
	@Resource(name = "venueBaseInfoService" )
	private VenueBaseInfoService venueBaseInfoService;
	
	@Resource(name = "mgrExamineVenueRegisterService" )
	private MgrExamineVenueRegisterService mgrExamineVenueRegisterService;
	
	@RequestMapping(value = "/MgrExamineVenueRegister", method = RequestMethod.GET)
	public String mgrExamineVenueRegisterPage(){
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			String mgrID = (String)session.getAttribute(ParaName.VerificationCode);
			if( Common.isMgr(mgrID) ){
				return CommonMgr.toMgrExamineVenueRegisterPage();
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
	@RequestMapping(value = "/GetAllUnconfirmedVenues", method = RequestMethod.POST)
	public List<VenueBaseInfo> getAllUnconfirmedVenues(){
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			String mgrID = (String) session.getAttribute(ParaName.VerificationCode);
			if( Common.isMgr(mgrID) ){
				List<VenueBaseInfo> venueRegisters = venueBaseInfoService.getAllUnconfirmedVenueInfos();
				return venueRegisters;
			}
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/AgreeWithVenueRegister", method = RequestMethod.POST)
	public Result agreeWithVenueRegister(String venueID){
		Result result = new Result();
		result.setResult(false);
		
		HttpSession session = request.getSession(false);
		if( mgrExamineVenueRegisterService.agreeWithVenueRegister(venueID) ){
			result.setResult(true);
		}
		return result;
	}
}
