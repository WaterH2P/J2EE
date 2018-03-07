package tickets.controller;

import javax.servlet.http.HttpSession;

public class Common {
	
	public static boolean hasLogin( HttpSession session ){
		if( session==null ){
			return false;
		}
		else {
			if( session.getAttribute("email")==null ){
				session.invalidate();
				return false;
			}
			else{
				return true;
			}
		}
	}
	
}
