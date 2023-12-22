package app;
import org.json.JSONObject;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class Attraction<br>
 * AttractionH類別（class）主要管理所有與attrction相關與資料庫之方法
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
    private String bussiness_hours; //營業時間
    private String website; //紀錄該景點官網的網址
    private String rating; //評分
    private String intro;
    private String image; //紀錄圖片的路徑儲存位置

    /**
     * 實例化（Instantiates）一個新的（new）Attraction物件<br>
     * 採用多載（overload）方法進行，此建構子用於查詢景點資料時，檢視景點詳細資訊
     *
	 * @param attraction_id 景點id
	 * @param attraction_name 景點名稱
     * @param address 景點地址
     * @param bussiness_hours 景點營業時間
     * @param website 景點官網網址
	 * @param rating 景點評分
	 * @param intro 景點簡介
     * @param image 景點圖片
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
     * 取得該景點所有資料
     *
     * @return the data 取得該景點之所有資料並封裝於JSONObject物件內
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