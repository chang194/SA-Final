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
     * �z�LJDBC�إ߸�Ʈw���s�u
     *
     * @return the connection �^�ǩҫإߤ���Ʈw�s�u
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
     * �����Ҧ���Ʈw�s�u�P����SQL�귽
     *
     * @param stm SQL�d�ߤ����O
     * @param conn ��Ʈw���s�u
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
     * �����Ҧ���Ʈw�s�u�P����SQL�귽
     *
     * @param rs ��Ʈw�˯��ᤧ���G���
     * @param stm SQL�d�ߤ����O
     * @param conn ��Ʈw���s�u
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
