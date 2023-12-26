package app;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.*;
import org.json.*;

import util.DBMgr;

public class HotelOwnerHelper {

    /**
     * 實例化（Instantiates）一個新的（new）HotelOwnerHelper物件<br>
     * 採用Singleton不需要透過new
     */
    private HotelOwnerHelper() {
        
    }
    
    /** 靜態變數，儲存HotelOwnerHelper物件 */
    private static HotelOwnerHelper hoh;
    
    /** 儲存JDBC資料庫連線 */
    private Connection conn = null;
    
    /** 儲存JDBC預準備之SQL指令 */
    private PreparedStatement pres = null;
    
    /**
     * 靜態方法<br>
     * 實作Singleton（單例模式），僅允許建立一個HotelOwnerHelper物件
     *
     * @return the helper 回傳HotelOwnerHelper物件
     */
    public static HotelOwnerHelper getHelper() {
        /** Singleton檢查是否已經有HotelOwnerHelper物件，若無則new一個，若有則直接回傳 */
        if(hoh == null) hoh = new HotelOwnerHelper();
        
        return hoh;
    }

    /**
     * 透過會員編號（ID）取得會員資料
     *
     * @param id 會員編號
     * @return the JSON object 回傳SQL執行結果與該會員編號之會員資料
     */
    public JSONObject getByID(int id) {
        /** 新建一個 HotelOwner 物件之 HotelOwner 變數，用於紀錄每一位查詢回之會員資料 */
        HotelOwner hotelowner = null;
        /** 用於儲存所有檢索回之會員，以JSONArray方式儲存 */
        JSONArray jsa = new JSONArray();
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 紀錄程式開始執行時間 */
        long start_time = System.nanoTime();
        /** 紀錄SQL總行數 */
        int row = 0;
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT * FROM `mydb`.`tbl_Hotelowner` WHERE `hotelowner_id` = ? LIMIT 1";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, id);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
            /** 正確來說資料庫只會有一筆該會員編號之資料，因此其實可以不用使用 while 迴圈 */
            while(rs.next()) {
                /** 每執行一次迴圈表示有一筆資料 */
                row += 1;
                
                /** 將 ResultSet 之資料取出 */
                int hotelowner_id = rs.getInt("hotelowner_id");
                String name = rs.getString("hotelowner_name");
                String email = rs.getString("hotelowner_email");
                String password = rs.getString("hotelowner_password");
                
                Timestamp modifiedTime = rs.getTimestamp("modified_time");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // 使用 SimpleDateFormat 將 Timestamp 轉換為 String
                String modified_time = dateFormat.format(modifiedTime);
                
                java.sql.Date sqlDate = rs.getDate("birthday");
                LocalDate birthday = sqlDate.toLocalDate(); //將sql DATE轉為java LocalDate
                String intro = rs.getString("intro");
                int hotel_id = rs.getInt("hotel_id");
                
                /** 將每一筆會員資料產生一名新hotelowner物件 */
                hotelowner = new HotelOwner(hotelowner_id, name, email, password, birthday, intro, hotel_id, modified_time);
                /** 取出該名會員之資料並封裝至 JSONsonArray 內 */
                jsa.put(hotelowner.getData());
            }
            
        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }
        
        /** 紀錄程式結束執行時間 */
        long end_time = System.nanoTime();
        /** 紀錄程式執行時間 */
        long duration = (end_time - start_time);
        
        /** 將SQL指令、花費時間、影響行數與所有會員資料之JSONArray，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }
    
    /**
     * 檢查該名會員之電子郵件信箱是否重複註冊
     *
     * @param hotelowner 一名會員之HotelOwner物件
     * @return boolean 若重複註冊回傳False，若該信箱不存在則回傳True
     */
    public boolean checkDuplicate(HotelOwner hotelowner){
        /** 紀錄SQL總行數，若為「-1」代表資料庫檢索尚未完成 */
        int row = -1;
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT count(*) FROM `mydb`.`tbl_Hotelowner` WHERE `hotelowner_email` = ?";
            
            /** 取得所需之參數 */
            String email = hotelowner.getEmail();
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, email);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 讓指標移往最後一列，取得目前有幾行在資料庫內 */
            rs.next();
            row = rs.getInt("count(*)");
            System.out.print(row);

        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }
        
        /** 
         * 判斷是否已經有一筆該電子郵件信箱之資料
         * 若無一筆則回傳False，否則回傳True 
         */
        return (row == 0) ? false : true;
    }
    
    /**
     * 建立該名會員至資料庫
     *
     * @param hotelowner 一名會員之HotelOwner物件
     * @return the JSONObject 回傳SQL指令執行之結果
     */
    public JSONObject create(HotelOwner hotelowner) {
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 紀錄程式開始執行時間 */
        long start_time = System.nanoTime();
        /** 紀錄SQL總行數 */
        int row = 0;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "INSERT INTO `mydb`.`tbl_Hotelowner`(`hotelowner_name`, `hotelowner_email`, `hotelowner_password`, `modified_time`, `birthday`, `intro`)"
                    + " VALUES(?, ?, ?, ?, ?, ?)";
            
            /** 取得所需之參數 */
            String name = hotelowner.getName();
            String email = hotelowner.getEmail();
            String password = hotelowner.getPassword();
            LocalDate nextDay = hotelowner.getBirthday().plusDays(1);
            Date birthday = java.sql.Date.valueOf(nextDay);
            String intro = hotelowner.getIntro();
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, name);
            pres.setString(2, email);
            pres.setString(3, password);
            pres.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            pres.setDate(5, birthday);
            pres.setString(6, intro);
            
            /** 執行新增之SQL指令並記錄影響之行數 */
            row = pres.executeUpdate();
            
            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(pres, conn);
        }

        /** 紀錄程式結束執行時間 */
        long end_time = System.nanoTime();
        /** 紀錄程式執行時間 */
        long duration = (end_time - start_time);

        /** 將SQL指令、花費時間與影響行數，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("time", duration);
        response.put("row", row);
        
        return response;
    }
    
    /**
     * 更新一名會員之會員資料
     *
     * @param hotelowner 一名會員之HotelOwner物件
     * @return the JSONObject 回傳SQL指令執行結果與執行之資料
     */
    public JSONObject update(HotelOwner hotelowner) {
        /** 紀錄回傳之資料 */
        JSONArray jsa = new JSONArray();
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 紀錄程式開始執行時間 */
        long start_time = System.nanoTime();
        /** 紀錄SQL總行數 */
        int row = 0;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "Update `mydb`.`tbl_Hotelowner` SET `hotelowner_name` = ? ,`hotelowner_password` = ? , `modified_time` = ?, `intro` = ? WHERE `hotelowner_id` = ?";
            /** 取得所需之參數 */
            int id = hotelowner.getHotelId();
            String name = hotelowner.getName();
            String password = hotelowner.getPassword();
            String intro = hotelowner.getIntro();
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, name);
            pres.setString(2, password);
            pres.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pres.setString(4, intro);
            pres.setInt(5, id);
            /** 執行更新之SQL指令並記錄影響之行數 */
            row = pres.executeUpdate();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);

        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(pres, conn);
        }
        
        /** 紀錄程式結束執行時間 */
        long end_time = System.nanoTime();
        /** 紀錄程式執行時間 */
        long duration = (end_time - start_time);
        
        /** 將SQL指令、花費時間與影響行數，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }

    /**
     * 判斷是否成功登入
     *
     * @param email 使用者輸入的email (String)
     * @param password 使用者輸入的password (String)
     * @return int 若登入成功則回傳hotelowner_id，若查無此帳號則回傳-1，若密碼錯誤則回傳-2
     */
    public int login(String email, String password){
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        // 初始化回傳值
        int resultCode = -1;
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT hotelowner_id, hotelowner_password FROM `mydb`.`tbl_Hotelowner` WHERE hotelowner_email = ?";

            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, email);

            // 執行 SQL 查詢
            rs = pres.executeQuery(); 
                if (rs.next()) {
                    // 如果有符合條件的資料，比對密碼
                    String storedPassword = rs.getString("hotelowner_password");
                    if (password.equals(storedPassword)) {
                        // 登入成功，回傳 customer_id
                        resultCode = rs.getInt("hotelowner_id");
                    } else {
                        // 密碼錯誤，回傳 -2
                        resultCode = -2;
                    }
                } else {                        
                    // 查無此帳號，回傳 -1
                    resultCode = -1;
                }
                
        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(pres, conn);
        }
        
        return resultCode;
    }

}
