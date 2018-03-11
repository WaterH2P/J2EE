package tickets.test;

import tickets.controller.Common;
import tickets.daoImpl.ParaName;

public class CommonControllerTest {
	
	public static void main(String args[]){
		boolean result = false;
		result =emailRegexTest();
//		result =venueIDRegexTest();
		
		System.out.println( result );
	}
	
	private static boolean emailRegexTest(){
		String email = "z110a110@126.com";
		return Common.regCheck(ParaName.emailRegex, email);
	}
	
	private static boolean venueIDRegexTest(){
		String venueID = "1628374";
		return Common.regCheck(ParaName.venueIDRegex, venueID);
	}
}
