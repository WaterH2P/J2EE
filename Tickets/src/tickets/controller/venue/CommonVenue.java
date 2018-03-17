package tickets.controller.venue;

public class CommonVenue {
	
	public static String toVenueRegisterPage(){
		return "VenueRegister";
	}
	public static String redirectToVenueRegisterPage(){
		return "redirect:/Venue/VenueRegister";
	}
	
	public static String toVenueBaseInfoPage(){
		return "venue/VenueBaseInfo";
	}
	public static String redirectToVenueBaseInfoPage(){
		return "redirect:/Venue/VenueBaseInfo";
	}
	
	public static String toVenueSeatLevelManagePage(){
		return "venue/VenueSeatLevelManage";
	}
	public static String redirectToVenueSeatLevelManagePage(){
		return "redirect:/Venue/VenueSeatLevelManage";
	}
	
	public static String toVenueHallManagePage(){
		return "venue/VenueHallManage";
	}
	public static String redirectToVenueHallManagePage(){
		return "redirect:/Venue/VenueHallManage";
	}
	
	public static String toVenuePlanManagePage(){
		return "venue/VenuePlanManage";
	}
	public static String redirectToVenuePlanManagePage(){
		return "redirect:/Venue/VenuePlanManage";
	}
	
}
