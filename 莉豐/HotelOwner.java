import java.util.Date;

public class HotelOwner extends Member{
	private int hotelowner_id;
	//register
	public HotelOwner(String name,String email,String password,Date birthday,String intro){
		super(name,email,password,birthday,intro);
	}
	//revise,getData
	public HotelOwner(int hotelowner_id,String name,String email,String password,Date birthday,String intro,Date modified_time) {
		super(name,email,password,birthday,intro,modified_time);
		this.hotelowner_id = hotelowner_id;
	}
	public int getHotelOwnerId(){
		return hotelowner_id;
	}
}