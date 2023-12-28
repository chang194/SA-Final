package app;

import java.sql.*;
import org.json.*;

import util.DBMgr;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class ShoppingCartHelper<br>
 * ShoppingCartHelper類別（class）主要管理所有與shoppingcart相關與資料庫之方法（method）
 * </p>
 * 
 * @author Winnie
 * @version 1.0.0
 * @since 1.0.0
 */

 public class ShoppingCartHelper{
    /**
     * 實例化（Instantiates）一個新的（new）CustomerHelper物件<br>
     * 採用Singleton不需要透過new
     */
    private ShoppingCartHelper() {
        
    }
    /** 靜態變數，儲存ShoppingCartHelper物件 */
    private static ShoppingCartHelper sh;
    
    
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
    public static ShoppingCartHelper getHelper() {
        /** Singleton檢查是否已經有ShoppingCartHelper物件，若無則new一個，若有則直接回傳 */
        if(sh == null) sh = new ShoppingCartHelper();
        
        return sh;
    }

    /**
     * 透過會員編號（ID）取得購物車資料
     *
     * @param id 會員編號
     * @return the JSON object 回傳SQL執行結果與該會員編號之購物車資料
     */
    public JSONObject getByID(int id) {
        ShoppingCart sc = null;
        /** 用於儲存所有檢索回之會員，以JSONArray方式儲存 */
        JSONArray jsa = new JSONArray();
        /** 記錄實際執行之SQL指令 */
        String execute_sql = "";
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
            String sql = "SELECT * FROM `mydb`.`tbl_shoppingcart` WHERE `customer_id` = ?";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, id);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            
            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
            /** 正確來說資料庫只會有一筆該會員編號之資料，因此其實可以不用使用 while 迴圈 */
            while(rs.next()) {
                /** 每執行一次迴圈表示有一筆資料 */
                row += 1;
                int room_id = rs.getInt("room_id");
                

                ResultSet rs_room = null;
                ResultSet rs_hotel = null;
                //查詢room屬於哪個hotel，抓hotel_id
                String sql_id = "SELECT * FROM `mydb`.`tbl_Room` WHERE `room_id` = ?";
                
                /** 將參數回填至SQL指令當中 */
                pres = conn.prepareStatement(sql_id);
                pres.setInt(1, room_id);
                /** 執行查詢之SQL指令並記錄其回傳之資料 */
                rs_room = pres.executeQuery();
                while(rs_room.next()) {
                	//查詢hotel的name
                    String sql_name = "SELECT * FROM `mydb`.`tbl_Hotel` WHERE `hotel_id` = ?";

                    int hotel_id = rs_room.getInt("hotel_id");
                    
                    /** 將參數回填至SQL指令當中 */
                    pres = conn.prepareStatement(sql_name);
                    pres.setInt(1, hotel_id);
                    /** 執行查詢之SQL指令並記錄其回傳之資料 */
                    rs_hotel = pres.executeQuery();
                    while(rs_hotel.next()) {
                    	// 使用 getByID 方法取得房型資料
                        String hotelname = rs_hotel.getString("hotel_name");
                        int order_number = rs.getInt("order_number");
                        int room_price = rs_room.getInt("room_price");
                        String room_type = rs_room.getString("room_type");
                        String image = rs_room.getString("room_image");

                        sc = new ShoppingCart(room_id,order_number,hotelname,room_price,room_type,image,hotel_id);
                        /** 取出該名會員之資料並封裝至 JSONsonArray 內 */
                    }
                }
                jsa.put(sc.getData());
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
        response.put("sql", execute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }
    
    /**
     * 透過會員、房間編號（ID）取得購物車資料，並傳入訂單
     *
     * @param id 會員編號
     * @param room_id 房間編號
     * @return the JSON object 回傳SQL執行結果與該會員編號之訂單資料
     */
    public JSONObject deliver_to_checkout(int customer_id,int room_id) {
    	ShoppingCart sc = null;
        /** 用於儲存所有檢索回之會員，以JSONArray方式儲存 */
        JSONArray jsa = new JSONArray();
        /** 記錄實際執行之SQL指令 */
        String execute_sql = "";
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
            String sql = "SELECT * FROM `mydb`.`tbl_shoppingcart` WHERE `customer_id` = ? AND `room_id` = ?";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, customer_id);
            pres.setInt(2, room_id);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            
            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
            /** 正確來說資料庫只會有一筆該會員編號之資料，因此其實可以不用使用 while 迴圈 */
            while(rs.next()) {
                /** 每執行一次迴圈表示有一筆資料 */
                row += 1;
                
                ResultSet rs_room = null;
                ResultSet rs_hotel = null;
                ResultSet rs_point = null;
              //查詢member有多少點數
                String sql_point = "SELECT * FROM `mydb`.`tbl_Customer` WHERE `customer_id` = ?";
                /** 將參數回填至SQL指令當中 */
                pres = conn.prepareStatement(sql_point);
                pres.setInt(1, customer_id);
                /** 執行查詢之SQL指令並記錄其回傳之資料 */
                rs_point = pres.executeQuery();
                int customer_point = 0;
                while(rs_point.next()) {
                	customer_point = rs_point.getInt("customer_point");
                }
                
                
                //查詢room屬於哪個hotel，抓hotel_id
                String sql_id = "SELECT * FROM `mydb`.`tbl_Room` WHERE `room_id` = ?";
                /** 將參數回填至SQL指令當中 */
                pres = conn.prepareStatement(sql_id);
                pres.setInt(1, room_id);
                /** 執行查詢之SQL指令並記錄其回傳之資料 */
                rs_room = pres.executeQuery();
                while(rs_room.next()) {
                	//查詢hotel的name
                    String sql_name = "SELECT * FROM `mydb`.`tbl_Hotel` WHERE `hotel_id` = ?";

                    int hotel_id = rs_room.getInt("hotel_id");
                    
                    
                    /** 將參數回填至SQL指令當中 */
                    pres = conn.prepareStatement(sql_name);
                    pres.setInt(1, hotel_id);
                    /** 執行查詢之SQL指令並記錄其回傳之資料 */
                    rs_hotel = pres.executeQuery();
                    while(rs_hotel.next()) {
                    	// 使用 getByID 方法取得房型資料
                        String hotelname = rs_hotel.getString("hotel_name");
                        int order_number = rs.getInt("order_number");
                        int room_price = rs_room.getInt("room_price");
                        String room_type = rs_room.getString("room_type");
                        String image = rs_room.getString("room_image");
                        String location = rs_hotel.getString("hotel_location");
                        

                        sc = new ShoppingCart(customer_id,room_id,order_number,hotelname,room_price,room_type,image,hotel_id,location,customer_point);
                        /** 取出該名會員之資料並封裝至 JSONsonArray 內 */
                    }
                }
                jsa.put(sc.getData());
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
        response.put("sql", execute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }

    /**
     * 建立該購物車內容至資料庫
     *
     * @param shoppingcart 一個購物車之shoppingcart物件
     * @return the JSONObject 回傳SQL指令執行之結果
     */
    public JSONObject create(ShoppingCart sc) {
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
            String sql = "INSERT INTO `mydb`.`tbl_shoppingcart`(`customer_id`, `room_id`, `order_number`)"
                    + " VALUES(?, ?, ?)";
            
            /** 取得所需之參數 */
            int customer_id = sc.getCustomerId();
            int room_id = sc.getRoomId();
            int order_number = sc.getOrderNumber();
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pres.setInt(1, customer_id);
            pres.setInt(2,room_id);
            pres.setInt(3, order_number);
          
            
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
     * 檢查購物車裡面是否已經有資料
     *
     * @param shoppingcart 一個購物車之shoppingcart物件
     * @return the JSONObject 回傳SQL指令執行結果與執行之資料
     */
    public int checkData(ShoppingCart sc) {
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        int num = -1;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT order_number FROM `mydb`.`tbl_shoppingcart` WHERE `customer_id` = ? AND room_id = ? LIMIT 1";
            
            /** 取得所需之參數 */
            int customer_id = sc.getCustomerId();
            int room_id = sc.getRoomId();            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, customer_id);
            pres.setInt(2, room_id);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();
            while(rs.next()) {
            	int order_number = rs.getInt("order_number");
            	
            	if(order_number > 0) {
            		num = order_number;
            	}
            	else {
            		num = -1;
            	}
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

        return num;
    }
    
    /**
     * 更新一個購物車之購物車資料
     *
     * @param shoppingcart 一個購物車之shoppingcart物件
     * @return the JSONObject 回傳SQL指令執行結果與執行之資料
     */
    public JSONObject update(ShoppingCart sc) {
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
            String sql = "Update `mydb`.`tbl_shoppingcart` SET `order_number` = ? WHERE `room_id` = ?";
            /** 取得所需之參數 */
            int room_id = sc.getRoomId();
            int order_number = sc.getOrderNumber();
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, order_number);
            pres.setInt(2, room_id);

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
     * 透過room_id,customer_id編號（ID）刪除購物車
     *
     * @param customer_id 房客編號
     * @param room_id 房型編號
     * @return the JSONObject 回傳SQL執行結果
     */
    public JSONObject deleteByID(int customer_id,int room_id) {
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
            String sql = "DELETE FROM `mydb`.`tbl_shoppingcart` WHERE `customer_id` = ? AND `room_id` = ? LIMIT 1";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, customer_id);
            pres.setInt(2,room_id);
            /** 執行刪除之SQL指令並記錄影響之行數 */
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
            DBMgr.close(rs, pres, conn);
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

        return response;
    }
 }