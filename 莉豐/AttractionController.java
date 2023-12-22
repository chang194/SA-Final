package controller;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import tools.JsonReader;

/**
 * <p>
 * The Class AttractionController<br>
 * AttractionController類別（class）主要用於處理Attraction相關之Http請求（Request），繼承HttpServlet
 * </p>
 * 
 * @author Winnie
 * @version 1.0.0
 * @since 1.0.0
 */

public class AttractionController extends HttpServlet{
    //The Constant serialVersionUID.
    private static final long serialVersionUID = 1L;

    //ah，MemberHelper之物件與Member相關之資料庫方法（Sigleton)
    private app.AttractionHelper ah = app.AttractionHelper.getHelper();
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
        String id = jsr.getParameter("id");
        
        /** 透過AttractionHelper物件的getByID()方法自資料庫取回該景點之資料，回傳之資料為JSONObject物件 */
        JSONObject query = ah.getByID(id);
            
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "景點資料取得成功");
        resp.put("response", query);
    
        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
    }
}
