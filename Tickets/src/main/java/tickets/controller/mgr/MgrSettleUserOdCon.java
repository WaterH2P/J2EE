package tickets.controller.mgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.controller.CommonCon;
import tickets.daoImpl.ParaName;
import tickets.model.Result;
import tickets.model.user.UserOd;
import tickets.service.user.UserOdService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MgrSettleUserOdCon {
	
	@Autowired
	private HttpServletRequest request;
	
	@Resource(name = "userOdService")
	private UserOdService userOdService;
	
	@RequestMapping(value = "/Mgr/MgrSettleUserOd", method = RequestMethod.GET)
	public String mgrSettleUserOd(){
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String mgrID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isMgr(mgrID) ){
				return ParaNameMgr.toMgrSettleUserOdPage();
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
	@RequestMapping(value = "/Mgr/GetAllUserOdIsCheckIsNotSettled", method = RequestMethod.POST)
	public List<UserOd> getAllUserOdIsCheckIsNotSettled(){
		List<UserOd> userOds = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String mgrID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isMgr(mgrID) ){
				userOds = userOdService.getAllUserOd_IsCheck_IsNotSettled();
			}
		}
		return userOds;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Mgr/SettleUserOd", method = RequestMethod.POST)
	public Result SettleUserOd(String OdID){
		Result result = new Result();
		result.setResult(false);
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String mgrID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isMgr(mgrID) ){
				userOdService.settleUserOd(OdID);
				result.setResult(true);
			}
		}
		return result;
	}
	
}
