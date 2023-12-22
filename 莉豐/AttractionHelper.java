package app;
import org.json.*;

import java.sql.*;
import util.DBMgr;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class AttractionHelper<br>
 * AttractionHelper���O�]class�^�D�n�޲z�Ҧ��Pattrction�����P��Ʈw����k
 * </p>
 * 
 * @author Winnie
 * @version 1.0.0
 * @since 1.0.0
 */

public class AttractionHelper {
    //�R�A�ܼơA�x�sAttractionHelper����
    private static AttractionHelper ah;
    //�x�sJDBC��Ʈw�s�u
    private Connection conn = null;
    //�x�sJDBC�w�ǳƤ�SQL���O
    private PreparedStatement pres = null;
    
    /**
     * ��Ҥơ]Instantiates�^�@�ӷs���]new�^MemberHelper����<br>
     * �ĥ�Singleton���ݭn�z�Lnew
     */
    private AttractionHelper(){}

    /**
     * �R�A��k<br>
     * ��@Singleton�]��ҼҦ��^�A�Ȥ��\�إߤ@��AttractionHelper����
     *
     * @return the helper �^��AttractionHelper����
     */
    public static AttractionHelper getHelper(){
        if(ah == null){
            ah = new AttractionHelper();
        }
        return ah;
    }

    /**
     * �z�L�|���s���]ID�^���o�|�����
     *
     * @param id �|���s��
     * @return the JSON object �^��SQL���浲�G�P�ӷ|���s�����|�����
     */
    public JSONObject getByID(String id){
        //�s�ؤ@��Attraction����a�ܼơA�O���C�@��d�ߤ����I���
        Attraction a = null;
        //�Ω��x�s�Ҧ��˯��^�����I�A�HJSONArray�覡�x�s
        JSONArray jsa = new JSONArray();
        //������ڰ��椧SQL���O
        String execute_sql = "";
        //�����{���}�l����ɶ�
        long start_time = System.nanoTime();
        //����SQL�`���
        int row = 0;
        //�x�sJDBC�˯���Ʈw��^�Ǥ����G�A�Hpointer�覡���ʨ�U�@�����
        ResultSet rs = null;
        try{
            //���o��Ʈw�s�u
            conn = DBMgr.getConnection();
            //SQL���O
            String sql = "SELECT * FROM `mydb`.`tbl_Attraction` WHERE `attraction_id` = ? LIMIT 1";
            //�N�ѼƦ^���SQL���O��
            pres = conn.prepareStatement(sql);
            pres.setString(1, id);
            //����d�ߤ�SQL���O�ì�����^�Ǥ����
            rs = pres.executeQuery();

            //�����u����檺SQL���O�A�æL�X
            execute_sql = pres.toString();
            System.out.println(execute_sql);

            //�z�Lwhile�j�鲾��pointer�A���o�C�@���^�Ǹ��
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
            /** �L�XJDBC SQL���O���~ **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        }catch(Exception e){
            /** �Y���~�h�L�X���~�T�� */
            e.printStackTrace();
        }finally{
            /** �����s�u������Ҧ���Ʈw�������귽 **/
            DBMgr.close(rs,pres,conn);
        }
        /** �����{����������ɶ� */
        long end_time = System.nanoTime();
        /** �����{������ɶ� */
        long duration = (end_time - start_time);
        
        /** �NSQL���O�B��O�ɶ��B�v�T��ƻP�Ҧ����I��Ƥ�JSONArray�A�ʸ˦�JSONObject�^�� */
        JSONObject response = new JSONObject();
        response.put("sql", execute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }
}