package kr.co.samjo.res;

import kr.co.samjo.product.maszip.MaszipDAO;
import kr.co.samjo.product.packagetour.packagetourDAO;
import kr.co.samjo.product.rentalcar.rentalcarDAO;
import kr.co.samjo.product.sookso.SooksoDAO;

public class resDetailDTO {

	private int detail_no;
    private String res_no; // timestamp not null,
    private String s_code; // varchar2(10) not null,
    private int p_cnt;
    private String sdate; // VARCHAR2(30) not null,
    private String fdate; // VARCHAR2(30)
	private String s_name;// 상품명
	

	public String getS_name() {
		if(this.s_code.charAt(0)=='C'){
			rentalcarDAO dao = new rentalcarDAO();
			s_name = dao.read(s_code).getC_name();
		}else if(this.s_code.charAt(0)=='S'){
			SooksoDAO dao = new SooksoDAO();
			s_name = dao.readroom(s_code).getS_name();
		}else if(this.s_code.charAt(0)=='R') {
			MaszipDAO dao = new MaszipDAO();
			s_name = dao.read(s_code).getM_name();
		}else {
			packagetourDAO dao = new packagetourDAO();
			s_name = dao.read(s_code).getPack_name();
		}
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
    
    public int getDetail_no() {
		return detail_no;
	}
    
    public void setDetail_no(int detail_no) {
		this.detail_no = detail_no;
	}
    
	public String getRes_no() {
		return res_no;
	}
	public void setRes_no(String res_no) {
		this.res_no = res_no;
	}
	public String getS_code() {
		return s_code;
	}
	public void setS_code(String s_code) {
		this.s_code = s_code;
	}
	public int getP_cnt() {
		return p_cnt;
	}
	public void setP_cnt(int p_cnt) {
		this.p_cnt = p_cnt;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getFdate() {
		return fdate;
	}
	public void setFdate(String fdate) {
		this.fdate = fdate;
	}
	@Override
	public String toString() {
		return "resDetailDTO [res_no=" + res_no + ", s_code=" + s_code + ", p_cnt=" + p_cnt + ", sdate=" + sdate
				+ ", fdate=" + fdate + "]";
	}    
}
