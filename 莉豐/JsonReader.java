package tools;
import javax.servlet.http.*; 
import java.io.*; 
import org.json.*;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class JsonReader<br>
 * JsonReader���O�]class�^�Ω�B�zHttp Rrequest�MHttp Rresponse����k
 * </p>
 * 
 * @author Winnie
 * @version 1.0.0
 * @since 1.0.0
 */

public class JsonReader {
    /** request�A�Ω��x�s��lHttp Request */
    private HttpServletRequest request;
    
    /** string�A�Ω��x�s�gŪ���ᤧRequest�r�� */
    private String request_string;

    /**
     * ��Ҥơ]Instantiates�^�@�ӷs���]new�^JSONReader����
     *
     * @param request Servlet Http Request����A����request
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public JsonReader(HttpServletRequest request) throws IOException, UnsupportedEncodingException {
        /** �]�w Request ���r��s�X��UTF-8�A�קK����r�X�{���~ */
        request.setCharacterEncoding("UTF-8");
        /** �x�s��lRequest������ */
        this.request = request;
        
        /** �إߤ@��StringBuilder�եX�r�� */
        StringBuilder sb = new StringBuilder();
        String s;
        
        /** 
         * �z�L while �j��v��Ū�����
         * �N��Ū�X��s�J�r���ܼ� s
         * �̲�append��StringBuilder��
         */
        while((s = request.getReader().readLine()) != null) sb.append(s);
        
        /** �NStringBuilder�ഫ��String�A�æL�X�Ӧr�� */
        this.request_string = sb.toString();
        System.out.println("[@JsonReader]" + this.request_string);
    }

    /**
     * ���oGET Request�����Ѽơ]�z�L���}�ǭȡ^
     *
     * @param key �ǤJ�n���o��key��
     * @return the parameter �^�Ǹ�key�Ȥ�value
     */
    public String getParameter(String key) {
        /** �P�_�� key �O�_�s�b��Request���A�Y���h�^�Ǹ� key ���x�s��value */
        if (this.request.getParameter(key) != null) return this.request.getParameter(key);
        /** �Y�L�h�^�ǪŦr�� */
        else return "";
    }

    /**
     * �N Http Request �ഫ�� JSONArray ������ARequest���r��̥~�h������ [] Array
     *
     * @return the array �^���ഫ����JSONArray
     */
    public JSONArray getArray() {
        /** 
         * �إߤ@��JSONArray����A�ΥH�ѪRRequest���r��
         * ��Request���r��̥~�h������ [] Array
         */
        JSONArray request_array = new JSONArray(this.request_string);
        
        /** �^��Request��Array�榡 */
        return request_array;
    }

    /**
     * �N Http Request �ഫ�� JSONObject ������ARequest���r��̥~�h������ {} Object
     *
     * @return the object �^���ഫ����JSONObject
     */
    public JSONObject getObject() {
        /** 
         * �إߤ@��JSONObject����A�ΥH�ѪRRequest���r��
         * ��Request���r��̥~�h������ {} Object
         */
        JSONObject request_object = new JSONObject(this.request_string);
        
        /** �^��Request��Object�榡 */
        return request_object;
    }

    /**
     * �N JSON �榡���r��ǤJ�A�N�B�z�����᪺��ƶi��Response�^�e�ݡA�r��̥~�򥲶��� {} Object
     * �ĥΦh���]overload�^��k�i��
     *
     * @param resp_string �UResponse��JSON�榡�r��
     * @param response Servlet�^�Ǥ�HttpServletResponse��Response����
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public void response(String resp_string, HttpServletResponse response) throws IOException, UnsupportedEncodingException {
        /** �]�wResponse���r���s�X */
        response.setCharacterEncoding("UTF-8");
        /** �]�wResponse��������� */
        response.setContentType("text/html; charset=UTF-8");
        /** �NJSON�榡���r���ഫ�� */
        JSONObject responseObj = new JSONObject(resp_string);
        /** ���oPrintWriter��Response�^�e�� */
        PrintWriter out = response.getWriter();
        /** �NResponse Object��J�A�^�ǫe�� */
        out.println(responseObj);
    }
    
    /**
     * �N JSON �榡��JSONObject�ǤJ�A�N�B�z�����᪺��ƶi��Response�^�e��
     * �ĥΦh���]overload�^��k�i��
     *
     * @param resp �UResponse��JSONObject Response������
     * @param response Servlet�^�Ǥ�HttpServletResponse��Response����
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public void response(JSONObject resp, HttpServletResponse response) throws IOException, UnsupportedEncodingException {
        /** �]�wResponse���r���s�X */
        response.setCharacterEncoding("UTF-8");
        /** �]�wResponse��������� */
        response.setContentType("text/html; charset=UTF-8");
        /** ���oPrintWriter��Response�^�e�� */
        PrintWriter out = response.getWriter();
        /** �NResponse Object��J�A�^�ǫe�� */
        out.println(resp);
    }
    
    /**
     * �N JSON �榡��JSONObject�ǤJ�A�N�B�z�����᪺��ƶi��Response�^�e��
     * �ĥΦh���]overload�^��k�i��
     *
     * @param status_code ���wHttp�����A�X
     * @param resp �UResponse��JSONObject Response������
     * @param response Servlet�^�Ǥ�HttpServletResponse��Response����
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public void response(int status_code, JSONObject resp, HttpServletResponse response) throws IOException, UnsupportedEncodingException {
        /** �]�wResponse���r���s�X */
        response.setCharacterEncoding("UTF-8");
        /** �]�wResponse��������� */
        response.setContentType("text/html; charset=UTF-8");
        /** �]�w���A�X */
        response.setStatus(status_code);
        /** ���oPrintWriter��Response�^�e�� */
        PrintWriter out = response.getWriter();
        /** �NResponse Object��J�A�^�ǫe�� */
        out.println(resp);
    }
}