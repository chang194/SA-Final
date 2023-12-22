import org.json.JSONObject;

public class Attraction {
    private int attraction_id;
    private String attraction_name;
    private String address;
    private String bussiness_hours;
    private String website;
    private String rating;
    private String intro;
    private String image; //儲存圖片路徑位置

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