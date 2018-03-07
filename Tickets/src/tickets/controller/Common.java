package tickets.controller;

import tickets.dao.ParaName;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Common {
	
	public static boolean hasLogin( HttpSession session ){
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
	
	public static boolean regCheck(String regex, String testStr){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(testStr);
		return matcher.matches();
	}
	
}
