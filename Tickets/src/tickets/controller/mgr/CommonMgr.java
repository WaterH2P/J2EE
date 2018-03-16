package tickets.controller.mgr;

public class CommonMgr {
	
	public static String toMgrInfoPage(){
		return "mgr/MgrInfo";
	}
	public static String redirectToMgrInfoPage(){
		return "redirect:/Mgr/MgrInfo";
	}
	
	public static String toMgrExamineVenueRegisterPage(){
		return "mgr/MgrExamineVenueRegister";
	}
	public static String redirectToMgrExamineVenueRegisterPage(){
		return "redirect:/Mgr/MgrExamineVenueRegister";
	}
	
	public static String toMgrExamineVenueInfoChangePage(){
		return "mgr/MgrExamineVenueInfoChange";
	}
	public static String redirectToMgrExamineVenueInfoChangePage(){
		return "redirect:/Mgr/MgrExamineVenueInfoChange";
	}
	
	public static String toMgrSetVIPDiscountChangePage(){
		return "mgr/MgrSetVIPDiscount";
	}
	public static String redirectToMgrSetVIPDiscountChangePage(){
		return "redirect:/Mgr/MgrSetVIPDiscount";
	}
	
}
