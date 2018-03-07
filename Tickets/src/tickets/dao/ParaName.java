package tickets.dao;

public class ParaName {
	
	public final static String Table_userInfo = "userInfo";
	public final static String Table_userAccount = "userAccount";
	
	public final static String Table_venueInfo = "venueInfo";
	public final static String Table_venueAccount = "venueAccount";
	
	public final static String VerificationCode = "EmailORVenueID";
	
	public final static String emailRegex = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+.([a-z.])+$";
	public final static String venueIDRegex = "^[0-9]{7}$";
}
