package tickets.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.controller.CommonCon;
import tickets.daoImpl.ParaName;
import tickets.model.Result;
import tickets.model.mgr.VIPLevelInfo;
import tickets.model.user.UserInfo;
import tickets.service.user.UserInfoService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserInfoCon {
	
	@Resource(name = "userInfoService" )
	private UserInfoService userInfoService;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value = "/User/UserInfo", method = RequestMethod.GET)
	public String userInfo(){
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				return ParaNameUser.toUserInfoPage();
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
	@RequestMapping(value = "/User/GetUserInfo", method = RequestMethod.POST)
	public UserInfo getUserInfo(){
		UserInfo userInfo = new UserInfo();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String) session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				userInfo = userInfoService.getUserInfo(email);
			}
		}
		return userInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/User/ChangeUserInfo", method = RequestMethod.POST)
	public Result changeUserInfo(String userEmail, String userName){
		Result result = new Result();
		result.setResult(false);
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String) session.getAttribute(ParaName.VerificationCode);
			if( email.equals(userEmail) ){
				System.out.println( email + " change name to " + userName);
				userInfoService.changeUserName(email, userName);
				result.setResult(true);
			}
		}
		if( !result.getResult() && result.getMessage().length()==0 ){
			result.setMessage(ParaName.message_ownNoAuthority);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/User/GetUserVIPDiscount", method = RequestMethod.POST)
	public VIPLevelInfo getUserVIPDiscount(){
		VIPLevelInfo vipLevelInfo = new VIPLevelInfo();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String) session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				vipLevelInfo = userInfoService.getUserVIPDiscount(email);
			}
		}
		return vipLevelInfo;
	}
	
}
