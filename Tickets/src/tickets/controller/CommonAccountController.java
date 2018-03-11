package tickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tickets.controller.mgr.CommonMgr;
import tickets.controller.user.CommonUser;
import tickets.controller.venue.CommonVenue;
import tickets.daoImpl.ParaName;
import tickets.model.UserInfo;
import tickets.model.VenueInfo;
import tickets.service.user.UserInfoService;
import tickets.service.venue.VenueInfoService;
import tickets.serviceImpl.mgr.MgrAccountServiceImpl;
import tickets.serviceImpl.user.UserAccountServiceImpl;
import tickets.serviceImpl.venue.VenueAccountServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CommonAccountController {
	
	@Resource(name = "userAccountService" )
	private UserAccountServiceImpl userAccountService;
	
	@Resource(name = "userInfoService" )
	private UserInfoService userInfoService;
	
	@Resource(name = "venueAccountService" )
	private VenueAccountServiceImpl venueAccountService;
	
	@Resource(name = "venueInfoService")
	private VenueInfoService venueInfoService;
	
	@Resource(name = "mgrAccountService" )
	private MgrAccountServiceImpl mgrAccountService;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value = "/*", method = RequestMethod.GET)
	public String wrongURLRedirectToLoginPage(){
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			return Common.redirectToInfoPage();
		}
		else {
			return Common.redirectToLoginPage();
		}
	}
	
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String loginPage(ModelMap model){
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			return Common.redirectToInfoPage();
		}
		else {
			model.addAttribute("message", "欢迎登录!");
			return Common.toLoginPage();
		}
	}
	
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String login(String EmailORID, String password, ModelMap model){
		String result = "";
		if( EmailORID.length()==7 && Common.regCheck(ParaName.venueIDRegex, EmailORID) ){
			System.out.println("场馆登录");
			if( venueAccountService.login(EmailORID, password) ){
				Common.createSession(request, EmailORID);
				VenueInfo venueInfo = venueInfoService.getVenueInfo(EmailORID);
				model.addAttribute("venueInfo", venueInfo);
				result = CommonVenue.redirectToVenueInfoPage();
			}
		}
		else if( Common.regCheck(ParaName.emailRegex, EmailORID)){
			System.out.println("邮箱登录");
			if( userAccountService.login(EmailORID, password) ){
				Common.createSession(request, EmailORID);
				UserInfo userInfo = userInfoService.getUserInfo(EmailORID);
				model.addAttribute("userInfo", userInfo);
				result = CommonUser.redirectToUserInfoPage();
			}
		}
		else {
			System.out.println("管理员登录");
			if( mgrAccountService.login(EmailORID, password) ){
				Common.createSession(request, EmailORID);
				result = CommonMgr.redirectToMgrInfoPage();
			}
		}
		if( result.length()==0 ){
			model.addAttribute("message", "帐号或密码失败，请重新登录!");
			result = Common.toLoginPage();
		}
		return result;
	}
	
	@RequestMapping(value = "/Logout", method = RequestMethod.POST)
	public String logout(){
		HttpSession session = request.getSession(false);
		session.removeAttribute(ParaName.VerificationCode);
		session.invalidate();
		return Common.redirectToLoginPage();
	}
	
	@RequestMapping(value = "/RedirectByVerificationCode")
	public String redirectByVerificationCode(ModelMap model){
		HttpSession session = request.getSession(false);
		String EmailORID = (String)session.getAttribute( ParaName.VerificationCode );
		String result = "";
		if( EmailORID.length()==7 && Common.regCheck(ParaName.venueIDRegex, EmailORID) ){
			System.out.println("场馆登录");
			result = CommonVenue.redirectToVenueInfoPage();
		}
		else if( Common.regCheck(ParaName.emailRegex, EmailORID)){
			System.out.println("邮箱登录");
			result = CommonUser.redirectToUserInfoPage();
		}
		else {
			System.out.println("管理员登录");
			result = CommonMgr.redirectToMgrInfoPage();
		}
		return result;
	}
}
