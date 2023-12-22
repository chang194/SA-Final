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
	private Date modified_time;//�����|���̫��s��ƪ��ɶ�
	
	/**
     * ��Ҥơ]Instantiates�^�@�ӷs���]new�^Member����<br>
     * �ĥΦh���]overload�^��k�i��A���غc�l�Ω�إ߷|����ƮɡA���ͤ@�W�s���|��
     *
	 * @param birthday �|���ͤ�
     * @param email �|���q�l�H�c
     * @param password �|���K�X
     * @param name �|���m�W
	 * @param intro �|��²��
     */
	public Member(String name,String email,String password,LocalDate birthday,String intro) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
		this.intro = intro;
	}
	/**
     * ��Ҥơ]Instantiates�^�@�ӷs���]new�^Member����<br>
     * �ĥΦh���]overload�^��k�i��A���غc�l�Ω�d�߷|����ƮɡA�˵��@�W�|�����ԲӸ��
     *
	 * @param birthday �|���ͤ�
     * @param email �|���q�l�H�c
     * @param password �|���K�X
     * @param name �|���m�W
	 * @param intro �|��²��
	 * @param modified_time �|���ק��Ʈɶ�
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
     * ���o�ӦW�|���Ҧ����
     *
     * @return the data ���o�ӦW�|�����Ҧ���ƨëʸ˩�JSONObject����
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
