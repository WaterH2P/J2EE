package tickets.controller.mgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.controller.CommonCon;
import tickets.daoImpl.ParaName;
import tickets.model.Result;
import tickets.model.venue.VenueBaseInfo;
import tickets.service.mgr.MgrExamineVenueRegisterService;
import tickets.service.venue.VenueBaseInfoService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MgrExamineVenueRegisterCon {
	
	@Autowired
	private HttpServletRequest request;
	
	@Resource(name = "venueBaseInfoService" )
	private VenueBaseInfoService venueBaseInfoService;
	
	@Resource(name = "mgrExamineVenueRegisterService" )
	private MgrExamineVenueRegisterService mgrExamineVenueRegisterService;
	
	@RequestMapping(value = "/Mgr/MgrExamineVenueRegister", method = RequestMethod.GET)
	public String mgrExamineVenueRegisterPage(){
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String mgrID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isMgr(mgrID) ){
				return ParaNameMgr.toMgrExamineVenueRegisterPage();
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
	@RequestMapping(value = "/Mgr/GetAllUnconfirmedVenues", method = RequestMethod.POST)
	public List<VenueBaseInfo> getAllUnconfirmedVenues(){
		List<VenueBaseInfo> venueRegisters = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String mgrID = (String) session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isMgr(mgrID) ){
				venueRegisters = venueBaseInfoService.getAllUnconfirmedVenueInfos();
			}
		}
		return venueRegisters;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Mgr/AgreeWithVenueRegister", method = RequestMethod.POST)
	public Result agreeWithVenueRegister(String venueID){
		Result result = new Result();
		result.setResult(false);
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String mgrID = (String) session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isMgr(mgrID) ){
				if( mgrExamineVenueRegisterService.agreeWithVenueRegister(venueID) ){
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
