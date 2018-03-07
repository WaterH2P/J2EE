package tickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tickets.dao.ParaName;
import tickets.model.UserInfo;
import tickets.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserInfoController {
	
	@Resource(name = "userService")
	private UserService userService;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value = "/UserInfo", method = RequestMethod.GET)
	public String userInfo(ModelMap model){
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			String email = (String) session.getAttribute(ParaName.VerificationCode);
			UserInfo userInfo = userService.getUserInfo(email);
			model.addAttribute("userInfo", userInfo);
			return "user/UserInfo";
		}
		else {
			return "redirect:/Login";
		}
	}
	
	@RequestMapping(value = "/UserInfo", method = RequestMethod.POST)
	public void changeUserName(String userEmail, String userName){
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			String email = (String) session.getAttribute(ParaName.VerificationCode);
			if( email.equals(userEmail) ){
				userService.changeUserName(email, userName);
			}
		}
	}
}
