package tickets.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tickets.service.user.UserInfoService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserOrderCon {
	
	@Resource(name = "userInfoService" )
	private UserInfoService userInfoService;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value = "/OrderMain")
	public String OrderMainPage(){
		return "user/Order";
	}
}
