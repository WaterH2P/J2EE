package tickets.daoImpl;

public class ParaName {
	
	public final static String Table_userInfo = "userInfo";
	public final static String Table_userAccount = "userAccount";
	
	public final static String Table_venueBaseInfo = "venueBaseInfo";
	public final static String Table_venueBaseInfoChange = "venueBaseInfoChange";
	public final static String Table_venueAccount = "venueAccount";
	public final static String Table_venueHall = "venueHall";
	public final static String Table_venueSeatLevel = "venueSeatLevel";
	public final static String Table_venueHallSeat = "venueHallSeat";
	public final static String Table_venuePlan = "venuePlan";
	
	public final static String Table_vipInfo = "vipInfo";
	public final static String Table_couponInfo = "couponInfo";
	
	public final static String Table_mgrAccount = "mgrAccount";
	
	public final static String VerificationCode = "EmailORVenueID";
	
	public final static String emailRegex = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+.([a-z.])+$";
	public final static String venueIDRegex = "^[0-9]{7}$";
	public final static String mgrIDRegex = "^[0-9]{2}$";
}
