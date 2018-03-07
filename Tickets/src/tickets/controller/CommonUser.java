package tickets.controller;

public class CommonUser {
	
	public static String toLoginPage(){
		return "common/log/Login";
	}
	public static String redirectToLoginPage(){
		return "redirect:/Login";
	}
	
	public static String toUserRegister(){
		return "user/log/UserRegister";
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
