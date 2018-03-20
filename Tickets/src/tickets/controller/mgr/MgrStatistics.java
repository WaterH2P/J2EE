package tickets.controller.mgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tickets.controller.CommonCon;
import tickets.daoImpl.ParaName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MgrStatistics {
	
	@Autowired
	HttpServletRequest request;
	
	@RequestMapping(value = "/Mgr/MgrStatistics", method = RequestMethod.GET)
	public String mgrStatistics(){
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String mgrID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isMgr(mgrID) ){
				return CommonMgr.toMgrStatisticsPage();
			}
			else{
				return CommonCon.redirectToInfoPage();
			}
		}
		else {
			return CommonCon.redirectToLoginPage();
		}
	}
	
}
