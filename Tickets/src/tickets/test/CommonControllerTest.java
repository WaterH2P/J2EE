package tickets.test;

import tickets.controller.CommonCon;
import tickets.controller.venue.VenueHallManageCon;
import tickets.daoImpl.ParaName;

public class CommonControllerTest {
	
	public static void main(String args[]){
//		boolean result = false;
//		result = emailRegexTest();
//		result = venueIDRegexTest();
		
		String result = "";
		System.out.println( result );
	}
	
	private static boolean emailRegexTest(){
		String email = "z110a110@126.com";
		return CommonCon.regCheck(ParaName.emailRegex, email);
	}
	
	private static boolean venueIDRegexTest(){
		String venueID = "1628374";
		return CommonCon.regCheck(ParaName.venueIDRegex, venueID);
	}
}
