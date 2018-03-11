package tickets.controller.mgr;

public class CommonMgr {
	
	public static String toMgrInfoPage(){
		return "mgr/MgrInfo";
	}
	public static String redirectToMgrInfoPage(){
		return "redirect:/MgrInfo";
	}
	
	public static String toMgrExamineVenueRegisterPage(){
		return "mgr/MgrExamineVenueRegister";
	}
	public static String redirectToMgrExamineVenueRegisterPage(){
		return "redirect:/MgrExamineVenueRegister";
	}
	
	public static String toMgrExamineVenueInfoChangePage(){
		return "mgr/MgrExamineVenueInfoChange";
	}
	public static String redirectToMgrExamineVenueInfoChangePage(){
		return "redirect:/MgrExamineVenueInfoChange";
	}
	
}
