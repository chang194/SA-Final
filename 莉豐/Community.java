package app;
import org.json.*;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class Community<br>
 * Community���O�]class�^�D�n�޲z�Ҧ��Pcommunity�����P��Ʈw����k
 * </p>
 * 
 * @author Winnie
 * @version 1.0.0
 * @since 1.0.0
 */

public class Community {
    private int community_id;
    private String community_name;
    private String intro;
    private int customer_id; //�����O�ֳЫسo���s

    /**
     * ��Ҥơ]Instantiates�^�@�ӷs���]new�^Community����<br>
     * �ĥΦh���]overload�^��k�i��A���غc�l�Ω�s�W�B�d�ߪ��s�ɡA���ͤ@�ӷs�����s�A���˵����s�ԲӸ��
     *
	 * @param community_name ���s�W��
	 * @param intro ���s²��
     * @param customer_id �ЫؤH��id
     */
    public Community(String community_name,String intro,int customer_id){
        this.community_name = community_name;
        this.intro =intro;
        this.customer_id = customer_id;
    }
     /**
     * ��Ҥơ]Instantiates�^�@�ӷs���]new�^Community����<br>
     * �ĥΦh���]overload�^��k�i��A���غc�l�Ω�ק���s�ɡA�ק���s���
     *
	 * @param community_name ���s�W��
	 * @param intro ���s²��
     * @param community_id ���sid
     */
    public Community(int community_id,String community_name,String intro){
        this.community_id = community_id;
        this.community_name = community_name;
        this.intro = intro;
    }
    public int getCommunityId(){
        return community_id;
    }
    public String getCommunityName(){
        return community_name;
    }
    public String getIntro(){
        return intro;
    }
    public int getCustomerId(){
        return customer_id;
    }
    /**
     * ���o�Ӫ��s�Ҧ����
     *
     * @return the data ���o�Ӫ��s���Ҧ���ƨëʸ˩�JSONObject����
     */
    public JSONObject getData(){
        JSONObject jso = new JSONObject();
        jso.put("community name",getCommunityName());
        jso.put("introduction",getIntro());
        return jso;
    }
}
