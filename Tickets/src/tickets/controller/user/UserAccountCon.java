package tickets.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.controller.Common;
import tickets.daoImpl.ParaName;
import tickets.model.Result;
import tickets.model.UserInfo;
import tickets.service.user.UserAccountService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserAccountCon {
	
	@Resource(name = "userAccountService" )
	private UserAccountService userAccountService;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value = "/UserRegister", method = RequestMethod.GET)
	public String registerPage(ModelMap model){
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			return Common.redirectToInfoPage();
		}
		else {
			model.addAttribute("message", "欢迎注册!");
			return CommonUser.toUserRegisterPage();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/GetVerificationCode", method = RequestMethod.POST)
	public Result createCode(String userEmail, String userName){
		Result result = new Result();
		String message = "";
		if( userAccountService.preRegister(userEmail, userName) ){
			message = "验证码已发送";
			result.setResult(true);
			result.setMessage(message);
		}
		else {
			message = "此邮箱已注册，请直接登录";
			result.setResult(false);
			result.setMessage(message);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/UserRegister", method = RequestMethod.POST)
	public Result register(String userEmail, String userPassword, String verificationCode){
		Result result = new Result();
		if( userAccountService.register(userEmail, userPassword, verificationCode) ){
			result.setResult(true);
		}
		else {
			String message = "验证码错误！";
			result.setResult(false);
			result.setMessage(message);
		}
		return result;
	}
	
	@RequestMapping(value = "/CancelAccountVIP", method = RequestMethod.POST)
	public String cancelAccountVIP(String userEmail){
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( Common.isUser(email) && email.equals(userEmail) ){
				System.out.println(email + " cancel VIP Controller");
				userAccountService.cancelAccountVIP(userEmail);
				return Common.redirectToLoginPage();
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
