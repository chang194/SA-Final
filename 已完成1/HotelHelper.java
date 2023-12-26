package app;

import java.sql.*;
import org.json.*;
import java.time.*;
import util.DBMgr;

public class HotelHelper {
    private HotelHelper() {
        
    }

    private static HotelHelper hh;
    private RoomHelper rh = RoomHelper.getHelper();
    private Connection conn = null;
    private PreparedStatement pres = null;
    
    public static HotelHelper getHelper() {
        /** Singleton檢查是否已經有HotelHelper物件，若無則new一個，若有則直接回傳 */
        if(hh == null) hh = new HotelHelper();
        
        return hh;
    }

    /**
     * 透過旅店id取得旅店資料
     *
     * @param id 旅店id
     * @return the JSON object 回傳SQL執行結果與該旅店id之旅店資料
     */
    public JSONObject getByID(int id) {
        /** 新建一個 Hotel 物件之 h 變數，用於紀錄每一位查詢回之旅店資料 */
        Hotel h = null;
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
            String sql = "SELECT * FROM `mydb`.`tbl_Hotel` WHERE `hotel_id` = ? LIMIT 1";
            
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
	            String hotelname = rs.getString("hotel_name");
	            String location = rs.getString("hotel_location");
	            String image = rs.getString("hotel_image");
	            String facilities = rs.getString("hotel_facilities");
	            String intro = rs.getString("hotel_intro");
                
                /** 將旅店資料產生一個新Hotel物件 */
                h = new Hotel(id, hotelname, location, image, facilities, intro);
                jsa.put(h.getData());
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
     * 取得所有旅店資料
     *
     * @return the JSON object 回傳SQL執行結果與所有旅店資料
     */
    public JSONObject getAll() {
        /** 新建一個 Hotel 物件之 h 變數，用於紀錄每一個查詢回之旅店資料 */
    	Hotel h = null;
        /** 用於儲存所有檢索回之旅店，以JSONArray方式儲存 */
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
            String sql = "SELECT * FROM `mydb`.`tbl_Hotel`";
            
            /** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
            pres = conn.prepareStatement(sql);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
            while(rs.next()) {
                /** 每執行一次迴圈表示有一筆資料 */
                row += 1;
                
                /** 將 ResultSet 之資料取出 */
                int hotel_id = rs.getInt("hotel_id");
	            String hotelname = rs.getString("hotel_name");
	            String location = rs.getString("hotel_location");
	            String image = rs.getString("hotel_image");
	            String facilities = rs.getString("hotel_facilities");
	            String intro = rs.getString("hotel_intro");
                
                /** 將旅店資料產生一個新Hotel物件 */
                h = new Hotel(hotel_id, hotelname, location, image, facilities, intro);
                jsa.put(h.getData());
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
     * 建立該旅店至資料庫
     *
     * @param hotel 一個Hotel物件
     * @param hotelowner_id 該住宿業者id
     * @return the JSONObject 回傳SQL指令執行之結果
     */
    public JSONObject create(Hotel hotel, int hotelowner_id) {
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
            String sql = "INSERT INTO `mydb`.`tbl_Hotel`(`hotel_name`, `hotel_location`, `hotel_image`, `hotel_facilities`, `hotel_intro`)"
                    + " VALUES(?, ?, ?, ?, ?)";
            
            /** 取得所需之參數 */
            String hotel_name = hotel.getHotelName();
            String hotel_location = hotel.getLocation();
            String hotel_image = hotel.getImage();
            String hotel_facilities = hotel.getFacilities();
            String hotel_intro = hotel.getIntro();
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, hotel_name);
            pres.setString(2, hotel_location);
            pres.setString(3, hotel_image);
            pres.setString(4, hotel_facilities);
            pres.setString(5, hotel_intro);
            
            /** 執行新增之SQL指令並記錄影響之行數 */
            row = pres.executeUpdate();
            
            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);

            ////將hotel_id加入tbl_HotelOwner
            sql = "UPDATE `mydb`.`tbl_Hotelowner` SET hotel_id = ? WHERE hotelowner_id = ?";
            
            //取得hotel_id
            ResultSet generatedKeys = pres.getGeneratedKeys();
            
            ////////為新的房型增加空房紀錄
            int hotel_id = 0;
            if (generatedKeys.next()) {
                // 获取刚插入记录的自增主键值
                hotel_id = generatedKeys.getInt(1);
            }
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, hotel_id);
            pres.setInt(2, hotelowner_id);
            
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
     * 更新一個旅店資料
     *
     * @param h 一個旅店物件
     * @return the JSON object 回傳SQL執行結果與所有旅店資料
     */
    public JSONObject update(Hotel h){

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
            String sql = "Update `mydb`.`tbl_Hotel` SET `hotel_name` = ? ,`hotel_location` = ? , `hotel_image` = ?, `hotel_facilities` = ?, `hotel_intro` = ? WHERE `hotel_id` = ?";
            
            int hotel_id = h.getHotel_id();
            String hotelname = h.getHotelName();
	        String location = h.getLocation();
	        String image = h.getImage();
	        String facilities = h.getFacilities();
	        String intro = h.getIntro();

            /** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
            pres = conn.prepareStatement(sql);
            pres.setString(1, hotelname);
            pres.setString(2, location);
            pres.setString(3, image);
            pres.setString(4, facilities);
            pres.setString(5, intro);
            pres.setInt(6, hotel_id);

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
        
        /** 將SQL指令、花費時間、影響行數與所有會員資料之JSONArray，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }

    /**
     * 搜尋旅店 依關鍵字
     *
     * @param keyword 關鍵字(String)
     * @return the JSON object 回傳SQL執行結果與所有旅店資料
     */
    public JSONObject search(String keyword){
        
        JSONArray jsa = new JSONArray();
        String exexcute_sql = "";
        long start_time = System.nanoTime();
        int row = 0;
        ResultSet rs = null;

        try {
            conn = DBMgr.getConnection();

            // SQL 指令使用 LIKE 運算子搜尋符合條件的 hotel_name
            String sql = "SELECT `hotel_id` FROM `mydb`.`tbl_Hotel` WHERE hotel_name LIKE ?";
            pres = conn.prepareStatement(sql);
            pres.setString(1, "%" + keyword + "%");
            exexcute_sql = pres.toString();

            rs = pres.executeQuery();

            while (rs.next()) {
                // 取得每個符合條件的 hotel_id
                int hotelId = rs.getInt("hotel_id");

                // 使用 getByID 方法取得該旅店資料
                JSONObject hotelData = getByID(hotelId);

                // 將該旅店資料放入 JSONArray
                jsa.put(hotelData);

                // 增加符合條件的行數
                row++;
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
     * 搜尋旅店 依其他條件
     *
     * @param region 地區(String)
     * @param checkin_date 入住日期 若不限值則設為null
     * @param checkout_date 退房日期 若不限值則設為null
     * @param guest_number 住房人數 若不限值則設為-1
     * @param price_ceiling 價格上限 若不限值則設為-1
     * @param price_floor 價格下限 若不限值則設為-1
     * @return the JSON object 回傳SQL執行結果與所有旅店資料
     */
    public JSONObject search(String region, LocalDate checkin_date, LocalDate checkout_date, int guest_number, int price_ceiling, int price_floor){
        JSONArray jsa = new JSONArray();
        String exexcute_sql = "";
        long start_time = System.nanoTime();
        int row = 0;
        ResultSet rs = null;

        try {
            // 取得資料庫連線
            conn = DBMgr.getConnection();
            
            // 使用 PreparedStatement 準備 SQL 指令
            String sql = "SELECT DISTINCT r.room_id, r.hotel_id " +
                         "FROM tbl_room r " +
                         "LEFT JOIN tbl_hotel h ON r.hotel_id = h.hotel_id " +
                         "WHERE (? IS NULL OR h.hotel_location LIKE ?) " +
                         "AND (? = -1 OR r.max_capacity >= ?) " +
                         "AND (? = -1 OR r.room_price BETWEEN ? AND ?)";
                         
            pres = conn.prepareStatement(sql);
            // 設定 PreparedStatement 的參數
            pres.setString(1, region);
            pres.setString(2, region == null ? "%" : "%" + region + "%");
            pres.setInt(3, guest_number);
            pres.setInt(4, guest_number);
            pres.setInt(5, price_ceiling);
            pres.setInt(6, price_floor);
            pres.setInt(7, price_ceiling);
                
            // 執行 SQL 查詢
            rs = pres.executeQuery();
                
            // 遍歷查詢結果，取得符合條件的 room_id
            while (rs.next()) {
                row++;
                int room_id = rs.getInt("room_id");
                int hotel_id = rs.getInt("hotel_id");
                
                if(checkin_date != null){
                    // 使用 getAvailableNumber 方法查詢空房數量
                    int availableNumber = rh.getAvailableNumber(room_id, checkin_date, checkout_date);
                    
                    // 如果空房數量大於等於 1，將 hotel_id 加入結果中
                    if (availableNumber >= 1) {
                        
                        JSONObject hotelData = getByID(hotel_id);
                        jsa.put(hotelData);
                    }
                }
                else{
                    JSONObject hotelData = getByID(hotel_id);
                    jsa.put(hotelData);
                }
                
            }
        }catch (SQLException e) {
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
}
