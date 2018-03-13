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
		result = unifyStrTest();
		System.out.println( result );
		System.out.println( VenueHallManageCon.unifyStrBack(result) );
		System.out.println( VenueHallManageCon.oneZeroToHexadecimal(result) );
		System.out.println( VenueHallManageCon.hexadecimalToOneZero(result) );
	}
	
	private static boolean emailRegexTest(){
		String email = "z110a110@126.com";
		return CommonCon.regCheck(ParaName.emailRegex, email);
	}
	
	private static boolean venueIDRegexTest(){
		String venueID = "1628374";
		return CommonCon.regCheck(ParaName.venueIDRegex, venueID);
	}
	
	private static String unifyStrTest(){
		String str = "aaaabbbbabba__aasf_faf_ad";
		str = VenueHallManageCon.unifyStr(str);
		return str;
	}
}
