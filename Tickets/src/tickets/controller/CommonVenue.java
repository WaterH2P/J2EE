package tickets.controller;

public class CommonVenue {
	
	public static String toVenueRegisterPage(){
		return "venue/log/VenueRegister";
	}
	public static String redirectToVenueRegisterPage(){
		return "redirect:/VenueRegister";
	}
	
}
