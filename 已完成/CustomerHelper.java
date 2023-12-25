package app;

import java.sql.*;
import java.time.*;
import org.json.*;

import util.DBMgr;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class CustomerHelper<br>
 * CustomerHelper類別（class）主要管理所有與Customer相關與資料庫之方法（method）
 * </p>
 * 
 * @author Alex
 * @version 1.0.0
 * @since 1.0.0
 */

public class CustomerHelper {
    
    /**
     * 實例化（Instantiates）一個新的（new）CustomerHelper物件<br>
     * 採用Singleton不需要透過new
     */
    private CustomerHelper() {
        
    }
    
    /** 靜態變數，儲存CustomerHelper物件 */
    private static CustomerHelper ch;
    
    /** 儲存JDBC資料庫連線 */
    private Connection conn = null;
    
    /** 儲存JDBC預準備之SQL指令 */
    private PreparedStatement pres = null;
    
    /**
     * 靜態方法<br>
     * 實作Singleton（單例模式），僅允許建立一個CustomerHelper物件
     *
     * @return the helper 回傳CustomerHelper物件
     */
    public static CustomerHelper getHelper() {
        /** Singleton檢查是否已經有CustomerHelper物件，若無則new一個，若有則直接回傳 */
        if(ch == null) ch = new CustomerHelper();
        
        return ch;
    } 
    
    /**
     * 透過會員編號（ID）取得會員資料
     *
     * @param id 會員編號
     * @return the JSON object 回傳SQL執行結果與該會員編號之會員資料
     */
    public JSONObject getByID(int id) {
        /** 新建一個 Customer 物件之 cus 變數，用於紀錄每一位查詢回之會員資料 */
        Customer cus = null;
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
            String sql = "SELECT * FROM `mydb`.`tbl_Customer` WHERE `customer_id` = ? LIMIT 1";
            
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
                int customer_id = rs.getInt("customer_id");
                String name = rs.getString("customer_name");
                String email = rs.getString("customer_email");
                String password = rs.getString("customer_password");
                Date modified_time = rs.getDate("modified_time");
                java.sql.Date sqlDate = rs.getDate("birthday");
                LocalDate birthday = sqlDate.toLocalDate(); //將sql DATE轉為java LocalDate
                String intro = rs.getString("intro");
                int customer_point = rs.getInt("customer_point");
                
                /** 將每一筆會員資料產生一名新Customer物件 */
                cus = new Customer(customer_id, name, email, password, birthday, intro, customer_point, modified_time);
                /** 取出該名會員之資料並封裝至 JSONsonArray 內 */
                jsa.put(cus.getData());
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
     * @param cus 一名會員之Customer物件
     * @return boolean 若重複註冊回傳False，若該信箱不存在則回傳True
     */
    public boolean checkDuplicate(Customer cus){
        /** 紀錄SQL總行數，若為「-1」代表資料庫檢索尚未完成 */
        int row = -1;
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT count(*) FROM `mydb`.`tbl_Customer` WHERE `customer_email` = ?";
            
            /** 取得所需之參數 */
            String email = cus.getEmail();
            
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
     * @param cus 一名會員之Customer物件
     * @return the JSONObject 回傳SQL指令執行之結果
     */
    public JSONObject create(Customer cus) {
    	
    	
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
            String sql = "INSERT INTO `mydb`.`tbl_Customer`(`customer_name`, `customer_email`, `customer_password`, `modified_time`, `birthday`, `intro`, `customer_point`)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?)";
            
            /** 取得所需之參數 */
            String name = cus.getName();
            String email = cus.getEmail();
            String password = cus.getPassword();
            Date birthday = java.sql.Date.valueOf(cus.getBirthday());
            String intro = cus.getIntro();
            int customer_point = cus.getCustomer_point();
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, name);
            pres.setString(2, email);
            pres.setString(3, password);
            pres.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            pres.setDate(5, birthday);
            pres.setString(6, intro);
            pres.setInt(7, customer_point);
            
            
            
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
     * @param cus 一名會員之Customer物件
     * @return the JSONObject 回傳SQL指令執行結果與執行之資料
     */
    public JSONObject update(Customer cus) {
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
            String sql = "Update `mydb`.`tbl_Customer` SET `customer_name` = ? ,`customer_password` = ? , `modified_time` = ?, `intro` = ? WHERE `customer_email` = ?";
            /** 取得所需之參數 */
            String name = cus.getName();
            String password = cus.getPassword();
            String intro = cus.getIntro();
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, name);
            pres.setString(2, password);
            pres.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pres.setString(4, intro);
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
     * @return int 若登入成功則回傳customer_id，若查無此帳號則回傳-1，若密碼錯誤則回傳-2
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
            String sql = "SELECT customer_id, customer_password FROM `mydb`.`tbl_Customer` WHERE customer_email = ?";

            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, email);

            // 執行 SQL 查詢
            rs = pres.executeQuery(); 
                if (rs.next()) {
                    // 如果有符合條件的資料，比對密碼
                    String storedPassword = rs.getString("customer_password");
                    if (password.equals(storedPassword)) {
                        // 登入成功，回傳 customer_id
                        resultCode = rs.getInt("customer_id");
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

    /**
     * 追蹤其他使用者
     *
     * @param follower_id 追蹤者的customer_id
     * @param customer_Id 被追蹤者的custome_id
     * @return the JSONObject 回傳SQL指令執行結果與執行之資料
     */
    public JSONObject follow(int follower_id, int customer_id){
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
            String sql = "INSERT INTO `mydb`.`tbl_Customer`(`follower_id`, `customer_id`) VALUES (?, ?)";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, follower_id);
            pres.setInt(2, customer_id);
            
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
     * 新增一個旅程或社群至喜愛清單
     *
     * @param customer_id 使用者的customer_id
     * @param journey_id 若要新增旅程則為journey_id，否則為-1
     * @param community_id 若要新增社群則為community_id，否則為-1
     * @return the JSONObject 回傳SQL指令執行結果與執行之資料
     */
    public JSONObject addFavorite(int customer_id, int journey_id, int community_id){
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
            String sql = "INSERT INTO `mydb`.`tbl_FavoriteList` (`customer_id`, `journey_id`, `community_id`) VALUES (?, ?, ?)";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, customer_id);
            pres.setInt(2, journey_id);
            pres.setInt(3, community_id);
            
            
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
     * 從喜愛清單刪除一個旅程或社群
     *
     * @param customer_id 使用者的customer_id
     * @param journey_id 若要刪除旅程則為journey_id，否則為-1
     * @param community_id 若要刪除社群則為community_id，否則為-1
     * @return the JSONObject 回傳SQL指令執行結果與執行之資料
     */
    public JSONObject deleteFavorite(int customer_id, int journey_id, int community_id){
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
            String sql = "DELETE FROM `mydb`.`tbl_FavoriteList` WHERE `customer_id` = ? AND ";
            
            if (journey_id != -1) {
                sql += "`journey_id` = ?";
            } else if (community_id != -1) {
                sql += "`community_id` = ?";
            } else {
                // 如果 journey_id 和 community_id 同時為 -1，表示未指定要刪除的是旅程還是社群
                JSONObject response = new JSONObject();
                response.put("status", "fail");
                response.put("message", "Please specify either journey_id or community_id for deletion.");
                return response;
            }

            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, customer_id);
            pres.setInt(2, journey_id != -1 ? journey_id : community_id);
            
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
}