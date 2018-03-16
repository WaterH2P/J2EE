package tickets.controller.user;

public class CommonUser {
	
	public static String toUserRegisterPage(){
		return "UserRegister";
	}
	public static String redirectToUserRegisterPage(){
		return "redirect:/Register";
	}
	
	public static String toUserInfoPage(){
		return "user/UserInfo";
	}
	public static String redirectToUserInfoPage(){
		return "redirect:/User/UserInfo";
	}
	
	public static String toUserOrderPage(){
		return "user/UserOrder";
	}
	public static String redirectToUserOrderPage(){
		return "redirect:/User/UserOrder";
	}
	
	public static String toUserBuyTicketOnlinePage(){
		return "user/UserBuyTicket";
	}
	public static String redirectToUserBuyTicketOnlinePage(){
		return "redirect:/User/UserBuyTicket";
	}
}
