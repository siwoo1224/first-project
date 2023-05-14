package kr.co.samjo.product.maszip;

import org.springframework.web.multipart.MultipartFile;

public class MaszipDTO {

	private String m_code;
	private String m_kind;
	private String m_name;
	private String m_addr;
	private String m_addr2;
	private String m_phone;
	private String m_content;
	private String m_img;
	
	public MaszipDTO() {}
	
private MultipartFile posterMF;
	
	public MultipartFile getPosterMF() {
        return posterMF;
    }

    public void setPosterMF(MultipartFile posterMF) {
        this.posterMF = posterMF;
    }

	public String getM_code() {
		return m_code;
	}

	public void setM_code(String m_code) {
		this.m_code = m_code;
	}

	public String getM_kind() {
		return m_kind;
	}

	public void setM_kind(String m_kind) {
		this.m_kind = m_kind;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_addr() {
		return m_addr;
	}

	public void setM_addr(String m_addr) {
		this.m_addr = m_addr;
	}

	public String getM_addr2() {
		return m_addr2;
	}

	public void setM_addr2(String m_addr2) {
		this.m_addr2 = m_addr2;
	}

	public String getM_phone() {
		return m_phone;
	}

	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}

	public String getM_content() {
		return m_content;
	}

	public void setM_content(String m_content) {
		this.m_content = m_content;
	}

	public String getM_img() {
		return m_img;
	}

	public void setM_img(String m_img) {
		this.m_img = m_img;
	}
	
	private String review_user_id;
	private String review_content;
	private String review_date;
	private String word;

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

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	
    
}
