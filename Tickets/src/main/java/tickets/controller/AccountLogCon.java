package tickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.controller.mgr.ParaNameMgr;
import tickets.controller.user.ParaNameUser;
import tickets.controller.venue.ParaNameVenue;
import tickets.daoImpl.ParaName;
import tickets.exception.AccountAccessException;
import tickets.model.Result;
import tickets.service.user.UserInfoService;
import tickets.service.venue.VenueBaseInfoService;
import tickets.serviceImpl.mgr.MgrAccountServiceImpl;
import tickets.serviceImpl.user.UserAccountServiceImpl;
import tickets.serviceImpl.venue.VenueAccountServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AccountLogCon {
	
	@Resource(name = "userAccountService" )
	private UserAccountServiceImpl userAccountService;
	
	@Resource(name = "userInfoService" )
	private UserInfoService userInfoService;
	
	@Resource(name = "venueAccountService" )
	private VenueAccountServiceImpl venueAccountService;
	
	@Resource(name = "venueBaseInfoService" )
	private VenueBaseInfoService venueBaseInfoService;
	
	@Resource(name = "mgrAccountService" )
	private MgrAccountServiceImpl mgrAccountService;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value = "/*", method = RequestMethod.GET)
	public String wrongURLRedirectToLoginPage1(){
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			return CommonCon.redirectToInfoPage();
		}
		else {
			return CommonCon.redirectToLoginPage();
		}
	}
	
	@RequestMapping(value = "/*/*", method = RequestMethod.GET)
	public String wrongURLRedirectToLoginPage2(){
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			return CommonCon.redirectToInfoPage();
		}
		else {
			return CommonCon.redirectToLoginPage();
		}
	}
	
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String loginPage(){
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			return CommonCon.redirectToInfoPage();
		}
		else {
			return CommonCon.toLoginPage();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public Result login(String EmailORID, String password, ModelMap model){
		Result result = new Result(false);
		String message = "";
		try{
			if( EmailORID.length()==7 && CommonCon.regCheck(ParaName.venueIDRegex, EmailORID) ){
				System.out.println("场馆登录");
				if( venueAccountService.login(EmailORID, password) ){
					CommonCon.createSession(request, EmailORID);
					result.setResult(true);
				}
			}
			else if( CommonCon.regCheck(ParaName.emailRegex, EmailORID)){
				System.out.println("邮箱登录");
				if( userAccountService.login(EmailORID, password) ){
					CommonCon.createSession(request, EmailORID);
					result.setResult(true);
				}
			}
			else {
				System.out.println("管理员登录");
				if( mgrAccountService.login(EmailORID, password) ){
					CommonCon.createSession(request, EmailORID);
					result.setResult(true);
				}
			}
		}catch( AccountAccessException e ){
			message = e.getMessage();
		}
		if( !result.getResult() ){
			result.setMessage(message);
		}
		return result;
	}
	
	@RequestMapping(value = "/Logout", method = RequestMethod.POST)
	public String logout(){
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			session.removeAttribute(ParaName.VerificationCode);
			session.invalidate();
		}
		return CommonCon.redirectToLoginPage();
	}
	
	@RequestMapping(value = "/RedirectByVerificationCode")
	public String redirectByVerificationCode(ModelMap model){
		HttpSession session = request.getSession(false);
		String EmailORID = (String)session.getAttribute( ParaName.VerificationCode );
		String result = "";
		if( EmailORID.length()==7 && CommonCon.regCheck(ParaName.venueIDRegex, EmailORID) ){
			System.out.println("场馆登录");
			result = ParaNameVenue.redirectToVenueBaseInfoPage();
		}
		else if( CommonCon.regCheck(ParaName.emailRegex, EmailORID)){
			System.out.println("邮箱登录");
			result = ParaNameUser.redirectToUserInfoPage();
		}
		else {
			System.out.println("管理员登录");
			result = ParaNameMgr.redirectToMgrInfoPage();
		}
		return result;
	}
}
