package tickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tickets.dao.ParaName;
import tickets.service.AccountService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AccountController {
	
	@Resource(name = "accountService" )
	private AccountService accountService;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String loginPage(ModelMap model){
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			return CommonUser.redirectToUserInfoPage();
		}
		else {
			model.addAttribute("message", "欢迎登录!");
			return CommonUser.toLoginPage();
		}
	}
	
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String login(String EmailORVenueID, String userPassword, ModelMap model){
		if( accountService.login(EmailORVenueID, userPassword) ){
			HttpSession session = request.getSession(true);
			session.setMaxInactiveInterval(600);
			session.setAttribute(ParaName.VerificationCode, EmailORVenueID);
			return CommonUser.redirectToUserInfoPage();
		}
		else {
			model.addAttribute("message", "登录失败，请重新登录!");
			return CommonUser.toLoginPage();
		}
	}
	
	@RequestMapping(value = "/Logout", method = RequestMethod.POST)
	public String logout(){
		HttpSession session = request.getSession(false);
		session.removeAttribute(ParaName.VerificationCode);
		session.invalidate();
		return CommonUser.redirectToLoginPage();
	}
	
}
