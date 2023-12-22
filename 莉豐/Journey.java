package app;

import java.util.Date;
import org.json.*;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class Journey<br>
 * Journey類別（class）主要管理所有與journey相關與資料庫之方法
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
    private Date journey_day; //紀錄旅程開始的日期

    /**
     * 實例化（Instantiates）一個新的（new）Journey物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增旅程時，產生一個新的旅程
     *
	 * @param journey_name 旅程名稱
     * @param intro 旅程簡介
     * @param customer_id 創建人id
     * @param journey_day 旅程開始的日期
     */
    public Journey(String journey_name,String intro,int customer_id,Date journey_day){
        this.journey_name = journey_name;
        this.intro = intro;
        this.customer_id = customer_id;
        this.journey_day =journey_day;
    }
    /**
     * 實例化（Instantiates）一個新的（new）Journey物件<br>
     * 採用多載（overload）方法進行，此建構子用於修改、查詢旅程時，修改旅程資料，或檢視旅程詳細資料
     *
	 * @param journey_id 旅程id
	 * @param journey_name 旅程名稱
     * @param intro 旅程簡介
     * @param customer_id 創建人id
     * @param journey_day 旅程開始的日期
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
     * 取得該旅程所有資料
     *
     * @return the data 取得該旅程之所有資料並封裝於JSONObject物件內
     */
    public JSONObject getData(){
        JSONObject jso = new JSONObject();
        jso.put("journey name",getJourneyName());
        jso.put("introduction",getIntro());
        jso.put("journey day",getJourneyDay());
        return jso;
    }
}