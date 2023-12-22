import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Date;
import org.json.*;

public class Order {
	private int order_id;
	private Room room;
	private int customer_id;
	private int order_number; //訂了幾間房
	private int order_price;
	private int number_of_guest;
	private Date booking_date;
	private Date checkin_date;
	private Date checkout_date;
	//add
	public Order(Room room,int customer_id,int order_number,int order_price,int number_of_guest,Date booking_date,Date checkin_date,Date checkout_date) {
		this.room = room;
		this.customer_id = customer_id;
		this.order_number = order_number;
		this.order_price = order_price;
		this.number_of_guest = number_of_guest;
		this.booking_date = booking_date;
		this.checkin_date = checkin_date;
		this.checkout_date = checkout_date;
	}
	//getData
	public Order(int order_id,Room room,int customer_id,int order_number,int order_price,int number_of_guest,Date booking_date,Date checkin_date,Date checkout_date) {
		this.order_id = order_id;
		this.room = room;
		this.customer_id = customer_id;
		this.order_number = order_number;
		this.order_price = order_price;
		this.number_of_guest = number_of_guest;
		this.booking_date = booking_date;
		this.checkin_date = checkin_date;
		this.checkout_date = checkout_date;
	}
	public int getOrder_id() {
		return order_id;
	}
	public Room getRoom(){
		return room;
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
	public Date getBooking_date() {
		return booking_date;
	}
	public Date getCheckin_date() {
		return checkin_date;
	}
	public Date getCheckout_date() {
		return checkout_date;
	}
	
	public JSONObject getOrderData() {
		JSONObject jso = new JSONObject();
		jso.put("Order_id", getOrder_id());
		jso.put("Room",getRoom());
		jso.put("Order_number", getOrder_number());
		jso.put("Order_price", getOrder_price());
		jso.put("number_of_guest", getNumber_of_guest());
		jso.put("booking_date", getBooking_date());
		jso.put("checkin_date", getCheckin_date());
		jso.put("checkout_date", getCheckout_date());
		return jso;
	}
}
