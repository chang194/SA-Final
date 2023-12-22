import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import java.io.IOException;

import javax.servlet.http.*;
import org.json.*;


public class JsonReader{
    private HttpServletRequest request;
    private String request_string;

    /**
     * 實例化（Instantiates）一個新的（new）JSONReader物件
     *
     * @param request Servlet Http Request物件，紀錄request
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public JsonReader(HttpServletRequest request) throws IOException, UnsupportedEncodingException{
        request.setCharacterEncoding("UTF-8");
        this.request = request;

        StringBuilder sb = new StringBuilder();
        String s;
        
        while((s = request.getReader().readLine()) != null){
            sb.append(s);
        }
        this.request_string = sb.toString();
        System.out.println("[@JsonReader]" + this.request_string);
    }

    //get parameter of Request
    public String getParameter(String key){
        if(this.request.getParameter(key) != null){
            return this.request.getParameter(key);
        }
        else{
            return "";
        }
    }

    public JSONArray getArray(){
        JSONArray request_array = new JSONArray(this.request_string);
        return request_array;
    }

    public JSONObject getObject() {
        JSONObject request_object = new JSONObject(this.request_string);
        return request_object;
    }
    
}
