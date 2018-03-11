package tickets.controller.venue;

public class CommonVenue {
	
	public static String toVenueRegisterPage(){
		return "venue/VenueRegister";
	}
	public static String redirectToVenueRegisterPage(){
		return "redirect:/VenueRegister";
	}
	
	public static String toVenueInfoPage(){
		return "venue/VenueInfo";
	}
	public static String redirectToVenueInfoPage(){
		return "redirect:/VenueInfo";
	}
}
