package app;
import java.util.Date;
import java.time.LocalDate;
import org.json.*;

public abstract class Member {
	private String name;
	private String email;
	private String password;
	private LocalDate birthday; //將生日的資料型態改為LocalDate
	private String intro;
	private Date modified_time; //紀錄會員最後一次更新的時間
	
	//修改
	public Member(String name,String password,String intro) {
		this.name = name;
		this.password = password;
		this.intro = intro;
	}
	
	//註冊
	public Member(String name,String email, String password,LocalDate birthday,String intro) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
		this.intro = intro;
	}
	//查詢
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
	
	//取得一個會員的資料
	public JSONObject getData() {
		JSONObject jso = new JSONObject();
		jso.put("name", getName());
		jso.put("email", getEmail());
		jso.put("password", getPassword());
		jso.put("birthday",getBirthday());
		jso.put("Introduction", getIntro());
		jso.put("modified_time", getModified_time());
		return jso;
	}
}