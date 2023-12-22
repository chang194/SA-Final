package app;
import org.json.*;

import java.sql.*;
import util.DBMgr;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class AttractionHelper<br>
 * AttractionHelper類別（class）主要管理所有與attrction相關與資料庫之方法
 * </p>
 * 
 * @author Winnie
 * @version 1.0.0
 * @since 1.0.0
 */

public class AttractionHelper {
    //靜態變數，儲存AttractionHelper物件
    private static AttractionHelper ah;
    //儲存JDBC資料庫連線
    private Connection conn = null;
    //儲存JDBC預準備之SQL指令
    private PreparedStatement pres = null;
    
    /**
     * 實例化（Instantiates）一個新的（new）MemberHelper物件<br>
     * 採用Singleton不需要透過new
     */
    private AttractionHelper(){}

    /**
     * 靜態方法<br>
     * 實作Singleton（單例模式），僅允許建立一個AttractionHelper物件
     *
     * @return the helper 回傳AttractionHelper物件
     */
    public static AttractionHelper getHelper(){
        if(ah == null){
            ah = new AttractionHelper();
        }
        return ah;
    }

    /**
     * 透過會員編號（ID）取得會員資料
     *
     * @param id 會員編號
     * @return the JSON object 回傳SQL執行結果與該會員編號之會員資料
     */
    public JSONObject getByID(String id){
        //新建一個Attraction物件之a變數，記錄每一位查詢之景點資料
        Attraction a = null;
        //用於儲存所有檢索回之景點，以JSONArray方式儲存
        JSONArray jsa = new JSONArray();
        //紀錄實際執行之SQL指令
        String execute_sql = "";
        //紀錄程式開始執行時間
        long start_time = System.nanoTime();
        //紀錄SQL總行數
        int row = 0;
        //儲存JDBC檢索資料庫後回傳之結果，以pointer方式移動到下一筆資料
        ResultSet rs = null;
        try{
            //取得資料庫連線
            conn = DBMgr.getConnection();
            //SQL指令
            String sql = "SELECT * FROM `mydb`.`tbl_Attraction` WHERE `attraction_id` = ? LIMIT 1";
            //將參數回填至SQL指令當中
            pres = conn.prepareStatement(sql);
            pres.setString(1, id);
            //執行查詢之SQL指令並紀錄其回傳之資料
            rs = pres.executeQuery();

            //紀錄真實執行的SQL指令，並印出
            execute_sql = pres.toString();
            System.out.println(execute_sql);

            //透過while迴圈移動pointer，取得每一筆回傳資料
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
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        }catch(Exception e){
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        }finally{
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs,pres,conn);
        }
        /** 紀錄程式結束執行時間 */
        long end_time = System.nanoTime();
        /** 紀錄程式執行時間 */
        long duration = (end_time - start_time);
        
        /** 將SQL指令、花費時間、影響行數與所有景點資料之JSONArray，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", execute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }
}