package tickets.daoImpl;

public class ParaName {
	
	public final static String Table_userInfo = "userInfo";
	public final static String Table_userAccount = "userAccount";
	public final static String Table_userCoupon = "userCoupon";
	public final static String Table_userOd = "userOd";
	public final static String Table_userOdSeat = "userOdSeat";
	
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
	
	public final static String message_ownNoAuthority = "很抱歉你没有权限!";
	public final static String message_failToOperate = "很抱歉操作失败，请重新尝试！";
	public final static String message_failToChange = "很抱歉修改失败，请重新尝试！";
	public final static String message_failToAdd = "很抱歉新增失败，请重新尝试！";
	public final static String message_failToDelete = "很抱歉删除失败，请重新尝试！";
	
	public final static String return_false = "false";
	public final static String return_true = "true";
	
	public final static String seat_none = "0";
	public final static String seat_available = "1";
	public final static String seat_selected = "2";
	public final static String seat_unavailable = "3";
	
	public final static String account_fictitious = "fictitious@fictitious.com";
	
	public final static String exception_accountInvalid = "账号不存在！";
	public final static String exception_accountDeleted = "账号已删除！";
	public final static String exception_accountUnconfirmed = "账号还未通过验证！";
	public final static String exception_accountOrPasswordWrong = "帐号或密码错误，请重新登录！";
}
