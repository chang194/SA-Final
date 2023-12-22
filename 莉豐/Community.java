package app;
import org.json.*;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class Community<br>
 * Community類別（class）主要管理所有與community相關與資料庫之方法
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
    private int customer_id; //紀錄是誰創建這社群

    /**
     * 實例化（Instantiates）一個新的（new）Community物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增、查詢社群時，產生一個新的社群，或檢視社群詳細資料
     *
	 * @param community_name 社群名稱
	 * @param intro 社群簡介
     * @param customer_id 創建人的id
     */
    public Community(String community_name,String intro,int customer_id){
        this.community_name = community_name;
        this.intro =intro;
        this.customer_id = customer_id;
    }
     /**
     * 實例化（Instantiates）一個新的（new）Community物件<br>
     * 採用多載（overload）方法進行，此建構子用於修改社群時，修改社群資料
     *
	 * @param community_name 社群名稱
	 * @param intro 社群簡介
     * @param community_id 社群id
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
     * 取得該社群所有資料
     *
     * @return the data 取得該社群之所有資料並封裝於JSONObject物件內
     */
    public JSONObject getData(){
        JSONObject jso = new JSONObject();
        jso.put("community name",getCommunityName());
        jso.put("introduction",getIntro());
        return jso;
    }
}
