package tickets.controller;

import tickets.daoImpl.ParaName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Common {
	
	public static String toLoginPage(){
		return "Login";
	}
	public static String redirectToLoginPage(){
		return "redirect:/Login";
	}
	
	public static String redirectToInfoPage(){
		return "forward:/RedirectByVerificationCode";
	}
	
	public static boolean hasLogin(HttpSession session){
		if( session==null ){
			return false;
		}
		else {
			if( session.getAttribute(ParaName.VerificationCode)==null ){
				session.invalidate();
				return false;
			}
			else{
				return true;
			}
		}
	}
	
	public static boolean isUser(String email){
		return Common.regCheck(ParaName.emailRegex, email);
	}
	
	public static boolean isVenue(String venueID){
		return Common.regCheck(ParaName.venueIDRegex, venueID);
	}
	
	public static boolean isMgr(String mgrID){
		return Common.regCheck(ParaName.mgrIDRegex, mgrID);
	}
	
	public static boolean regCheck(String regex, String testStr){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(testStr);
		return matcher.matches();
	}
	
	public static void createSession(HttpServletRequest request, String EmailORID){
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(600);
		session.setAttribute(ParaName.VerificationCode, EmailORID);
	}
	
}
