package tickets.controller.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tickets.controller.CommonCon;
import tickets.daoImpl.ParaName;
import tickets.model.Result;
import tickets.model.venue.VenueHall;
import tickets.service.venue.VenueHallService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class VenueHallManageCon {
	
	@Autowired
	private HttpServletRequest request;
	
	@Resource(name = "venueHallService")
	private VenueHallService venueHallService;
	
	@RequestMapping(value = "/Venue/VenueHallPage", method = RequestMethod.GET)
	public String VenueHallManagePage(){
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				return ParaNameVenue.toVenueHallManagePage();
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
	@RequestMapping(value = "/Venue/AddVenueNewHall", method = RequestMethod.POST)
	public VenueHall venueAddNewHall(@ModelAttribute("venueHall")VenueHall venueHall, String seatData, String seatLevel){
		VenueHall venueHall1 = new VenueHall();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				venueHall.setVenueID(venueID);
				venueHall = venueHallService.addNewVenueHall(venueHall, seatData, seatLevel);
			}
		}
		return venueHall;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Venue/GetAllVenueHalls", method = RequestMethod.POST)
	public List<VenueHall> getAllVenueHalls(){
		List<VenueHall> venueHalls = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				venueHalls = venueHallService.getAllVenueHalls(venueID);
			}
		}
		return venueHalls;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Venue/DeleteVenueHall", method = RequestMethod.POST)
	public Result deleteVenueHall(String hallID){
		Result result = new Result();
		result.setResult(false);
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				venueHallService.deleteVenueHall(hallID);
				result.setResult(true);
			}
		}
		if( !result.getResult() && result.getMessage().length()==0 ){
			result.setMessage(ParaName.message_ownNoAuthority);
		}
		return result;
	}
	
}
