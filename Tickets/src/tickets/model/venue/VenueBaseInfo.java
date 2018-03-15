package tickets.model.venue;

public class VenueBaseInfo {
	
	public VenueBaseInfo(){}
	public VenueBaseInfo(VenueBaseInfoChange venueInfo){
		this.setVenueID( venueInfo.getVenueID() );
		this.setProvince( venueInfo.getProvince() );
		this.setCity( venueInfo.getCity() );
		this.setAddress( venueInfo.getAddress() );
		this.setTelephone( venueInfo.getTelephone() );
		this.setIsChanging( false );
	}
	
	private String venueID = "";
	private String province = "";
	private String city = "";
	private String address = "";
	private String telephone = "";
	private boolean isChanging = false;
	
	public void setVenueID(String venueID){
		this.venueID = venueID;
	}
	
	public void setProvince(String province){
		this.province = province;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public void setTelephone(String telephone){
		this.telephone = telephone;
	}
	
	public void setIsChanging(boolean isChanging){
		this.isChanging = isChanging;
	}
	
	public String getVenueID(){
		return venueID;
	}
	
	public String getProvince(){
		return province;
	}
	
	public String getCity(){
		return city;
	}
	
	public String getAddress(){
		return address;
	}
	
	public String getTelephone(){
		return telephone;
	}
	
	public boolean getIsChanging(){
		return isChanging;
	}
	
	public void show(){
		System.out.println( this.getVenueID() );
		System.out.println( this.getProvince() + this.getCity() );
		System.out.println( this.getAddress() );
		System.out.println( this.getTelephone() );
		System.out.println( this.getIsChanging() );
		System.out.println();
	}
	
}
