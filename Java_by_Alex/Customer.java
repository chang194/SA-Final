package app;
import java.time.LocalDate;
import java.util.Date;

public class Customer extends Member{
	private int customer_id;
	private int customer_point;
	//register
	public Customer(String name,String email,String password,LocalDate birthday,String intro) {
		super(name,email,password,birthday,intro);
		this.customer_point = 0; //在註冊時將會員點數設為0
	}
	//revise
	public Customer(int customer_id,String name,String email,String password,LocalDate birthday,String intro,Date modified_time){
		super(name,email,password,birthday,intro,modified_time);
		this.customer_id = customer_id;
	}
	//getdata
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
