package app;

import org.json.JSONObject;

public class Hotel {
	private int hotel_id;
	private String hotelname;
	private int hotelowner_id;
	private String location;
	private String image;
	private String facilities;
	private String intro;
	
	//add
	public Hotel(String hotelname,int hotelowner_id,String location,String image,String facilities,String intro){
		this.hotelname = hotelname;
		this.hotelowner_id = hotelowner_id;
		this.location = location;
		this.image = image;
		this.facilities = facilities;
		this.intro = intro;
	}
	//revise
	public Hotel(int hotel_id,String hotelname,String location,String image,String facilities,String intro) {
		this.hotel_id = hotel_id;
		this.hotelname = hotelname;
		this.location = location;
		this.image = image;
		this.facilities = facilities;
		this.intro = intro;
	}
	
	public int getHotel_id(){
		return hotel_id;
	}
	public String getHotelName() {
		return hotelname;
	}
	public int getHotelowner() {
		return hotelowner_id;
	}
	public String getLocation() {
		return location;
	}
	public String getImage(){
		return image;
	}
	public String getFacilities() {
		return facilities;
	}
	public String getIntro() {
		return intro;
	}
	public JSONObject getData(){
		JSONObject jso = new JSONObject();
		jso.put("hotelname",getHotelName());
		jso.put("location",getLocation());
		jso.put("image",getImage());
		jso.put("facilities",getFacilities());
		jso.put("introduction",getIntro());
		return jso;
	}
}