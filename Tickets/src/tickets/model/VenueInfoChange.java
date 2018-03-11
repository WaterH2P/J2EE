package tickets.model;

public class VenueInfoChange {
	
	private String venueID = "";
	private String province = "";
	private String city = "";
	private String address = "";
	private String telephone = "";
	
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
	
	public void show(){
		System.out.println( this.getVenueID() );
		System.out.println( this.getProvince() + this.getCity() );
		System.out.println( this.getAddress() );
		System.out.println( this.getTelephone() );
		System.out.println();
	}
	
}
