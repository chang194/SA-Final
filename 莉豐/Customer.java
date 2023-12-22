package app;

import java.time.LocalDate;
import java.util.Date;

public class Customer extends Member{
	private int customer_id;
	private int customer_point;
	/**
     * ��Ҥơ]Instantiates�^�@�ӷs���]new�^Customer����<br>
     * �ĥΦh���]overload�^��k�i��A���غc�l�Ω�إ߷|����ƮɡA���ͤ@�W�s���|��
     *
	 * @param birthday �U�ȥͤ�
     * @param email �U�ȹq�l�H�c
     * @param password �U�ȱK�X
     * @param name �U�ȩm�W
	 * @param intro �U��²��
     */
	public Customer(String name,String email,String password,LocalDate birthday,String intro) {
		super(name,email,password,birthday,intro);
		this.customer_point = 0; //�b���U�ɱN�|���I�Ƴ]��0
	}
	/**
     * ��Ҥơ]Instantiates�^�@�ӷs���]new�^Customer����<br>
     * �ĥΦh���]overload�^��k�i��A���غc�l�Ω�ק�|����ƮɡA�ק�|������T
     *
	 * @param customer_id �U��id
	 * @param birthday �U�ȥͤ�
     * @param email �U�ȹq�l�H�c
     * @param password �U�ȱK�X
     * @param name �U�ȩm�W
	 * @param intro �U��²��
	 * @param modified_time �U�ȳ̫�@����s��Ʈɶ�
     */
	public Customer(int customer_id,String name,String email,String password,LocalDate birthday,String intro,Date modified_time){
		super(name,email,password,birthday,intro,modified_time);
		this.customer_id = customer_id;
	}
	/**
     * ��Ҥơ]Instantiates�^�@�ӷs���]new�^Customer����<br>
     * �ĥΦh���]overload�^��k�i��A���غc�l�Ω�d�߷|����ƮɡA�˵��|���ԲӸ��
     *
	 * @param customer_id �U��id
	 * @param birthday �U�ȥͤ�
     * @param email �U�ȹq�l�H�c
     * @param password �U�ȱK�X
     * @param name �U�ȩm�W
	 * @param intro �U��²��
	 * @param customer_point �|���I��
	 * @param modified_time �U�ȳ̫�@����s��Ʈɶ�
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