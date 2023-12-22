import org.json.*;

public class Community {
    private int community_id;
    private String community_name;
    private String intro;
    private int customer_id;

    //add,getdata
    public Community(String community_name,String intro,int customer_id){
        this.community_name = community_name;
        this.intro =intro;
        this.customer_id = customer_id;
    }
    //revise
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
    public JSONObject getData(){
        JSONObject jso = new JSONObject();
        jso.put("community name",getCommunityName());
        jso.put("introduction",getIntro());
        return jso;
    }
}
