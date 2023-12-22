import org.json.*;

public class Room {
	private int room_id;
	private int hotel_id;
	private String room_type;
	private String image;
	private int room_price;
	private int max_capacity;
	private int room_number; //total room in a hotel
	
	//add
	public Room(int hotel_id,String room_type,String image,int room_number,int room_price,int max_capacity) {
		this.hotel_id = hotel_id;
		this.room_type = room_type;
		this.image = image;
		this.room_number = room_number;
		this.room_number = room_number;
		this.max_capacity = max_capacity;
	}
	//revise,getdata
	public Room(int room_id,int hotel_id,String room_type,String image,int room_number,int room_price,int max_capacity){
		this.room_id = room_id;
		this.hotel_id = hotel_id;
		this.room_type = room_type;
		this.image = image;
		this.room_number = room_number;
		this.room_number = room_number;
		this.max_capacity = max_capacity;
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
	public JSONObject getOrderData() {
		JSONObject jso = new JSONObject();
		jso.put("room type", getRoomType());
		jso.put("image",getImage());
		jso.put("room price",getRoomPrice());
		jso.put("max capacity",getMaxCapacity());
		jso.put("room number", getRoomNumber());
		return jso;
	}
	
}
