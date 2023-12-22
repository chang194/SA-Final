package util;
import java.sql.*;
import java.util.Properties;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class DBMgr<br>
 * DBMgr���O�]class�^�޲z�P��Ʈw�إ߻P�����s�u����k�A���x�s�������]�w���<br>
 * </p>
 *
 * @author Winnie
 * @version 1.0.0
 * @since 1.0.0
 */

public class DBMgr {

    /** JDBC_DRIVER�`�ơA�]�wJDBC�X�ʤ����O�W�� */
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    /** DB_URL�`�ơA���w��Ʈw�Ҧb��IP�κ���BPort���X�P���w�ҭn�ϥθ�Ʈw */
    static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";

    /** USER�`�ơA�ҭn�ϥΤ���Ʈw�ϥΪ̱b�� */
    static final String USER = "root";

    /** PASS�`�ơA�Ҧ��ϥΤ���Ʈw�ϥΪ̱K�X */
    static final String PASS = "";

    /** �R�A���w�ҭn�ϥΤ�Class�W�� **/
    static {
        try {
            /** ���JJDBC�X�����O�A�ð����l�� */
            Class.forName(DBMgr.JDBC_DRIVER);
        } catch (Exception e) {
            /** �Y���~�h�L�X���~�T�� */
            e.printStackTrace();
        }
    }

    /**
     * ��Ҥơ]Instantiates�^�@�ӷs���]new�^DBMgr����
     */
    public DBMgr() {

    }

    /**
     * �z�LJDBC�إ߸�Ʈw���s�u
     *
     * @return the connection �^�ǩҫإߤ���Ʈw�s�u
     */
    public static Connection getConnection() {
        /** ��Ʈw�s�u�����Ѽ� */
        Properties props = new Properties();
        /** �]�w��Ʈw�s�u�O�_�n�ϥ�SSL�s�u�A�ѩ�w�]���ϥ�SSL�s�u�]���������n���w��False */
        props.setProperty("useSSL", "false");
        /** �]�w��Ʈw�ϥΤ��ɰ� */
        props.setProperty("serverTimezone", "UTC");
        /** �]�w�O�_�ϥ�Unicode�A�������n�]�w��True�קK����r�|�o�Ͱ��D */
        props.setProperty("useUnicode", "true");
        /** �]�w�ϥΤ��r���s�X�A�ĥ�UTF-8 */
        props.setProperty("characterEncoding", "utf8");
        /** �]�w�s�u�ҨϥΤ��b���M�K�X */
        props.setProperty("user", DBMgr.USER);
        props.setProperty("password", DBMgr.PASS);

        /** �ŧiConnection�ܼơA�å����V��null */
        Connection conn = null;

        /** ���ճz�LDriverManager��getConnection()�إߨè��o��Ʈw�s�u */
        try {
            conn = DriverManager.getConnection(DBMgr.DB_URL, props);
        } catch (Exception e) {
            /** �Y���~�h�L�X���~�T�� */
            e.printStackTrace();
        }

        /** �إ߻P���^�s�u���\��A�^�Ǹӳs�u���ܼ� */
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
            /** �T�{Statement�O�_��null�A�Y����null�h��������Statement����귽 */
            if (stm != null) stm.close();
            /** �T�{Connection�O�_��null�A�Y����null�h��������Connection����귽 */
            if (conn != null) conn.close();
        } catch (Exception e) {
            /** �Y���~�h�L�X���~�T�� */
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
            /** �T�{ResultSet�O�_��null�A�Y����null�h��������ResultSet����귽 */
            if (rs != null) rs.close();
            /** �T�{Statement�O�_��null�A�Y����null�h��������Statement����귽 */
            if (stm != null) stm.close();
            /** �T�{Connection�O�_��null�A�Y����null�h��������Connection����귽 */
            if (conn != null) conn.close();
        } catch (Exception e) {
            /** �Y���~�h�L�X���~�T�� */
            e.printStackTrace();
        }
    }

    public static String[] stringToArray(String data, String delimiter) {
      String[] result;
      result = data.split(delimiter);
      return result;
    }
}
