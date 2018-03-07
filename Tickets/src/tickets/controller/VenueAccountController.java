package tickets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tickets.model.VenueInfo;
import tickets.service.VenueAccountService;

import javax.annotation.Resource;

@Controller
public class VenueAccountController {
	
	@Resource(name = "venueAccountService")
	private VenueAccountService venueAccountService;
	
	@RequestMapping(value = "/VenueRegister", method = RequestMethod.GET)
	public String VenueRegisterPage(){
		return CommonVenue.toVenueRegisterPage();
	}
	
	@RequestMapping(value = "/VenueRegister", method = RequestMethod.POST)
	public String VenueRegister(String province, String city, String address,
	                            String telephone, String password){
		
		System.out.println("Someone is register!");
		
		VenueInfo venueInfo = new VenueInfo();
		venueInfo.setProvince(province);
		venueInfo.setCity(city);
		venueInfo.setAddress(address);
		venueInfo.setTelephone(telephone);
		venueAccountService.preRegister(venueInfo, password);
		return CommonVenue.toVenueRegisterPage();
	}
}
