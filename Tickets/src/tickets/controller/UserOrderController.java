package tickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tickets.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserOrderController {
	
	@Resource(name = "userService")
	private UserService userService;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value = "/OrderMain")
	public String OrderMainPage(){
		return "Order";
	}
}
