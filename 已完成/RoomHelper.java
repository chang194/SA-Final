package app;

import java.sql.*;
import org.json.*;
import java.time.*;
import util.DBMgr;

public class RoomHelper {

    private RoomHelper() {
        
    }

    private static RoomHelper rh;
    private Connection conn = null;
    private PreparedStatement pres = null;
    
    public static RoomHelper getHelper() {
        /** Singleton檢查是否已經有ProductHelper物件，若無則new一個，若有則直接回傳 */
        if(rh == null) rh = new RoomHelper();
        
        return rh;
    }

    /**
     * 透過房型id取得房型資料
     *
     * @param id 房型id
     * @return the JSON object 回傳SQL執行結果與該房型id之房型資料
     */
    public JSONObject getByID(int id) {
        /** 新建一個 Room 物件之 Room 變數，用於紀錄每一位查詢回之房型資料 */
        Room room = null;
        /** 用於儲存所有檢索回之房型，以JSONArray方式儲存 */
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
            String sql = "SELECT * FROM `mydb`.`tbl_Room` WHERE `room_id` = ? LIMIT 1";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, id);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            while(rs.next()) {
                /** 每執行一次迴圈表示有一筆資料 */
                row += 1;
                
                /** 將 ResultSet 之資料取出 */
                int room_id = rs.getInt("room_id");
                int hotel_id = rs.getInt("hotel_id");
                String room_type = rs.getString("room_type");
                String room_image = rs.getString("room_image");
                int room_price = rs.getInt("room_price");
                int max_capacity = rs.getInt("max_capacity");
                int room_number = rs.getInt("room_number"); //將sql DATE轉為java LocalDate
                String room_info = rs.getString("room_info");
                
                /** 將每一筆房型資料產生一名新Room物件 */
                room = new Room(room_id, hotel_id, room_type, room_image, room_number, room_price, max_capacity, room_info);
                /** 取出該名房型之資料並封裝至 JSONsonArray 內 */
                jsa.put(room.getData());
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
     * 透過hotel_id取得房型資料
     *
     * @param id hotel_id
     * @return the JSON object 回傳SQL執行結果與該hotel_id之房型資料
     */
    public JSONObject getByHotelID(int id) {
        /** 用於儲存所有檢索回之房型，以JSONArray方式儲存 */
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
            String sql = "SELECT room_id FROM `mydb`.`tbl_Room` WHERE hotel_id = ?";
            
            // 使用 PreparedStatement 預防 SQL 注入
            pres = conn.prepareStatement(sql);
            pres.setInt(1, id);

            // 執行查詢
            rs = pres.executeQuery();

            // 逐一處理查詢結果
            while (rs.next()) {
                int room_id = rs.getInt("room_id");

                // 使用 getByID 方法取得房型資料
                JSONObject roomData = getByID(room_id);

                // 將房型資料放入結果 JSON 物件
                jsa.put(roomData);
            }

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
        
        /** 將SQL指令、花費時間、影響行數與所有房型資料之JSONArray，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
         
    }

    /**
     * 建立該房型至資料庫
     *
     * @param room 一個房型之Room物件
     * @return the JSONObject 回傳SQL指令執行之結果
     */
    public JSONObject create(Room room) {
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
            String sql = "INSERT INTO `mydb`.`tbl_Room`(`hotel_id`, `room_type`, `room_image`, `room_price`, `max_capacity`, `room_number`, `room_info`)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?)";
            
            /** 取得所需之參數 */
            int hotel_id = room.getHotelId();
            String room_type = room.getRoomType();
            String image = room.getImage();
            int room_price = room.getRoomPrice();
            int max_capacity = room.getMaxCapacity();
            int room_number = room.getRoomNumber();
            String room_info = room.getRoomInfo();
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pres.setInt(1, hotel_id);
            pres.setString(2, room_type);
            pres.setString(3, image);
            pres.setInt(4, room_price);
            pres.setInt(5, max_capacity);
            pres.setInt(6, room_number);
            pres.setString(7, room_info);
            
            /** 執行新增之SQL指令並記錄影響之行數 */
            row = pres.executeUpdate();
            
            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);

            // 在执行插入操作后获取生成的主键
            ResultSet generatedKeys = pres.getGeneratedKeys();
            
            ////////為新的房型增加空房紀錄
            int new_room_id = 0;
            if (generatedKeys.next()) {
                // 获取刚插入记录的自增主键值
                new_room_id = generatedKeys.getInt(1);

                // 在这里可以使用 newRoomId，它是刚插入记录的 room_id
                System.out.println("新插入的房型的 room_id 是：" + new_room_id);
            }

            // 获取当前日期
            LocalDate currentDate = LocalDate.now();
            // 计算明天的日期
            LocalDate tomorrowDate = currentDate.plusDays(1);
            // 插入七天的数据
            for (int i = 0; i < 30; i++) {
                // 计算当前循环的日期
                LocalDate date = tomorrowDate.plusDays(i);

                sql = "INSERT INTO `mydb`.`tbl_roomavailability` (room_id, date, available_quantity) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                // 设置参数
                preparedStatement.setInt(1, new_room_id);
                preparedStatement.setDate(2, Date.valueOf(date));
                preparedStatement.setInt(3, room_number);
                
                // 执行插入
                preparedStatement.executeUpdate();
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
     * 更新一個房型之房型資料
     *
     * @param room 一個房型之Room物件
     * @return the JSONObject 回傳SQL指令執行結果與執行之資料
     */
    public JSONObject update(Room room) {
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
            String sql = "Update `mydb`.`tbl_Room` SET `room_type` = ?, `room_image` = ?, `room_price` = ?, `max_capacity` = ?, `room_number` = ?, `room_info` = ? WHERE `room_id` = ?";
            /** 取得所需之參數 */
            int room_id = room.getRoomId();
            String room_type = room.getRoomType();
            String room_image = room.getImage();
            int room_price = room.getRoomPrice();
            int max_capacity = room.getMaxCapacity();
            int room_number = room.getRoomNumber();
            String room_info = room.getRoomInfo();
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, room_type);
            pres.setString(2, room_image);
            pres.setInt(3, room_price);
            pres.setInt(4, max_capacity);
            pres.setInt(5, room_number);
            pres.setInt(6, room_id);
            pres.setString(7, room_info);

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
     * 刪除一個房型
     *
     * @param id 房型id
     * @return the JSONObject 回傳SQL指令執行結果與執行之資料
     */
    public JSONObject deleteByID(int id){
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
            String sql = "DELETE FROM `mydb`.`tbl_Room` WHERE `room_id` = ?";

            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, id);
            
            /** 執行新增之SQL指令並記錄影響之行數 */
            row = pres.executeUpdate();
            
            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);

            ////刪除空房紀錄
            sql = "DELETE FROM `mydb`.`tbl_roomavailability` WHERE room_id = ?";
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setInt(1, id);
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

    /**
     * 取得空房數量
     *
     * @param id 房型id
     * @param checkin_date 入住日期
     * @param checkout_date 退房日期
     * @return the JSONObject 回傳SQL指令執行結果與執行之資料
     */
    public int getAvailableNumber(int id, LocalDate checkin_date, LocalDate checkout_date){
        
        String exexcute_sql = "";
        int minAvailableQuantity = 0;
        
        try {
            conn = DBMgr.getConnection();
            String sql = "SELECT MIN(available_quantity) AS min_available_quantity " +
                     "FROM `mydb`.`tbl_roomavailability` " +
                     "WHERE room_id = ? AND date BETWEEN ? AND DATE_SUB(?, INTERVAL 1 DAY)";

            pres = conn.prepareStatement(sql);
            pres.setInt(1, id);
            pres.setDate(2, java.sql.Date.valueOf(checkin_date));
            pres.setDate(3, java.sql.Date.valueOf(checkout_date));

            ResultSet rs = pres.executeQuery();

            if (rs.next()) {
                minAvailableQuantity = rs.getInt("min_available_quantity");
            }

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

        return minAvailableQuantity;
    }

    /**
     * 取得空房日期
     *
     * @param id 房型id
     * @return the JSONObject 回傳SQL指令執行結果與該房型空房的日期與數量
     */
    public JSONObject getAvailableDate(int id){
        
        /** 用於儲存所有檢索回之房型，以JSONArray方式儲存 */
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
            conn = DBMgr.getConnection();
            String sql = "SELECT date, available_quantity " +
            "FROM `mydb`.`tbl_roomavailability` " +
            "WHERE room_id = ? AND available_quantity > 0";

            pres = conn.prepareStatement(sql);
            pres.setInt(1, id);

            rs = pres.executeQuery();

            while (rs.next()) {
                JSONObject dateInfo = new JSONObject();
                dateInfo.put("date", rs.getString("date"));
                dateInfo.put("available_quantity", rs.getInt("available_quantity"));
                jsa.put(dateInfo);
            }

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
        
        /** 將SQL指令、花費時間、影響行數與所有房型資料之JSONArray，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }

}
