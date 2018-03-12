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
		return "redirect:/UserInfo";
	}
	
}
