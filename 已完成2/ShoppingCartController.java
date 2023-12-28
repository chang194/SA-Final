package controller;

import java.io.*;
import javax.servlet.annotation.WebServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;
import app.ShoppingCart;
import app.ShoppingCartHelper;
import tools.JsonReader;

@WebServlet("/api/shoppingcart.do")
public class ShoppingCartController extends HttpServlet{
    
    private static final long serialVersionUID = 1L;
    
    private ShoppingCartHelper sh =  ShoppingCartHelper.getHelper();
    
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
        int customer_id = jso.getInt("customer_id");
        int room_id = jso.getInt("room_id");
        int order_number = jso.getInt("order_number");
        
        /** 建立一個新的shoppingcart物件 */
        ShoppingCart sc = new ShoppingCart(customer_id,room_id,order_number);
        
        if(sh.checkData(sc) > 0) {
        	order_number += sh.checkData(sc);
        	ShoppingCart update_sc = new ShoppingCart(customer_id,room_id,order_number);
        	
        	JSONObject data = sh.update(update_sc);
        	/** 新建一個JSONObject用於將回傳之資料進行封裝 */
            JSONObject resp = new JSONObject();
            resp.put("status", "200");
            resp.put("message", "成功! 新增至購物車成功...");
            resp.put("response", data);
                
            /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            jsr.response(resp, response);
        }
        else {
        	/** 透過RoomHelper物件的create()方法新建一個房型至資料庫 */
            JSONObject data = sh.create(sc);
            /** 新建一個JSONObject用於將回傳之資料進行封裝 */
            JSONObject resp = new JSONObject();
            resp.put("status", "200");
            resp.put("message", "成功! 新增至購物車成功...");
            resp.put("response", data);
                
            /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            jsr.response(resp, response);
        }
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
        int room_id = Integer.parseInt(jsr.getParameter("room_id"));
        if(room_id == -1) {
        	/** 新建一個JSONObject用於將回傳之資料進行封裝 */
        	JSONObject resp = new JSONObject();

        	/** 透過HotelHelper物件之getAll()方法取回所有旅程之資料，回傳之資料為JSONObject物件 */
            JSONObject query = sh.getByID(id);
            
            /** 新建一個JSONObject用於將回傳之資料進行封裝 */

            resp.put("status", "200");
            resp.put("message", "購物車資料取得成功");
            resp.put("response", query);
    
            /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            jsr.response(resp, response);
        }
        else {
        	/** 新建一個JSONObject用於將回傳之資料進行封裝 */
        	JSONObject resp = new JSONObject();

        	/** 透過HotelHelper物件之getAll()方法取回所有旅程之資料，回傳之資料為JSONObject物件 */
            JSONObject query = sh.deliver_to_checkout(id,room_id);
            
            /** 新建一個JSONObject用於將回傳之資料進行封裝 */

            resp.put("status", "200");
            resp.put("message", "購物車資料取得成功");
            resp.put("response", query);
    
            /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            jsr.response(resp, response);
        }
        
    }

    /**
     * 處理Http Method請求DELETE方法（刪除）
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
        
        /** 取出經解析到JSONObject之Request參數 */
        int customer_id = Integer.parseInt(jsr.getParameter("customer_id"));
        int room_id = Integer.parseInt(jsr.getParameter("room_id"));
        
        /** 透過CommunityHelper物件的deleteByID()方法至資料庫刪除該社群，回傳之資料為JSONObject物件 */
        JSONObject query = sh.deleteByID(customer_id,room_id);
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "此項目移除成功！");
        resp.put("response", query);

        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
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
        int room_id = jso.getInt("room_id");
        int order_number = jso.getInt("order_number");

        
        /** 建立一個新的shoppingcart物件 */
        ShoppingCart sc = new ShoppingCart(room_id, order_number);
        
        /** 透過RoomHelper物件的update()方法至資料庫更新該名會員資料，回傳之資料為JSONObject物件 */
        JSONObject data = sh.update(sc);
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "成功! 更新購物車內容...");
        resp.put("response", data);
        
        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
    }
}
