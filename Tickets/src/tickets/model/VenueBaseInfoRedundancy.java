package tickets.model;

public class VenueBaseInfoRedundancy {
	
	private VenueBaseInfo venueBaseInfo;
	private VenueBaseInfoChange venueBaseInfoChange;
	
	public VenueBaseInfo getVenueBaseInfo(){
		return venueBaseInfo;
	}
	
	public void setVenueBaseInfo(VenueBaseInfo venueBaseInfo){
		this.venueBaseInfo = venueBaseInfo;
	}
	
	public VenueBaseInfoChange getVenueBaseInfoChange(){
		return venueBaseInfoChange;
	}
	
	public void setVenueBaseInfoChange(VenueBaseInfoChange venueBaseInfoChange){
		this.venueBaseInfoChange = venueBaseInfoChange;
	}
	
}
