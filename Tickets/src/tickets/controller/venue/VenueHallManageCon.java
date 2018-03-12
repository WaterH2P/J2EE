package tickets.controller.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tickets.controller.Common;
import tickets.daoImpl.ParaName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class VenueHallManageCon {
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value = "/VenueHallPage", method = RequestMethod.GET)
	public String VenueHallManagePage(){
		HttpSession session = request.getSession(false);
		if( Common.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( Common.isVenue(venueID) ){
				return CommonVenue.toVenueHallManagePage();
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
