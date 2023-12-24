package controller;

import java.io.*;
import java.time.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.*;
import app.HotelOwner;
import app.HotelOwnerHelper;
import tools.JsonReader;


@WebServlet("/api/hotelowner.do")
public class HotelOwnerController extends HttpServlet {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** hoh，HotelOwnerHelper之物件與hotelowner相關之資料庫方法（Sigleton） */
    private HotelOwnerHelper hoh =  HotelOwnerHelper.getHelper();
    
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
        String name = jso.getString("name");
        String email = jso.getString("email");
        String password = jso.getString("password");
        String intro = jso. getString("intro");
        //將JSON格式轉為LocalDate
        String birthdayString = jso.getString("birthday");
        LocalDate birthday = LocalDate.parse(birthdayString);
        
        /** 建立一個新的會員物件 */
        HotelOwner hotelowner = new HotelOwner(name, email, password, birthday, intro);
        
        /** 後端檢查是否有欄位為空值，若有則回傳錯誤訊息 */
        if(email.isEmpty() || password.isEmpty() || name.isEmpty()) {
            /** 以字串組出JSON格式之資料 */
            String resp = "{\"status\": \'400\', \"message\": \'欄位不能有空值\', \'response\': \'\'}";
            /** 透過JsonReader物件回傳到前端（以字串方式） */
            jsr.response(resp, response);
        }
        /** 透過MemberHelper物件的checkDuplicate()檢查該會員電子郵件信箱是否有重複 */
        else if (!hoh.checkDuplicate(hotelowner)) {
            /** 透過MemberHelper物件的create()方法新建一個會員至資料庫 */
            JSONObject data = hoh.create(hotelowner);
            
            /** 新建一個JSONObject用於將回傳之資料進行封裝 */
            JSONObject resp = new JSONObject();
            resp.put("status", "200");
            resp.put("message", "成功! 註冊會員資料...");
            resp.put("response", data);
            
            /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            jsr.response(resp, response);
        }
        else {
            /** 以字串組出JSON格式之資料 */
            String resp = "{\"status\": \'400\', \"message\": \'新增帳號失敗，此E-Mail帳號重複！\', \'response\': \'\'}";
            /** 透過JsonReader物件回傳到前端（以字串方式） */
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
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();

        ///如果id = -1表示要登入
        if(id == -1){
            String email = jsr.getParameter("email");
            String password = jsr.getParameter("password");

            int hotelowner_id = hoh.login(email, password);

            if (hotelowner_id != -1) {
                resp.put("status", "200");
                resp.put("message", "登入成功");
                JSONObject query = hoh.getByID(hotelowner_id);
                resp.put("response", query);
            } else {
                resp.put("status", "401");
                resp.put("message", "登入失败，使用者名稱或密碼不正確");
            }
        }
        else{
            /** 透過HotelOwnerHelper物件的getByID()方法自資料庫取回該名會員之資料，回傳之資料為JSONObject物件 */
            JSONObject query = hoh.getByID(id);
            
            resp.put("status", "200");
            resp.put("message", "會員資料取得成功");
            resp.put("response", query);
        }
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
        int id = jso.getInt("id");
        String name = jso.getString("name");
        String email = jso.getString("email");
        String password = jso.getString("password");
        //將JSON格式轉為LocalDate
        String birthdayString = jso.getString("birthday");
        LocalDate birthday = LocalDate.parse(birthdayString);
        String intro = jso.getString("intro");
        
        /** 透過傳入之參數，新建一個以這些參數之會員Member物件 */
        HotelOwner hotelowner = new HotelOwner(id, name, email, password, birthday, intro);
        
        /** 透過Member物件的update()方法至資料庫更新該名會員資料，回傳之資料為JSONObject物件 */
        JSONObject data = hoh.update(hotelowner);
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "成功! 更新會員資料...");
        resp.put("response", data);
        
        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
    }
}
