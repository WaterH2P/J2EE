package tickets.controller.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.controller.CommonCon;
import tickets.daoImpl.ParaName;
import tickets.model.Result;
import tickets.model.venue.VenueHall;
import tickets.model.venue.VenueSeatLevel;
import tickets.service.venue.VenueHallService;
import tickets.service.venue.VenueSeatLevelService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class VenueSeatLevelManageCon {
	
	@Autowired
	private HttpServletRequest request;
	
	@Resource(name = "venueHallService")
	private VenueHallService venueHallService;
	
	@Resource(name = "venueSeatLevelService")
	private VenueSeatLevelService venueSeatLevelService;
	
	@RequestMapping(value = "/Venue/VenueSeatLevelManage", method = RequestMethod.GET)
	public String venueSeatLevelManage(){
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				return ParaNameVenue.toVenueSeatLevelManagePage();
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
	@RequestMapping(value = "/Venue/GetAllSeatLevels", method = RequestMethod.POST)
	public List<VenueSeatLevel> getAllSeatLevels(){
		List<VenueSeatLevel> venueSeatLevels = null;
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				venueSeatLevels = venueSeatLevelService.getAllSeatLevels(venueID);
			}
		}
		return venueSeatLevels;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Venue/GetAllVenueHall", method = RequestMethod.POST)
	public List<VenueHall> getAllVenueHall(){
		List<VenueHall> venueHalls = null;
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
	@RequestMapping(value = "/Venue/AddNewSeatLevel", method = RequestMethod.POST)
	public Result addNewSeatLevel(String name, String percent){
		Result result = new Result();
		result.setResult(false);
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				VenueSeatLevel venueSeatLevel = new VenueSeatLevel();
				venueSeatLevel.setVenueID(venueID);
				venueSeatLevel.setName(name);
				venueSeatLevel.setPercent(Integer.valueOf(percent));
				String seatID = venueSeatLevelService.addSeatLevel( venueSeatLevel );
				if( seatID.length()>0 ){
					result.setResult(true);
					result.setMessage(venueID + "-" + seatID);
					System.out.println( venueID + " add new seat : " + seatID);
				}
			}
		}
		if( !result.getResult() && result.getMessage().length()==0 ){
			result.setMessage(ParaName.message_ownNoAuthority);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Venue/DeleteSeatLevel", method = RequestMethod.POST)
	public Result deleteSeatLevel(String venueID, String seatID){
		Result result = new Result();
		result.setResult(false);
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String VerificationCode = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) && VerificationCode.equals(venueID) ){
				venueSeatLevelService.deleteSeatLevel(seatID);
				result.setResult(true);
			}
		}
		if( !result.getResult() && result.getMessage().length()==0 ){
			result.setMessage(ParaName.message_ownNoAuthority);
		}
		return result;
	}
	
}
