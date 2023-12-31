package controller;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.time.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.*;

import app.Room;
import app.RoomHelper;
import tools.JsonReader;


@WebServlet("/api/room.do")
public class RoomController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    private RoomHelper rh =  RoomHelper.getHelper();
    
    /**
     * 處理Http Method請求POST方法（新增資料）
     *
     * @param request Servlet請求之HttpServletRequest之Request物件（前端到後端）
     * @param response Servlet回傳之HttpServletResponse之Response物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();

        /** 取出經解析到JSONObject之Request參數 */
        int hotel_id = jso.getInt("hotel_id");
        String room_type = jso.getString("room_type");
        int room_number = jso.getInt("room_number");
        int room_price = jso.getInt("room_price");
        int max_capacity = jso.getInt("max_capacity");
        String room_info = jso.getString("room_info");
        
        /** 建立一個新的Room物件 */
        Room r = new Room(hotel_id, room_type, room_number, room_price, max_capacity, room_info);
        
        /** 透過RoomHelper物件的create()方法新建一個房型至資料庫 */
        JSONObject data = rh.create(r);
            
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "成功! 新增房型資料");
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
            int room_id = Integer.parseInt(jsr.getParameter("room_id"));
            int hotel_id = Integer.parseInt(jsr.getParameter("hotel_id"));
            System.out.println(hotel_id);
            
            /** 新建一個JSONObject用於將回傳之資料進行封裝 */
            JSONObject resp = new JSONObject();

            if(hotel_id == -1) {
            	/** 透過RoomHelper物件的getByID()方法自資料庫取回該房型之資料，回傳之資料為JSONObject物件 */
            	String dateString = jsr.getParameter("day");
            	 java.sql.Date realdate = null;

                // 使用 SimpleDateFormat 解析字串
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate;
                try {
                    utilDate = dateFormat.parse(dateString);

                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    LocalDate localDate = sqlDate.toLocalDate();

                    // 加一天
                    LocalDate nextDay = localDate.plusDays(1);

                    // 將結果轉換回 java.sql.Date
                    realdate = Date.valueOf(nextDay);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            	JSONObject query = rh.getAvailableDate(room_id,realdate);
            	
                
            	resp.put("status", "200");
            	resp.put("message", "房型資料取得成功");
            	resp.put("response", query);
            
            	/** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            	jsr.response(resp, response);
            }
            else if(hotel_id == -2) {
            	String day1 = jsr.getParameter("day1");
            	String day2 = jsr.getParameter("day2");
            	LocalDate nextday1 = null;
            	LocalDate nextday2 = null;

                // 使用 SimpleDateFormat 解析字串
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate1;
                java.util.Date utilDate2;
                try {
                    utilDate1 = dateFormat.parse(day1);
                    utilDate2 = dateFormat.parse(day2);

                    java.sql.Date sqlDate1 = new java.sql.Date(utilDate1.getTime());
                    java.sql.Date sqlDate2 = new java.sql.Date(utilDate2.getTime());
                    LocalDate localDate1 = sqlDate1.toLocalDate();
                    LocalDate localDate2 = sqlDate2.toLocalDate();
                    // 加一天
                    nextday1 = localDate1.plusDays(1);
                    nextday2 = localDate2.plusDays(1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                JSONObject query = rh.getAvailableRange(room_id,nextday1,nextday2);
            	
                
            	resp.put("status", "200");
            	resp.put("message", "房型資料取得成功");
            	resp.put("response", query);
            
            	/** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            	jsr.response(resp, response);
            	
            }
            else if(hotel_id == -3) {
            	JSONObject query = rh.getByID(room_id);
                System.out.println(query);
                resp.put("status", "200");
                resp.put("message", "房型資料取得成功");
                resp.put("response", query);
                
                /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
                jsr.response(resp, response);
            }
            else {
            	JSONObject query = rh.getByHotelID(hotel_id);
                System.out.println(query);
                resp.put("status", "200");
                resp.put("message", "所有房型資料取得成功");
                resp.put("response", query);
                
                /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
                jsr.response(resp, response);
            }
        }

    /**
     * 處理Http Method請求PUT方法（更新）
     *
     * @param request Servlet請求之HttpServletRequest之Request物件（前端到後端）
     * @param response Servlet回傳之HttpServletResponse之Response物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */

    public void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
     
        /** 取出經解析到JSONObject之Request參數 */
        int id = jso.getInt("room_id");
     
        /** 透過RoomHelper物件的deleteByID()方法至資料庫刪除該房型，回傳之資料為JSONObject物件 */
        JSONObject query = rh.deleteByID(id);
     
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "房型移除成功！");
        resp.put("response", query);

        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
    }
    
    public void doPut(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        /** 取出經解析到JSONObject之Request參數 */
        int room_id = jso.getInt("room_id");
        int hotel_id = jso.getInt("hotel_id");
        String room_type = jso.getString("room_type");
        String image = jso.getString("image");
        int room_number = jso. getInt("room_number");
        int room_price = jso. getInt("room_price");
        int max_capacity = jso. getInt("max_capacity");
        String room_info = jso.getString("room_info");
        
        /** 建立一個新的Room物件 */
        Room r = new Room(room_id, hotel_id, room_type, image, room_number, room_price, max_capacity, room_info);
        
        /** 透過RoomHelper物件的update()方法至資料庫更新該名會員資料，回傳之資料為JSONObject物件 */
        JSONObject data = rh.update(r);
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "成功! 更新會員資料...");
        resp.put("response", data);
        
        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
    }


}
