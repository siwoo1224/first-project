package kr.co.samjo.res;

public class resDTO {

    private String res_no; //timestamp default systimestamp not null,
    private String user_id; //varchar2(15) not null,
    private int amount; // number,
    private String pay; //varchar2(20),
    private String result; // char(1) not null,
    
	public String getRes_no() {
		return res_no;
	}
	public void setRes_no(String res_no) {
		this.res_no = res_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "resDTO [res_no=" + res_no + ", user_id=" + user_id + ", amount=" + amount + ", pay=" + pay + ", result="
				+ result + "]";
	}
	
}
