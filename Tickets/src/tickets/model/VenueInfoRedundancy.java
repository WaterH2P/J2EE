package tickets.model;

public class VenueInfoRedundancy {
	
	private VenueInfo venueInfo;
	private VenueInfoChange venueInfoChange;
	
	public VenueInfo getVenueInfo(){
		return venueInfo;
	}
	
	public void setVenueInfo(VenueInfo venueInfo){
		this.venueInfo = venueInfo;
	}
	
	public VenueInfoChange getVenueInfoChange(){
		return venueInfoChange;
	}
	
	public void setVenueInfoChange(VenueInfoChange venueInfoChange){
		this.venueInfoChange = venueInfoChange;
	}
	
}
