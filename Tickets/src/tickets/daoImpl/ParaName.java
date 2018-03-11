package tickets.daoImpl;

public class ParaName {
	
	public final static String Table_userInfo = "userInfo";
	public final static String Table_userAccount = "userAccount";
	
	public final static String Table_venueInfo = "venueInfo";
	public final static String Table_venueInfoChange = "venueInfoChange";
	public final static String Table_venueAccount = "venueAccount";
	
	public final static String Table_mgrAccount = "mgrAccount";
	
	public final static String VerificationCode = "EmailORVenueID";
	
	public final static String emailRegex = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+.([a-z.])+$";
	public final static String venueIDRegex = "^[0-9]{7}$";
	public final static String mgrIDRegex = "^[0-9]{2}$";
}
