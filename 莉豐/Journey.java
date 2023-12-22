import java.util.Date;
import org.json.*;

public class Journey {
    private int journey_id;
    private String journey_name;
    private String intro;
    private int customer_id;
    private Date journey_day; //journey start day

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
    public JSONObject getData(){
        JSONObject jso = new JSONObject();
        jso.put("journey name",getJourneyName());
        jso.put("introduction",getIntro());
        jso.put("journey day",getJourneyDay());
        return jso;
    }
}