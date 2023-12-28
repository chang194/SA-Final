package controller;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.*;

import app.Order;
import app.OrderHelper;
import tools.JsonReader;


@WebServlet("/api/order.do")
public class OrderController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    private OrderHelper oh =  OrderHelper.getHelper();
    
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
            JsonReader jsr = new JsonReader(request);
            JSONObject jso = jsr.getObject();

            /** 取出經解析到JSONObject之Request參數 */
            int room_id = jso.getInt("room_id");
        	int customer_id = jso.getInt("customer_id");
        	int order_number = jso.getInt("order_number"); 
        	int order_price = jso.getInt("order_price");
        	int number_of_guest = jso.getInt("number_of_guest");
        	
        	String sbooking_date = jso.getString("booking_date");
        	String scheckin_date = jso.getString("checkin_date");
        	String scheckout_date = jso.getString("checkout_date");
        	// 定義日期字符串的格式
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    		// 將字符串轉換為 LocalDate
        	LocalDate booking_date = LocalDate.parse(sbooking_date, formatter);
        	LocalDate checkin_date = LocalDate.parse(scheckin_date, formatter);
        	LocalDate checkout_date = LocalDate.parse(scheckout_date, formatter);
            
        	String customer_point_use = jso.getString("customer_point_use");
        	boolean cpu = false;
        	if(customer_point_use.equals("true")) {cpu = true;}
            /** 建立一個新的Room物件 */
            Order o = new Order(room_id, customer_id, order_number, order_price, number_of_guest, booking_date, checkin_date, checkout_date);
            
            /** 透過RoomHelper物件的create()方法新建一個房型至資料庫 */
            JSONObject data = oh.checkout(o, cpu);
                
            /** 新建一個JSONObject用於將回傳之資料進行封裝 */
            JSONObject resp = new JSONObject();
            resp.put("status", "200");
            resp.put("message", "成功! 新增房型資料...");
            resp.put("response", data);
                
            /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            jsr.response(resp, response);
            
        }

    /**
     * 處理Http Method請求GET方法（取得資料）
     *
     * @param request Servlet請求之HttpServletRequest之Request物件（前端到後端）
     * @param response Servlet回傳之HttpServletResponse之Response物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        /** 若直接透過前端AJAX之data以key=value之字串方式進行傳遞參數，可以直接由此方法取回資料 */
        int customer_id = Integer.parseInt(jsr.getParameter("customer_id"));
        int hotel_id = Integer.parseInt(jsr.getParameter("hotel_id"));
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        JSONObject query = new JSONObject();
        
        if(customer_id == -1) {
        	/** 透過RoomHelper物件的getByID()方法自資料庫取回該房型之資料，回傳之資料為JSONObject物件 */
        	query = oh.getByHotelID(hotel_id);
        }
        else {
        	query = oh.getByCustomerID(customer_id);
        }
            
        resp.put("status", "200");
        resp.put("message", "訂單資料取得成功");
        resp.put("response", query);
        
        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
    }
    
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
            JsonReader jsr = new JsonReader(request);
            JSONObject jso = jsr.getObject();
         
            /** 取出經解析到JSONObject之Request參數 */
            int id = jso.getInt("order_id");
         
            /** 透過RoomHelper物件的deleteByID()方法至資料庫刪除該房型，回傳之資料為JSONObject物件 */
            JSONObject query = oh.deleteByID(id);
         
            /** 新建一個JSONObject用於將回傳之資料進行封裝 */
            JSONObject resp = new JSONObject();
            resp.put("status", "200");
            resp.put("message", "訂單刪除成功！");
            resp.put("response", query);

            /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            jsr.response(resp, response);
        }

}

