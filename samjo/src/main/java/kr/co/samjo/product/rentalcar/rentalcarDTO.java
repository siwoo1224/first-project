package kr.co.samjo.product.rentalcar;

import java.util.Date;

import kr.co.samjo.product.maszip.MaszipDAO;
import kr.co.samjo.product.packagetour.packagetourDAO;
import kr.co.samjo.product.rental.*;
import kr.co.samjo.product.sookso.SooksoDAO;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class rentalcarDTO {

	private String c_code; // VARCHAR2(10) NOT NULL 차량코드
	private String u_code; // VARCHAR2(10) NOT NULL 업체코드
	private String c_kind; // VARCHAR2(20) NOT NULL 차종
	private String c_name; // VARCHAR2(20) NOT NULL 차량명
	private int c_sum; // NUMBER NOT NULL 금액(1일)
	private int c_charge; // NUMBER NOT NULL 추가요금(1시간당)
	private int c_reserve; // NUMBER NOT NULL 예약가능 차량수
	private String c_img; // VARCHAR2(255) 이미지
	private String c_cont; // VARCHAR2(4000)NOT NULL 내용
	private String review_user_id; // VARCHAR2(15)
	private String review_content; // VARCHAR2(4000)
	private String review_date; // DATE NOT NULL
	
	private String u_name;

	public String getU_name() {
		rentalDAO dao = new rentalDAO();
		u_name = dao.read(u_code).getU_name();
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	

	public rentalcarDTO() {
	}

	private MultipartFile posterMF;

	public void setPosterMF(MultipartFile posterMF) {
		this.posterMF = posterMF;
	}

	public MultipartFile getPosterMF() {
		return posterMF;
	}

	public String getC_code() {
		return c_code;
	}

	public void setC_code(String c_code) {
		this.c_code = c_code;
	}

	public String getU_code() {
		return u_code;
	}

	public void setU_code(String u_code) {
		this.u_code = u_code;
	}

	public String getC_kind() {
		return c_kind;
	}

	public void setC_kind(String c_kind) {
		this.c_kind = c_kind;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public int getC_sum() {
		return c_sum;
	}

	public void setC_sum(int c_sum) {
		this.c_sum = c_sum;
	}

	public int getC_charge() {
		return c_charge;
	}

	public void setC_charge(int c_charge) {
		this.c_charge = c_charge;
	}

	public int getC_reserve() {
		return c_reserve;
	}

	public void setC_reserve(int c_reserve) {
		this.c_reserve = c_reserve;
	}

	public String getC_img() {
		return c_img;
	}

	public void setC_img(String c_img) {
		this.c_img = c_img;
	}

	public String getC_cont() {
		return c_cont;
	}

	public void setC_cont(String c_cont) {
		this.c_cont = c_cont;
	}


	public String getReview_user_id() {
		return review_user_id;
	}

	public void setReview_user_id(String review_user_id) {
		this.review_user_id = review_user_id;
	}

	public String getReview_content() {
		return review_content;
	}

	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}

	public String getReview_date() {
		return review_date;
	}

	public void setReview_date(String review_date) {
		this.review_date = review_date;
	}

	@Override
	public String toString() {
		return "rentalcarDTO [c_code=" + c_code + ", u_code=" + u_code + ", c_kind=" + c_kind + ", c_name=" + c_name
				+ ", c_sum=" + c_sum + ", c_charge=" + c_charge + ", c_reserve=" + c_reserve + ", c_img=" + c_img
				+ ", c_cont=" + c_cont + ", review_user_id=" + review_user_id + ", review_content=" + review_content + ", review_date="
				+ review_date + "]";
	}

}
