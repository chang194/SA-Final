package app;

import java.time.LocalDate;
import java.util.Date;

public class Customer extends Member{
	private int customer_id;
	private int customer_point;
	/**
     * 實例化（Instantiates）一個新的（new）Customer物件<br>
     * 採用多載（overload）方法進行，此建構子用於建立會員資料時，產生一名新的會員
     *
	 * @param birthday 顧客生日
     * @param email 顧客電子信箱
     * @param password 顧客密碼
     * @param name 顧客姓名
	 * @param intro 顧客簡介
     */
	public Customer(String name,String email,String password,LocalDate birthday,String intro) {
		super(name,email,password,birthday,intro);
		this.customer_point = 0; //在註冊時將會員點數設為0
	}
	/**
     * 實例化（Instantiates）一個新的（new）Customer物件<br>
     * 採用多載（overload）方法進行，此建構子用於修改會員資料時，修改會員的資訊
     *
	 * @param customer_id 顧客id
	 * @param birthday 顧客生日
     * @param email 顧客電子信箱
     * @param password 顧客密碼
     * @param name 顧客姓名
	 * @param intro 顧客簡介
	 * @param modified_time 顧客最後一次更新資料時間
     */
	public Customer(int customer_id,String name,String email,String password,LocalDate birthday,String intro,Date modified_time){
		super(name,email,password,birthday,intro,modified_time);
		this.customer_id = customer_id;
	}
	/**
     * 實例化（Instantiates）一個新的（new）Customer物件<br>
     * 採用多載（overload）方法進行，此建構子用於查詢會員資料時，檢視會員詳細資料
     *
	 * @param customer_id 顧客id
	 * @param birthday 顧客生日
     * @param email 顧客電子信箱
     * @param password 顧客密碼
     * @param name 顧客姓名
	 * @param intro 顧客簡介
	 * @param customer_point 會員點數
	 * @param modified_time 顧客最後一次更新資料時間
     */
	public Customer(int customer_id,String name,String email,String password,LocalDate birthday,String intro,int customer_point,Date modified_time){
		super(name,email,password,birthday,intro,modified_time);
		this.customer_id = customer_id;
		this.customer_point = customer_point;
	}
	public int getCustomer_id(){
		return customer_id;
	}
	public int getCustomer_point(){
		return customer_point;
	}
}