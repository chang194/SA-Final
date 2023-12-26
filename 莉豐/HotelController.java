package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.*;

import app.Hotel;
import app.HotelHelper;
import tools.JsonReader;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.*;

import app.Hotel;
import app.HotelHelper;
import tools.JsonReader;


@WebServlet("/api/hotel.do")
public class HotelController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    private HotelHelper hh =  HotelHelper.getHelper();
    
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
        String hotelname = jso.getString("hotelname");
        String location = jso.getString("location");
        String image = jso.getString("image");
        String facilities = jso. getString("facilities");
        String intro = jso. getString("intro");
        //取的hotelowner_id
        int hotelowner_id = jso. getInt("hotelowner_id");
        
        /** 建立一個新的Hotel物件 */
        Hotel h = new Hotel(hotelname, location, image, facilities, intro);
        
        /** 透過RoomHelper物件的create()方法新建一個房型至資料庫 */
        JSONObject data = hh.create(h, hotelowner_id);
            
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
        int id = Integer.parseInt(jsr.getParameter("id"));
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();

        if(id == -1) {
        	/** 透過HotelHelper物件之getAll()方法取回所有旅程之資料，回傳之資料為JSONObject物件 */
            JSONObject query = hh.getAll();
            
            /** 新建一個JSONObject用於將回傳之資料進行封裝 */

            resp.put("status", "200");
            resp.put("message", "所有房型資料取得成功");
            resp.put("response", query);
    
            /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            jsr.response(resp, response);
        }else {
        	/** 透過RoomHelper物件的getByID()方法自資料庫取回該房型之資料，回傳之資料為JSONObject物件 */
	        JSONObject query = hh.getByID(id);
	            
	        resp.put("status", "200");
	        resp.put("message", "房型資料取得成功");
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
    public void doPut(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        /** 取出經解析到JSONObject之Request參數 */
        int hotel_id = jso.getInt("hotel_id");
        String hotelname = jso.getString("hotelname");
        String location = jso.getString("location");
        String image = jso.getString("image");
        String facilities = jso. getString("facilities");
        String intro = jso. getString("intro");
        
        /** 建立一個新的Hotel物件 */
        Hotel h = new Hotel(hotel_id, hotelname, location, image, facilities, intro);
        
        /** 透過RoomHelper物件的update()方法至資料庫更新該名會員資料，回傳之資料為JSONObject物件 */
        JSONObject data = hh.update(h);
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "成功! 更新旅店資料...");
        resp.put("response", data);
        
        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
    }


}
