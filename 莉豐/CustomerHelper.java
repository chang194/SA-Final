import java.sql.*;

import javax.print.attribute.standard.JobKOctets;

import org.json.JSONObject;

public class CustomerHelper {
    private static CustomerHelper ch;
    private Connection conn = null;
    private PreparedStatement pres = null;

    public CustomerHelper(){}

    public static CustomerHelper getHelper(){
        if(ch == null){
            ch = new CustomerHelper();
        }
        return ch;
    }
    
    public JSONObject getByID(int id){
        Customer c = null;
        
        JSONObject response = new JSONObject();
        return response;
    }
    

}
