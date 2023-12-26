package app;

import org.json.*;

public class Room {
	private int room_id;
	private int hotel_id;
	private String room_type;
	private String image;
	private int room_price;
	private int max_capacity;
	private int room_number; //此房型的總數量
	private String room_info;
	
	//add
	public Room(int hotel_id,String room_type,String image,int room_number,int room_price,int max_capacity,String room_info) {
		this.hotel_id = hotel_id;
		this.room_type = room_type;
		this.image = image;
		this.room_number = room_number;
		this.room_price = room_price;
		this.max_capacity = max_capacity;
		this.room_info=room_info;
	}
	//revise,getdata
	public Room(int room_id,int hotel_id,String room_type,String image,int room_number,int room_price,int max_capacity,String room_info){
		this.room_id = room_id;
		this.hotel_id = hotel_id;
		this.room_type = room_type;
		this.image = image;
		this.room_number = room_number;
		this.room_price = room_price;
		this.max_capacity = max_capacity;
		this.room_info=room_info;
	}
	public int getRoomId() {
		return room_id;
	}
	public int getHotelId(){
		return hotel_id;
	}
	public String getRoomType() {
		return room_type;
	}
	public String getImage(){
		return image;
	}
	public int getRoomPrice(){
		return room_price;
	}
	public int getRoomNumber() {
		return room_number;
	}
	public int getMaxCapacity(){
		return max_capacity;
	}
	public String getRoomInfo(){
		return room_info;
	}
	public JSONObject getData() {
		JSONObject jso = new JSONObject();
		jso.put("hotel_id", getHotelId());
		jso.put("room_type", getRoomType());
		jso.put("image",getImage());
		jso.put("room_price",getRoomPrice());
		jso.put("max_capacity",getMaxCapacity());
		jso.put("room_number", getRoomNumber());
		jso.put("room_info", getRoomInfo());
		return jso;
	}
	
}