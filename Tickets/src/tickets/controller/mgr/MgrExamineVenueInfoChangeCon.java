package tickets.controller.mgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.controller.CommonCon;
import tickets.daoImpl.ParaName;
import tickets.model.Result;
import tickets.model.venue.VenueBaseInfoRedundancy;
import tickets.service.mgr.MgrExamineVenueInfoChangeService;
import tickets.service.venue.VenueBaseInfoService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MgrExamineVenueInfoChangeCon {
	
	@Autowired
	private HttpServletRequest request;
	
	@Resource(name = "venueBaseInfoService" )
	private VenueBaseInfoService venueBaseInfoService;
	
	@Resource(name = "mgrExamineVenueInfoChangeService")
	private MgrExamineVenueInfoChangeService mgrExamineVenueInfoChangeService;

	@RequestMapping(value = "/Mgr/MgrExamineVenueInfoChange", method = RequestMethod.GET)
	public String mgrExamineVenueInfoChangePage(){
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String mgrID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isMgr(mgrID) ){
				return CommonMgr.toMgrExamineVenueInfoChangePage();
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
	@RequestMapping(value = "/Mgr/GetAllVenueInfoRedundancies", method = RequestMethod.POST)
	public List<VenueBaseInfoRedundancy> getAllVenueInfoRedundancies(){
		HttpSession session = request.getSession(false);
		List<VenueBaseInfoRedundancy> venueInfoRedundancies = null;
		if( CommonCon.hasLogin(session) ){
			String mgrID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isMgr(mgrID) ){
				venueInfoRedundancies = venueBaseInfoService.getAllVenueInfoRedundancies();
			}
		}
		return venueInfoRedundancies;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Mgr/AgreeWithVenueInfoChange", method = RequestMethod.POST)
	public Result agreeWithVenueRegister(String venueID){
		Result result = new Result();
		result.setResult(false);
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String mgrID = (String) session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isMgr(mgrID) ){
				if( mgrExamineVenueInfoChangeService.agreeWithVenueInfoChange(venueID) ){
					result.setResult(true);
				}
			}
		}
		if( !result.getResult() && result.getMessage().length()==0 ){
			result.setMessage(ParaName.message_ownNoAuthority);
		}
		return result;
	}

}
