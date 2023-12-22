import org.json.*;
import java.sql.*;

public class AttractionHelper {
    private static AttractionHelper ah;
    private Connection conn = null;
    private PreparedStatement pres = null;

    public AttractionHelper(){}

    public static AttractionHelper getHelper(){
        if(ah == null){
            ah = new AttractionHelper();
        }
        return ah;
    }

    public JSONObject getByID(String id){
        Attraction a = null;
        JSONArray jsa = new JSONArray();
        String execute_sql = "";
        int row = 0;
        ResultSet rs = null;
        try{
            conn = DBMgr.getConnection();
            String sql = "SELECT * FROM `mydb`.`tbl_Attraction` WHERE `attraction_id` = ? LIMIT 1";
            pres = conn.prepareStatement(sql);
            pres.setString(1, id);
            rs = pres.executeQuery();

            execute_sql = pres.toString();
            System.out.println(execute_sql);

            while(rs.next()){
                row += 1;

                int attraction_id = rs.getInt("attraction_id");
                String attraction_name = rs.getString("attraction_name");
                String address = rs.getString("address");
                String business_hours = rs.getString("business_hours");
                String website = rs.getString("website");
                String rating = rs.getString("rating");
                String intro = rs.getString("intro");
                String image = rs.getString("image");

                a = new Attraction(attraction_id,attraction_name,address,business_hours,website,rating,intro,image);
                jsa.put(a.getData());
            }
        }catch(SQLException e){
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBMgr.close(rs,pres,conn);
        }
        JSONObject response = new JSONObject();
        response.put("sql",execute_sql);
        response.put("row",row);
        response.put("data",jsa);
        return response;
    }

    public JSONObject getAll(){
        Attraction a = null;
        JSONArray jsa = new JSONArray();
        String execute_sql = "";
        int row = 0;
        ResultSet rs = null;

        try{
            conn = DBMgr.getConnection();
            String sql = "SELECT * FROM `mydb`.`tbl_Attraction`";
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            execute_sql = pres.toString();
            System.out.println(execute_sql);
            while(rs.next()){
                row += 1;
                int attraction_id = rs.getInt("attraction_id");
                String attraction_name = rs.getString("attraction_name");
                String address = rs.getString("address");
                String business_hours = rs.getString("business_hours");
                String website = rs.getString("website");
                String rating = rs.getString("rating");
                String intro = rs.getString("intro");
                String image = rs.getString("image");

                a = new Attraction(attraction_id,attraction_name,address,business_hours,website,rating,intro,image);
                jsa.put(a.getData());
            }
        }catch(SQLException e){
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBMgr.close(rs,pres,conn);
        }
        JSONObject response = new JSONObject();
        response.put("sql",execute_sql);
        response.put("row",row);
        response.put("data",jsa);
        return response;
    }
}
