package tickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.service.UserAccountService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserAccountController {
	
	@Resource(name = "userAccountService" )
	private UserAccountService userAccountService;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value = "/UserRegister", method = RequestMethod.GET)
	public String registerPage(ModelMap model){
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			return CommonUser.redirectToUserInfoPage();
		}
		else {
			model.addAttribute("message", "欢迎注册!");
			return CommonUser.toUserRegister();
		}
	}
	
	@RequestMapping(value = "/getVerificationCode", method = RequestMethod.POST,
			produces = "text/html; charset=utf-8")
	public @ResponseBody String createCode(String userEmail, String userName){
		String result = "";
		String message = "";
		if( userAccountService.preRegister(userEmail, userName) ){
			message = "验证码已发送";
			result = "{\"result\":true, \"message\":\"" + message + "\"}";
		}
		else {
			message = "此邮箱已注册，请直接登录";
			result = "{\"result\":false, \"message\":\"" + message + "\"}";
		}
		return result;
	}
	
	@RequestMapping(value = "/UserRegister", method = RequestMethod.POST,
			produces = "text/html; charset=utf-8")
	public @ResponseBody String register(String userEmail, String userPassword, String verificationCode){
		if( userAccountService.register(userEmail, userPassword, verificationCode) ){
			String result = "{\"result\":true}";
			return result;
		}
		else {
			String message = "验证码错误！";
			String result = "{\"result\":false, \"message\":\"" + message + "\"}";
			return result;
		}
	}
}
