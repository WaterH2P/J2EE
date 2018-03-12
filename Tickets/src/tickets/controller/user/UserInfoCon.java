package tickets.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.controller.Common;
import tickets.daoImpl.ParaName;
import tickets.model.UserInfo;
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
	
	@RequestMapping(value = "/UserInfo", method = RequestMethod.GET)
	public String userInfo(ModelMap model){
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( Common.isUser(email) ){
				return CommonUser.toUserInfoPage();
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
	@RequestMapping(value = "/GetUserInfo", method = RequestMethod.POST)
	public UserInfo getUserInfo(){
		UserInfo userInfo = new UserInfo();
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			String email = (String) session.getAttribute(ParaName.VerificationCode);
			if( Common.isUser(email) ){
				userInfo = userInfoService.getUserInfo(email);
			}
		}
		return userInfo;
	}
	
	@RequestMapping(value = "/ChangeUserInfo", method = RequestMethod.POST)
	public void changeUserInfo(String userEmail, String userName){
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			String email = (String) session.getAttribute(ParaName.VerificationCode);
			if( email.equals(userEmail) ){
				userInfoService.changeUserName(email, userName);
			}
		}
	}
	
}
