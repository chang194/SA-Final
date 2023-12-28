package app;

import org.json.*;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class ShoppingCart<br>
 * ShoppingCart類別（class）主要管理所有與ShoppingCart相關與資料庫之方法
 * </p>
 * 
 * @author Winnie
 * @version 1.0.0
 * @since 1.0.0
 */

public class ShoppingCart {
    private int customer_id;
    private int room_id;
    private int order_number;
    private String hotel_name;
    private int room_price;
    private String room_type;
    private String image;
    private int hotel_id;
    private String location;
    private int customer_point;

    //新增的建構子
    public ShoppingCart(int customer_id,int room_id,int order_number){
        this.customer_id = customer_id;
        this.room_id = room_id;
        this.order_number = order_number;
    }
    //修改
    public ShoppingCart(int room_id,int order_number){
        this.room_id = room_id;
        this.order_number = order_number;
    }
    //查詢
    public ShoppingCart(int room_id,int order_number,String hotel_name,int room_price,String room_type,String image,int hotel_id){
        this.room_id = room_id;
        this.order_number = order_number;
        this.hotel_name = hotel_name;
        this.room_price = room_price;
        this.room_type = room_type;
        this.image = image;
        this.hotel_id = hotel_id;
    }
    //傳資料給結帳畫面用
    public ShoppingCart(int customer_id,int room_id,int order_number,String hotel_name,int room_price,String room_type,String image,int hotel_id,String location,int customer_point){
        this.customer_id = customer_id;
    	this.room_id = room_id;
        this.order_number = order_number;
        this.hotel_name = hotel_name;
        this.room_price = room_price;
        this.room_type = room_type;
        this.image = image;
        this.hotel_id = hotel_id;
        this.location = location;
        this.customer_point = customer_point;
    }

    public int getCustomerId(){
        return customer_id;
    }
    public int getRoomId(){
        return room_id;
    }
    public int getOrderNumber(){
        return order_number;
    }
    public String getHotelName(){
        return hotel_name;
    }
    public int getRoomPrice(){
        return room_price;
    }
    public String getRoomType() {
    	return room_type;
    }
    public String getImage() {
    	return image;
    }
    public int getHotelId() {
    	return hotel_id;
    }
    public String getLocation() {
    	return location;
    }
    public int getCustomerPoint() {
    	return customer_point;
    }

    /**
     * 取得該購物車所有資料
     *
     * @return the data 取得該購物車之所有資料並封裝於JSONObject物件內
     */
    public JSONObject getData(){
        JSONObject jso = new JSONObject();
        jso.put("customer_id",getCustomerId());
        jso.put("room_id",getRoomId());
        jso.put("order_number",getOrderNumber());
        jso.put("hotel_name",getHotelName());
        jso.put("room_price",getRoomPrice());
        jso.put("room_type", getRoomType());
        jso.put("room_image",getImage());
        jso.put("hotel_id", getHotelId());
        jso.put("hotel_location", getLocation());
        jso.put("customer_point", getCustomerPoint());
        return jso;
    }
}
