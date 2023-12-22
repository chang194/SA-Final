import java.sql.*;
import java.util.*;

public class DBMgr {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    static final String USER = "root";
    static final String Password = "";

    static{
        try{
            Class.forName(DBMgr.JDBC_DRIVER);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public DBMgr(){}

    /**
     * 透過JDBC建立資料庫之連線
     *
     * @return the connection 回傳所建立之資料庫連線
     */
    public static Connection getConnection(){
        Connection conn = null;
        //database connection parameter seting
        Properties props = new Properties();
        props.setProperty("useSSL", "flase"); //set SSL
        props.setProperty("serverTimezone", "UTC"); //set time zone
        props.setProperty("useUnicode", "true"); //set unicode
        props.setProperty("characterEncoding", "utf8");
        props.setProperty("user", DBMgr.USER);
        props.setProperty("password", DBMgr.Password);

        try{
            conn = DriverManager.getConnection(DBMgr.DB_URL,props);
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 關閉所有資料庫連線與釋放SQL資源
     *
     * @param stm SQL查詢之指令
     * @param conn 資料庫之連線
     */
    public static void close(Statement stm, Connection conn) {
        try {
            if (stm != null) stm.close(); //check statment
            if (conn != null) conn.close(); //check connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 關閉所有資料庫連線與釋放SQL資源
     *
     * @param rs 資料庫檢索後之結果資料
     * @param stm SQL查詢之指令
     * @param conn 資料庫之連線
     */
    public static void close(ResultSet rs, Statement stm, Connection conn) {
        try {
            if (rs != null) rs.close(); //check resultset
            if (stm != null) stm.close(); //check statment
            if (conn != null) conn.close(); //check connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
