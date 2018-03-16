package tickets.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.controller.CommonCon;
import tickets.daoImpl.ParaName;
import tickets.model.venue.VenuePlan;
import tickets.service.user.UserTicketService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserTicketCon {
	
	@Autowired
	HttpServletRequest request;
	
	@Resource(name = "userTicketService")
	private UserTicketService userTicketService;
	
	@RequestMapping(value = "/User/UserBuyTicket", method = RequestMethod.GET)
	public String userBuyTicket(){
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				return CommonUser.toUserBuyTicketOnlinePage();
			}
			else{
				return CommonCon.redirectToInfoPage();
			}
		}
		else {
			return CommonCon.redirectToLoginPage();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/User/SearchVenuePlanByPlanName", method = RequestMethod.POST)
	public List<VenuePlan> searchVenuePlanByPlanName(String planName){
		List<VenuePlan> venuePlans = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				venuePlans = userTicketService.searchPlanByPlanName(planName);
			}
		}
		return venuePlans;
	}
	
}
