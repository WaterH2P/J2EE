package tickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping(value = "/UserInfo")
	public String userInfo(ModelMap model){
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			String email = (String) session.getAttribute("email");
			UserInfo userInfo = userService.getUserInfo(email);
			model.addAttribute("userInfo", userInfo);
			return "UserInfo";
		}
		else {
			return "redirect:/Login";
		}
	}
	
}
