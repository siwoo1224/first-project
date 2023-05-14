package kr.co.samjo.product.packagetour;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class packagetourDTO {

	private String pack_no;		//VARCHAR2(10)	 NULL		패키지코드
	private String pack_name;	//VARCHAR2(50)	 NOT NULL	패키지이름
	private String pack_cose;	//VARCHAR2(4000) NOT NULL	여행코스
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date pack_plan_start;	//VARCHAR2(1000) NOT NULL	모집일정
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date pack_plan_end;	//VARCHAR2(1000) NOT NULL	모집일정
	private int pack_price;		//NUMBER 		 NOT NULL 	비용
	private int pack_people;	//NUMBER 		 NOT NULL 	모집인원
	private String pack_cont; 	//VARCHAR2(4000) NOT NULL	내용
	private String pack_img;	//
	private String review_user_id;
	private String review_content;
	private String review_date;
	
	public packagetourDTO() {}
	
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

	private MultipartFile posterMF;

   
    public MultipartFile getPosterMF() {
        return posterMF;
    }
	
	public void setPosterMF(MultipartFile posterMF) {
		this.posterMF = posterMF;
	}

	public String getPack_no() {
		return pack_no;
	}

	public void setPack_no(String pack_no) {
		this.pack_no = pack_no;
	}

	public String getPack_name() {
		return pack_name;
	}

	public void setPack_name(String pack_name) {
		this.pack_name = pack_name;
	}

	public String getPack_cose() {
		return pack_cose;
	}

	public void setPack_cose(String pack_cose) {
		this.pack_cose = pack_cose;
	}

	public Date getPack_plan_start() {
		return pack_plan_start;
	}

	public void setPack_plan_start(Date pack_plan_start) {
		this.pack_plan_start = pack_plan_start;
	}

	public Date getPack_plan_end() {
		return pack_plan_end;
	}

	public void setPack_plan_end(Date pack_plan_end) {
		this.pack_plan_end = pack_plan_end;
	}

	public int getPack_price() {
		return pack_price;
	}

	public void setPack_price(int pack_price) {
		this.pack_price = pack_price;
	}

	public int getPack_people() {
		return pack_people;
	}

	public void setPack_people(int pack_people) {
		this.pack_people = pack_people;
	}

	public String getPack_cont() {
		return pack_cont;
	}

	public void setPack_cont(String pack_cont) {
		this.pack_cont = pack_cont;
	}

	public String getPack_img() {
		return pack_img;
	}

	public void setPack_img(String pack_img) {
		this.pack_img = pack_img;
	}

	@Override
	public String toString() {
		return "packagetourDTO [pack_no=" + pack_no + ", pack_name=" + pack_name + ", pack_cose=" + pack_cose
				+ ", pack_plan_start=" + pack_plan_start + ", pack_plan_end=" + pack_plan_end + ", pack_price="
				+ pack_price + ", pack_people=" + pack_people + ", pack_cont=" + pack_cont + ", pack_img=" + pack_img
				+ ", review_user_id=" + review_user_id + ", review_content=" + review_content + ", review_date="
				+ review_date + ", posterMF=" + posterMF + "]";
	}

}
