package app;

import java.time.LocalDate;
import org.json.*;

public class Order {
	private int order_id;
	private int room_id;
	private int customer_id;
	private int order_number; 
	private int order_price;
	private int number_of_guest;
	private LocalDate booking_date;
	private LocalDate checkin_date;
	private LocalDate checkout_date;
	private String email;
	//add
	public Order(int room_id,int customer_id,int order_number,int order_price,int number_of_guest,LocalDate booking_date,LocalDate checkin_date,LocalDate checkout_date,String email) {
		this.room_id = room_id;
		this.customer_id = customer_id;
		this.order_number = order_number;
		this.order_price = order_price;
		this.number_of_guest = number_of_guest;
		this.booking_date = booking_date;
		this.checkin_date = checkin_date;
		this.checkout_date = checkout_date;
		this.email = email;
	}
	//getData
	public Order(int order_id,int room_id,int customer_id,int order_number,int order_price,int number_of_guest,LocalDate booking_date,LocalDate checkin_date,LocalDate checkout_date,String email) {
		this.order_id = order_id;
		this.room_id = room_id;
		this.customer_id = customer_id;
		this.order_number = order_number;
		this.order_price = order_price;
		this.number_of_guest = number_of_guest;
		this.booking_date = booking_date;
		this.checkin_date = checkin_date;
		this.checkout_date = checkout_date;
		this.email = email;
	}
	public int getOrder_id() {
		return order_id;
	}
	public int getRoom_id(){
		return room_id;
	}
	public int getCustomerId(){
		return customer_id;
	}
	public int getOrder_number() {
		return order_number;
	}
	public int getOrder_price() {
		return order_price;
	}
	public int getNumber_of_guest() {
		return number_of_guest;
	}
	public LocalDate getBooking_date() {
		return booking_date;
	}
	public LocalDate getCheckin_date() {
		return checkin_date;
	}
	public LocalDate getCheckout_date() {
		return checkout_date;
	}
	public String getEmail() {
		return email;
	}
	
	public JSONObject getData() {
		JSONObject jso = new JSONObject();
		jso.put("order_id", getOrder_id());
		jso.put("room_id",getRoom_id());
		jso.put("order_number", getOrder_number());
		jso.put("order_price", getOrder_price());
		jso.put("number_of_guest", getNumber_of_guest());
		jso.put("booking_date", getBooking_date());
		jso.put("checkin_date", getCheckin_date());
		jso.put("checkout_date", getCheckout_date());
		jso.put("email", getEmail());
		return jso;
	}
}