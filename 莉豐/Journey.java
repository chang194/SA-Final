package app;

import java.util.Date;
import org.json.*;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class Journey<br>
 * Journey���O�]class�^�D�n�޲z�Ҧ��Pjourney�����P��Ʈw����k
 * </p>
 * 
 * @author Winnie
 * @version 1.0.0
 * @since 1.0.0
 */

public class Journey {
    private int journey_id;
    private String journey_name;
    private String intro;
    private int customer_id;
    private Date journey_day; //�����ȵ{�}�l�����

    /**
     * ��Ҥơ]Instantiates�^�@�ӷs���]new�^Journey����<br>
     * �ĥΦh���]overload�^��k�i��A���غc�l�Ω�s�W�ȵ{�ɡA���ͤ@�ӷs���ȵ{
     *
	 * @param journey_name �ȵ{�W��
     * @param intro �ȵ{²��
     * @param customer_id �ЫؤHid
     * @param journey_day �ȵ{�}�l�����
     */
    public Journey(String journey_name,String intro,int customer_id,Date journey_day){
        this.journey_name = journey_name;
        this.intro = intro;
        this.customer_id = customer_id;
        this.journey_day =journey_day;
    }
    /**
     * ��Ҥơ]Instantiates�^�@�ӷs���]new�^Journey����<br>
     * �ĥΦh���]overload�^��k�i��A���غc�l�Ω�ק�B�d�߮ȵ{�ɡA�ק�ȵ{��ơA���˵��ȵ{�ԲӸ��
     *
	 * @param journey_id �ȵ{id
	 * @param journey_name �ȵ{�W��
     * @param intro �ȵ{²��
     * @param customer_id �ЫؤHid
     * @param journey_day �ȵ{�}�l�����
     */
    public Journey(int journey_id,String journey_name,String intro,int customer_id,Date journey_day){
        this.journey_id = journey_id;
        this.journey_name = journey_name;
        this.intro = intro;
        this.customer_id = customer_id;
        this.journey_day =journey_day;
    }
    public int getJourneyId(){
        return journey_id;
    }
    public String getJourneyName(){
        return journey_name;
    }
    public String getIntro(){
        return intro;
    }
    public int getCustomerId(){
        return customer_id;
    }
    public Date getJourneyDay(){
        return journey_day;
    }
    /**
     * ���o�Ӯȵ{�Ҧ����
     *
     * @return the data ���o�Ӯȵ{���Ҧ���ƨëʸ˩�JSONObject����
     */
    public JSONObject getData(){
        JSONObject jso = new JSONObject();
        jso.put("journey name",getJourneyName());
        jso.put("introduction",getIntro());
        jso.put("journey day",getJourneyDay());
        return jso;
    }
}