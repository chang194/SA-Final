package app;

import java.sql.*;
import java.time.*;

import org.json.JSONArray;
import org.json.JSONObject;

import util.DBMgr;

public class OrderHelper {

    private OrderHelper() {
        
    }

    private static OrderHelper oh;
    private Connection conn = null;
    private PreparedStatement pres = null;
    
    public static OrderHelper getHelper() {
        /** Singleton檢查是否已經有OrderHelper物件，若無則new一個，若有則直接回傳 */
        if(oh == null) oh = new OrderHelper();
        
        return oh;
    }

    /**
     * 透過訂單id取得訂單資料
     *
     * @param id 訂單id
     * @return the JSON object 回傳SQL執行結果與該訂單id之訂單資料
     */
    public JSONObject getByOrderID(int id){
        /** 新建一個 Order 物件之 o 變數，用於紀錄每一位查詢回之旅店資料 */
        Order o = null;
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
            String sql = "SELECT * FROM `mydb`.`tbl_Order` WHERE `order_id` = ? LIMIT 1";
            
            /** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, id);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
            while(rs.next()) {
                /** 將 ResultSet 之資料取出 */
	            int room_id = rs.getInt("room_id");
	            int customer_id = rs.getInt("customer_id");
	            int order_number = rs.getInt("order_number");
	            int order_price = rs.getInt("order_price");
	            int guest_number = rs.getInt("guest_number");
                java.sql.Date sqlDate = rs.getDate("booking_date");
                LocalDate booking_date = sqlDate.toLocalDate();
                sqlDate = rs.getDate("checkin_date");
                LocalDate checkin_date = sqlDate.toLocalDate();
                sqlDate = rs.getDate("checkout_date");
	            LocalDate checkout_date = sqlDate.toLocalDate();
                
                /** 將旅店資料產生一個新Order物件 */
                o = new Order(id, room_id, customer_id, order_number, order_price, guest_number, booking_date, checkin_date, checkout_date);
                jsa.put(o.getData());
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
        
        /** 將SQL指令、花費時間、影響行數與所有訂單資料之JSONArray，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }

    /**
     * 透過hotel id取得訂單資料
     *
     * @param id hotel_id
     * @return the JSON object 回傳SQL執行結果與該Hotel id之訂單資料
     */
    public JSONObject getByHotelID(int hotel_id) {
        JSONArray jsa = new JSONArray();
        String execute_sql = "";
        long start_time = System.nanoTime();
        int row = 0;
        ResultSet rsRoom = null;
        ResultSet rsOrder = null;
        ResultSet rsCustomer = null;
    
        try {
            conn = DBMgr.getConnection();
            // Step 1: Query room_id based on hotel_id
            String roomSql = "SELECT * FROM `mydb`.`tbl_room` WHERE `hotel_id` = ?";
            pres = conn.prepareStatement(roomSql);
            pres.setInt(1, hotel_id);
            rsRoom = pres.executeQuery();
            execute_sql = pres.toString();
    
            while (rsRoom.next()) {
                int room_id = rsRoom.getInt("room_id");
                String room_type = rsRoom.getString("room_type");
                
                // Step 2: Query order data based on room_id
                String orderSql = "SELECT * FROM `mydb`.`tbl_Order` WHERE `room_id` = ?";
                pres = conn.prepareStatement(orderSql);
                pres.setInt(1, room_id);
                rsOrder = pres.executeQuery();
    
                while (rsOrder.next()) {
                	JSONObject orderdetail = new JSONObject();
                	
                    int order_id = rsOrder.getInt("order_id");
                    int customer_id = rsOrder.getInt("customer_id");
                    int order_number = rsOrder.getInt("order_number");
                    int order_price = rsOrder.getInt("order_price");
                    int guest_number = rsOrder.getInt("guest_number");
                    java.sql.Date sqlDate = rsOrder.getDate("booking_date");
                    LocalDate booking_date = sqlDate.toLocalDate();
                    sqlDate = rsOrder.getDate("checkin_date");
                    LocalDate checkin_date = sqlDate.toLocalDate();
                    sqlDate = rsOrder.getDate("checkout_date");
                    LocalDate checkout_date = sqlDate.toLocalDate();
                    
                    orderdetail.put("room_type", room_type);
                    orderdetail.put("order_id", order_id);
                    orderdetail.put("order_number", order_number);
                    orderdetail.put("booking_date", booking_date);
                    orderdetail.put("checkin_date", checkin_date);
    
                    String customerSql = "SELECT * FROM `mydb`.`tbl_customer` WHERE `customer_id` = ?";
                    pres = conn.prepareStatement(customerSql);
                    pres.setInt(1, customer_id);
                    rsCustomer = pres.executeQuery();
                    
                    if(rsCustomer.next()) {
                    	String customer_name = rsCustomer.getString("customer_name");
                    	orderdetail.put("customer_name", customer_name);
                    	jsa.put(orderdetail);
                    }
                    row++;
                }
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBMgr.close(rsOrder, null, null); // Close rsOrder
            DBMgr.close(rsRoom, pres, conn);
        }
    
        long end_time = System.nanoTime();
        long duration = (end_time - start_time);
    
        JSONObject response = new JSONObject();
        response.put("sql", execute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);
    
        return response;
    }
    
    /**
     * 透過customer_id取得該使用者所有訂單資料
     *
     * @param id customer_id
     * @return the JSON object 回傳SQL執行結果與該使用者所有訂單資料
     */
    public JSONObject getByCustomerID(int id){
        /** 新建一個 Order 物件之 o 變數，用於紀錄每一筆訂單資料 */
        Order o = null;
        /** 用於儲存所有檢索回之訂單，以JSONArray方式儲存 */
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
            String sql = "SELECT * FROM `mydb`.`tbl_Order` WHERE customer_id = ?";
            
            // 使用 PreparedStatement 預防 SQL 注入
            pres = conn.prepareStatement(sql);
            pres.setInt(1, id);

            // 執行查詢
            rs = pres.executeQuery();

            // 逐一處理查詢結果
            while (rs.next()) {
                int order_id = rs.getInt("order_id");

                // 使用 getByOrderID 方法取得訂單資料
                JSONObject orderData = getByOrderID(order_id);

                // 將房型資料放入結果 JSON 物件
                jsa.put(orderData);
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
        
        /** 將SQL指令、花費時間、影響行數與所有房型資料之JSONArray，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }
    
    /**
     * 結帳並新增該訂單資料
     *
     * @param o 訂單物件
     * @param use_point 是否使用會員點數
     * @return the JSON object 回傳SQL執行結果
     */
    public JSONObject checkout(Order o, boolean use_point){
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
            String sql = "INSERT INTO `mydb`.`tbl_Order`(`room_id`, `customer_id`, `order_number`, `order_price`, `guest_number`, `booking_date`, checkin_date, checkout_date)"
                    + " VALUES(?, ?, ?, ?, ?, ?)";
            
            /** 取得所需之參數 */
            int room_id = o.getRoom_id();
	        int customer_id = o.getCustomerId();
	        int order_number = o.getOrder_number(); 
	        int order_price = o.getOrder_price();
	        int number_of_guest = o.getNumber_of_guest();
            LocalDate booking_date = LocalDate.now();
	        LocalDate checkin_date = o.getCheckin_date();
	        LocalDate checkout_date = o.getCheckout_date();
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, room_id);
            pres.setInt(2, customer_id);
            pres.setInt(3, order_number);
            pres.setInt(4, order_price);
            pres.setInt(5, number_of_guest);
            pres.setDate(6, java.sql.Date.valueOf(booking_date));
            pres.setDate(7, java.sql.Date.valueOf(checkin_date));
            pres.setDate(8, java.sql.Date.valueOf(checkout_date));
            
            /** 執行新增之SQL指令並記錄影響之行數 */
            row = pres.executeUpdate();
            
            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            ////////修改空房紀錄
            sql = "UPDATE `mydb`.`tbl_roomavailability` " +
            "SET `available_quantity` = `available_quantity` - ? " +
            "WHERE `room_id` = ? AND `date` >= ? AND `date` < ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            // 设置参数
            preparedStatement.setInt(1, order_number);
            preparedStatement.setInt(2, room_id);
            preparedStatement.setDate(3, Date.valueOf(checkin_date));
            preparedStatement.setDate(4, Date.valueOf(checkout_date));

            preparedStatement.executeUpdate();
            

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
     * 驗證信用卡卡號
     *
     * @param o 訂單物件
     * @param use_point 是否使用會員點數
     * @param card_number 信用卡卡號
     * @return Boolean 回傳卡號是否正確
     */
    public Boolean check_credit_card(String card_number){
        /** 紀錄SQL總行數，若為「-1」代表資料庫檢索尚未完成 */
        int row = -1;
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT count(*) FROM `mydb`.`tbl_Creditcard` WHERE `card_num` = ?";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, card_number);
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
         * 判斷是否有符合卡號
         * 若無一筆則回傳False，否則回傳True 
         */
        return (row == 0) ? false : true;
    }

    /**
     * 刪除一個訂單
     *
     * @param id 訂單id
     * @return the JSONObject 回傳SQL指令執行結果與執行之資料
     */
    public JSONObject deleteByID(int id){
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 紀錄程式開始執行時間 */
        long start_time = System.nanoTime();
        /** 紀錄SQL總行數 */
        int row = 0;

        //先取得訂單資料
        JSONObject order_detail = getByOrderID(id);
        JSONArray jsa = order_detail.getJSONArray("data");
        JSONObject orderData = jsa.getJSONObject(0);
        int room_id = orderData.getInt("room_id");
        int order_number = orderData.getInt("order_number");
        String dateString = orderData.getString("checkin_date");
        LocalDate checkin_date = LocalDate.parse(dateString);
        dateString = orderData.getString("checkout_date");
        LocalDate checkout_date = LocalDate.parse(dateString);
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "DELETE FROM `mydb`.`tbl_Order` WHERE `order_id` = ?";

            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, id);
            
            /** 執行新增之SQL指令並記錄影響之行數 */
            row = pres.executeUpdate();
            
            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);

            ////新增空房紀錄
            sql = "UPDATE `mydb`.`tbl_room_availability` " +
            "SET `available_quantity` = `available_quantity` + ? " +
            "WHERE `room_id` = ? AND `date` >= ? AND `date` < ?";

            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setInt(1, order_number);
            pres.setInt(2, room_id);
            pres.setDate(3, java.sql.Date.valueOf(checkin_date));
            pres.setDate(4, java.sql.Date.valueOf(checkout_date));

            pres.executeUpdate();

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
