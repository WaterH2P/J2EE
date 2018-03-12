package tickets.controller.mgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tickets.controller.Common;
import tickets.controller.venue.CommonVenue;
import tickets.daoImpl.ParaName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MgrInfoCon {
	
	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "/MgrInfo", method = RequestMethod.GET)
	public String mgrInfoPage(){
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			String mgrID = (String)session.getAttribute(ParaName.VerificationCode);
			if( Common.isMgr(mgrID) ){
				return CommonMgr.toMgrInfoPage();
			}
			else{
				return Common.redirectToInfoPage();
			}
		}
		else {
			return Common.redirectToLoginPage();
		}
	}
}
