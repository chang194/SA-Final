package app;

import java.time.LocalDate;
import java.util.Date;
import org.json.*;

public abstract class Member {
	private String name;
	private String email;
	private String password;
	private LocalDate birthday;
	private String intro;
	private Date modified_time;//紀錄會員最後更新資料的時間
	
	/**
     * 實例化（Instantiates）一個新的（new）Member物件<br>
     * 採用多載（overload）方法進行，此建構子用於建立會員資料時，產生一名新的會員
     *
	 * @param birthday 會員生日
     * @param email 會員電子信箱
     * @param password 會員密碼
     * @param name 會員姓名
	 * @param intro 會員簡介
     */
	public Member(String name,String email,String password,LocalDate birthday,String intro) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
		this.intro = intro;
	}
	/**
     * 實例化（Instantiates）一個新的（new）Member物件<br>
     * 採用多載（overload）方法進行，此建構子用於查詢會員資料時，檢視一名會員的詳細資料
     *
	 * @param birthday 會員生日
     * @param email 會員電子信箱
     * @param password 會員密碼
     * @param name 會員姓名
	 * @param intro 會員簡介
	 * @param modified_time 會員修改資料時間
     */
	public Member(String name,String email,String password,LocalDate birthday,String intro,Date modified_time) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
		this.intro = intro;
		this.modified_time = modified_time;
	} 
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public LocalDate getBirthday(){
		return birthday;
	}
	public String getIntro(){
		return intro;
	}
	public Date getModified_time(){
		return modified_time;
	}
	
	/**
     * 取得該名會員所有資料
     *
     * @return the data 取得該名會員之所有資料並封裝於JSONObject物件內
     */
	public JSONObject getData() {
		JSONObject jso = new JSONObject();
		jso.put("name", getName());
		jso.put("email", getEmail());
		jso.put("password", getPassword());
		jso.put("birthday",getBirthday());
		jso.put("Introduction", getIntro());
		return jso;
	}
}
