package kr.co.samjo.product.rental;

import org.springframework.web.multipart.MultipartFile;
import kr.co.samjo.product.rentalcar.*;

public class rentalDTO {

	private String u_code;	//VARCHAR2(10)	NOT NULL	업체코드
	private String u_name;	//VARCHAR2(20)	NOT NULL	업체명
	private String u_phone;	//VARCHAR2(20) 	NOT NULL	연락처
	private String u_office;//VARCHAR2(200)	NOT NULL	사무실(주소)
	private String u_img;
	private String u_cont;
	private String review_user_id;
	private String review_content;
	private String review_date;
	

	private MultipartFile posterMF;
	
	public void setPosterMF(MultipartFile posterMF) {
		this.posterMF = posterMF;
	}
   
    public MultipartFile getPosterMF() {
        return posterMF;
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

	public String getU_img() {
		return u_img;
	}

	public void setU_img(String u_img) {
		this.u_img = u_img;
	}

	public String getU_cont() {
		return u_cont;
	}

	public void setU_cont(String u_cont) {
		this.u_cont = u_cont;
	}

	public rentalDTO() {}

	public String getU_code() {
		return u_code;
	}

	public void setU_code(String u_code) {
		this.u_code = u_code;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_phone() {
		return u_phone;
	}

	public void setU_phone(String u_phone) {
		this.u_phone = u_phone;
	}

	public String getU_office() {
		return u_office;
	}

	public void setU_office(String u_office) {
		this.u_office = u_office;
	}

	@Override
	public String toString() {
		return "rentalDTO [u_code=" + u_code + ", u_name=" + u_name + ", u_phone=" + u_phone + ", u_office=" + u_office
				+ ", u_img=" + u_img + ", u_cont=" + u_cont + ", review_user_id=" + review_user_id + ", review_content="
				+ review_content + ", review_date=" + review_date + "]";
	}


	
	
	
}
