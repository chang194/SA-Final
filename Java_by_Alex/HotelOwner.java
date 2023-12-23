package app;
import java.time.LocalDate;
import java.util.Date;

import org.json.JSONObject;

public class HotelOwner extends Member{
	private int hotelowner_id;
	private int hotel_id;

	//register
	public HotelOwner(String name, String email, String password, LocalDate birthday, String intro){
		super(name,email,password,birthday,intro);
	}
	//revise,getData
	public HotelOwner(int hotelowner_id, String name, String email, String password, LocalDate birthday, String intro, Date modified_time, int hotel_id) {
		super(name,email,password,birthday,intro,modified_time);
		this.hotelowner_id = hotelowner_id;
		this.hotel_id = hotel_id;
	}
	public int getHotelOwnerId(){
		return hotelowner_id;
	}

	public int getHotelId(){
		return hotel_id;
	}

	public JSONObject getData(){
		JSONObject jso = super.getData(); // 使用父類別的 getData() 取得基本資料

        // 加入額外的資料
        jso.put("hotelowner_id", getHotelOwnerId());
        jso.put("hotel_id", getHotelId());

        return jso;
	}
}
