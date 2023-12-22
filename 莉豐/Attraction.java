package app;
import org.json.JSONObject;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class Attraction<br>
 * AttractionH���O�]class�^�D�n�޲z�Ҧ��Pattrction�����P��Ʈw����k
 * </p>
 * 
 * @author Winnie
 * @version 1.0.0
 * @since 1.0.0
 */

public class Attraction {
    private int attraction_id;
    private String attraction_name;
    private String address;
    private String bussiness_hours; //��~�ɶ�
    private String website; //�����Ӵ��I�x�������}
    private String rating; //����
    private String intro;
    private String image; //�����Ϥ������|�x�s��m

    /**
     * ��Ҥơ]Instantiates�^�@�ӷs���]new�^Attraction����<br>
     * �ĥΦh���]overload�^��k�i��A���غc�l�Ω�d�ߴ��I��ƮɡA�˵����I�ԲӸ�T
     *
	 * @param attraction_id ���Iid
	 * @param attraction_name ���I�W��
     * @param address ���I�a�}
     * @param bussiness_hours ���I��~�ɶ�
     * @param website ���I�x�����}
	 * @param rating ���I����
	 * @param intro ���I²��
     * @param image ���I�Ϥ�
     */
    public Attraction(int attraction_id,String attraction_name,String address,String bussiness_hours,String website,String rating,String intro,String image){
        this.attraction_id = attraction_id;
        this.attraction_name = attraction_name;
        this.address = address;
        this.bussiness_hours = bussiness_hours;
        this.website = website;
        this.rating =  rating;
        this.intro = intro;
        this.image = image;
    }
    public int getAttractionId(){
        return attraction_id;
    }
    public String getAttractionName(){
        return attraction_name;
    }
    public String getAddress(){
        return address;
    }
    public String getBussinessHours(){
        return bussiness_hours;
    }
    public String getWebsite(){
        return website;
    }
    public String getRating(){
        return rating;
    }
    public String getIntro(){
        return intro;
    }
    public String getImage(){
        return image;
    }

    /**
     * ���o�Ӵ��I�Ҧ����
     *
     * @return the data ���o�Ӵ��I���Ҧ���ƨëʸ˩�JSONObject����
     */
    public JSONObject getData(){
        JSONObject jso = new JSONObject();
        jso.put("attraction name", getAttractionName());
        jso.put("address",getAddress());
        jso.put("bussiness hour",getBussinessHours());
        jso.put("website",getWebsite());
        jso.put("rating",getRating());
        jso.put("introduction",getIntro());
        jso.put("image",getImage());
        return jso;
    }
}