package kr.co.samjo.tour;

import org.springframework.web.multipart.MultipartFile;

public class TourDTO {
	private String t_cn;
	private String t_name;
	private String t_addr;
	private int t_dividecn;
	private String t_tel;
	private String t_link;
	private String t_sche;
	private String t_car;
	private String t_img;
	private String t_cont;
	private String t_rdate;
	private String review_user_id;
	private String review_content;
	private String review_date;
	private String word;
	

	public TourDTO() {
	}

	private MultipartFile posterMF;
	
	public MultipartFile getPosterMF() {
        return posterMF;
    }

    public void setPosterMF(MultipartFile posterMF) {
        this.posterMF = posterMF;
    }
	
	public String getT_cn() {
		return t_cn;
	}

	public void setT_cn(String t_cn) {
		this.t_cn = t_cn;
	}

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	public String getT_addr() {
		return t_addr;
	}

	public void setT_addr(String t_addr) {
		this.t_addr = t_addr;
	}

	public int getT_dividecn() {
		return t_dividecn;
	}

	public void setT_dividecn(int t_dividecn) {
		this.t_dividecn = t_dividecn;
	}

	public String getT_tel() {
		return t_tel;
	}

	public void setT_tel(String t_tel) {
		this.t_tel = t_tel;
	}

	public String getT_link() {
		return t_link;
	}

	public void setT_link(String t_link) {
		this.t_link = t_link;
	}

	public String getT_sche() {
		return t_sche;
	}

	public void setT_sche(String t_sche) {
		this.t_sche = t_sche;
	}

	public String getT_car() {
		return t_car;
	}

	public void setT_car(String t_car) {
		this.t_car = t_car;
	}

	public String getT_img() {
		return t_img;
	}

	public void setT_img(String t_img) {
		this.t_img = t_img;
	}

	public String getT_cont() {
		return t_cont;
	}

	public void setT_cont(String t_cont) {
		this.t_cont = t_cont;
	}

	public String getT_rdate() {
		return t_rdate;
	}

	public void setT_rdate(String t_rdate) {
		this.t_rdate = t_rdate;
	}

	
	public String getReview_user_id() {
		return review_user_id;
	}

	public void setReview_user_id(String review_user_id) {
		this.review_user_id = review_user_id;
	}

	public String getReview_date() {
		return review_date;
	}

	public void setReview_date(String review_date) {
		this.review_date = review_date;
	}

	public String getReview_content() {
		return review_content;
	}

	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	
	

}
