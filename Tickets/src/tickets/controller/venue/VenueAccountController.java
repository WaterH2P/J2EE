package tickets.controller.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.controller.Common;
import tickets.model.Result;
import tickets.model.VenueInfo;
import tickets.service.venue.VenueAccountService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class VenueAccountController {
	
	@Resource(name = "venueAccountService")
	private VenueAccountService venueAccountService;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value = "/VenueRegister", method = RequestMethod.GET)
	public String VenueRegisterPage(ModelMap model){
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			return Common.redirectToInfoPage();
		}
		else {
			model.addAttribute("message", "欢迎注册!");
			return CommonVenue.toVenueRegisterPage();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/VenueRegister", method = RequestMethod.POST)
	public Result VenueRegister(@ModelAttribute("venueInfo") VenueInfo venueInfo, String password){
		System.out.println("VenueRegister Controller");
		Result result = new Result();
		String venueID = venueAccountService.preRegister(venueInfo, password);
		result.setResult(true);
		result.setMessage(venueID);
		return result;
	}
}
