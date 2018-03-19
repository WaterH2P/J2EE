package tickets.controller;

import tickets.daoImpl.ParaName;
import tickets.model.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonCon {
	
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
		return CommonCon.regCheck(ParaName.emailRegex, email);
	}
	
	public static boolean isVenue(String venueID){
		return CommonCon.regCheck(ParaName.venueIDRegex, venueID);
	}
	
	public static boolean isMgr(String mgrID){
		return CommonCon.regCheck(ParaName.mgrIDRegex, mgrID);
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
	
	public static Result strCheck(String strChecked, String strChecking){
		Result result = new Result();
		if( !strChecked.equals(strChecking) ){
			result.setResult(true);
			result.setMessage(strChecked);
		}
		else {
			result.setResult(false);
			String message = "购票冲突，请刷新重试！";
			result.setMessage(message);
		}
		return result;
	}
	
}
