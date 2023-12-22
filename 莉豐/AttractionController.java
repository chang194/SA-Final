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
 * AttractionController���O�]class�^�D�n�Ω�B�zAttraction������Http�ШD�]Request�^�A�~��HttpServlet
 * </p>
 * 
 * @author Winnie
 * @version 1.0.0
 * @since 1.0.0
 */

public class AttractionController extends HttpServlet{
    //The Constant serialVersionUID.
    private static final long serialVersionUID = 1L;

    //ah�AMemberHelper������PMember��������Ʈw��k�]Sigleton)
    private app.AttractionHelper ah = app.AttractionHelper.getHelper();
    /**
     * �B�zHttp Method�ШDGET��k�]���o��ơ^
     *
     * @param request Servlet�ШD��HttpServletRequest��Request����]�e�ݨ��ݡ^
     * @param response Servlet�^�Ǥ�HttpServletResponse��Response����]��ݨ�e�ݡ^
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        /** �z�LJsonReader���O�NRequest��JSON�榡��ƸѪR�è��^ */
        JsonReader jsr = new JsonReader(request);
        /** �Y�����z�L�e��AJAX��data�Hkey=value���r��覡�i��ǻ��ѼơA�i�H�����Ѧ���k���^��� */
        String id = jsr.getParameter("id");
        
        /** �z�LAttractionHelper����getByID()��k�۸�Ʈw���^�Ӵ��I����ơA�^�Ǥ���Ƭ�JSONObject���� */
        JSONObject query = ah.getByID(id);
            
        /** �s�ؤ@��JSONObject�Ω�N�^�Ǥ���ƶi��ʸ� */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "���I��ƨ��o���\");
        resp.put("response", query);
    
        /** �z�LJsonReader����^�Ǩ�e�ݡ]�HJSONObject�覡�^ */
        jsr.response(resp, response);
    }
}
