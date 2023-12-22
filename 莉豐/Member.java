import java.util.Date;
import org.json.*;

public abstract class Member {
	private String name;
	private String email;
	private String password;
	private Date birthday;
	private String intro;
	private Date modified_time;//紀錄會員最後一次更新的時間
	
	public Member(String name,String email,String password,Date birthday,String intro) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
		this.intro = intro;
	}
	public Member(String name,String email,String password,Date birthday,String intro,Date modified_time) {
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
	public Date getBirthday(){
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
		return jso;
	}
}
