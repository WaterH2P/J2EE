package tickets.controller.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tickets.controller.CommonCon;
import tickets.daoImpl.ParaName;
import tickets.model.Result;
import tickets.model.VenueHall;
import tickets.service.venue.VenueHallService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class VenueHallManageCon {
	
	@Autowired
	private HttpServletRequest request;
	
	@Resource(name = "venueHallService")
	private VenueHallService venueHallService;
	
	@RequestMapping(value = "/VenueHallPage", method = RequestMethod.GET)
	public String VenueHallManagePage(){
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				return CommonVenue.toVenueHallManagePage();
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
	@RequestMapping(value = "/VenueAddNewHall", method = RequestMethod.POST)
	public Result venueAddNewHall(@ModelAttribute("venueHall")VenueHall venueHall, String seatData, String seatLevel){
		Result result = new Result();
		result.setResult(false);
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String venueID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isVenue(venueID) ){
				String seatDist = unifyStr(seatData);
				seatDist = oneZeroToHexadecimal(seatDist);
				venueHall.setVenueID(venueID);
				venueHall.setSeatDist(seatDist);
				result.setResult( venueHallService.addNewHall(venueHall, seatLevel) );
			}
			
		}
		if( !result.getResult() ){
			String message = "新添加场厅失败，请重新尝试！";
			result.setMessage(message);
		}
		return result;
	}
	
	public static String unifyStr(String str){
		Pattern pattern1 = Pattern.compile("[^_]");
		Matcher matcher1 = pattern1.matcher(str);
		String result = matcher1.replaceAll("1");
		Pattern pattern2 = Pattern.compile("[_]");
		Matcher matcher2 = pattern2.matcher(result);
		result = matcher2.replaceAll("0");
		return result;
	}
	
	public static String unifyStrBack(String str){
		Pattern pattern1 = Pattern.compile("[1]");
		Matcher matcher1 = pattern1.matcher(str);
		String result = matcher1.replaceAll("a");
		Pattern pattern2 = Pattern.compile("[0]");
		Matcher matcher2 = pattern2.matcher(result);
		result = matcher2.replaceAll("_");
		return result;
	}
	
	public static String oneZeroToHexadecimal(String str){
		Map<String, String> exchange = new HashMap<>();
		exchange.put("0000","0");
		exchange.put("0001","1");
		exchange.put("0010","2");
		exchange.put("0011","3");
		exchange.put("0100","4");
		exchange.put("0101","5");
		exchange.put("0110","6");
		exchange.put("0111","7");
		exchange.put("1000","8");
		exchange.put("1001","9");
		exchange.put("1010","A");
		exchange.put("1011","B");
		exchange.put("1100","C");
		exchange.put("1101","D");
		exchange.put("1110","E");
		exchange.put("1111","F");
		while( str.length()%4!=0 ){
			str += "0";
		}
		String result = "";
		for( int i=0; i<str.length(); i=i+4 ){
			result += exchange.get(str.substring(i, i+4));
		}
		return result;
	}
	
	public static String hexadecimalToOneZero(String str){
		Map<String, String> exchange = new HashMap<>();
		exchange.put("0", "0000");
		exchange.put("1", "0001");
		exchange.put("2", "0010");
		exchange.put("3", "0011");
		exchange.put("4", "0100");
		exchange.put("5", "0101");
		exchange.put("6", "0110");
		exchange.put("7", "0111");
		exchange.put("8", "1000");
		exchange.put("9", "1001");
		exchange.put("A", "1010");
		exchange.put("B", "1011");
		exchange.put("C", "1100");
		exchange.put("D", "1101");
		exchange.put("E", "1110");
		exchange.put("F", "1111");
		
		String result = "";
		for( int i=0; i<str.length(); i++ ){
			result += exchange.get(str.substring(i, i+1));
		}
		return result;
	}
}
