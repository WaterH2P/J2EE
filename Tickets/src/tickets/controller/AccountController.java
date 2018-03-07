package tickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;
import tickets.model.UserInfo;
import tickets.service.AccountService;
import tickets.service.MailService;
import tickets.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AccountController {
	
	@Resource(name = "accountService")
	private AccountService accountService;
	
	@Resource(name = "mailService")
	private MailService mailService;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String loginPage(ModelMap model){
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			return "redirect:/UserInfo";
		}
		else {
			model.addAttribute("message", "欢迎登录!");
			return "Login";
		}
	}
	
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String login(String userEmail, String userPassword, ModelMap model){
		if( accountService.login(userEmail, userPassword) ){
			HttpSession session = request.getSession(true);
			session.setMaxInactiveInterval(600);
			session.setAttribute("email", userEmail);
			return "redirect:/UserInfo";
		}
		else {
			model.addAttribute("message", "登录失败，请重新登录!");
			return "Login";
		}
	}
	
	@RequestMapping(value = "/Logout", method = RequestMethod.POST)
	public String logout(){
		HttpSession session = request.getSession(false);
		session.removeAttribute("email");
		session.invalidate();
		return "redirect:/Login";
	}
	
	@RequestMapping(value = "/Register", method = RequestMethod.GET)
	public String registerPage(ModelMap model){
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			return "redirect:/UserInfo";
		}
		else {
			model.addAttribute("message", "欢迎注册!");
			return "Register";
		}
	}
	
	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public String register(String userEmail, String userPassword, String userName, ModelMap model){
		if( accountService.register(userEmail, userPassword, userName) ){
			model.addAttribute("message", "注册成功，请登录!");
			return "Login";
		}
		else {
			model.addAttribute("message", "注册邮箱已存在!");
			return "Register";
		}
	}
	
	@RequestMapping(value = "/getVerificationCode", produces = {"text/html;charset=UTF-8;"} )
	@ResponseBody
	public String createCode(String userEmail){
		String result = "";
		try{
			String string = userEmail + Calendar.getInstance();
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			String newstr = base64en.encode( md5.digest(string.getBytes("utf-8")) );
			String code = "";
			final int codeLength = 5;
			for(int i=0; i<codeLength; i++ ){
				int index = (int)(Math.random()*newstr.length());
				code += newstr.charAt( index );
			}
			String subject = "Verification";
			String content = "Hello, your Verification Code is : " + code;
			System.out.println( code );
			if( mailService.sendMail(userEmail, subject, content) ){
				result = "{result:'true'}";
			}
			else {
				result = "{result:'false'}";
			}
		}catch( NoSuchAlgorithmException e ){
			e.printStackTrace();
		}catch( UnsupportedEncodingException e ){
			e.printStackTrace();
		}
		return result;
	}
}
