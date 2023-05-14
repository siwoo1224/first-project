package kr.co.samjo.admin;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class adminDTO {

	/* 여행지 시작 */
	/*
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

	public adminDTO() {
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
	}*/
	/* 여행지 끝 */

	/* 공지사항 시작 */
	/*private int board_no; // NUMBER NULL 일련번호
	private String board_title; // VARCHAR2(200) NOT NULL 제목
	private String board_content; // VARCHAR2(4000) NOT NULL 내용
	private String board_date; // date NOT NULL 작성일
	private int board_readcnt;

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public String getBoard_date() {
		return board_date;
	}

	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}

	public int getBoard_readcnt() {
		return board_readcnt;
	}

	public void setBoard_readcnt(int board_readcnt) {
		this.board_readcnt = board_readcnt;
	}*/

	/* 공지사항 끝 */

	/* 패키지투어 시작 */
	/*private String pack_no; // VARCHAR2(10) NULL 패키지코드
	private String pack_name; // VARCHAR2(50) NOT NULL 패키지이름
	private String pack_cose; // VARCHAR2(4000) NOT NULL 여행코스
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date pack_plan_start; // VARCHAR2(1000) NOT NULL 모집일정
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date pack_plan_end; // VARCHAR2(1000) NOT NULL 모집일정
	private int pack_price; // NUMBER NOT NULL 비용
	private int pack_people; // NUMBER NOT NULL 모집인원
	private String pack_cont; // VARCHAR2(4000) NOT NULL 내용
	private String pack_img; //
	private String review_user_id;
	private String review_content;
	private String review_date;


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
	}*/
	/* 패키지투어 끝 */

	/* 렌트카업체 시작 */
	/*private String u_code; // VARCHAR2(10) NOT NULL 업체코드
	private String u_name; // VARCHAR2(20) NOT NULL 업체명
	private String u_phone; // VARCHAR2(20) NOT NULL 연락처
	private String u_office;// VARCHAR2(200) NOT NULL 사무실
	private String u_img;
	private String u_cont;

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
	}*/
	/* 렌트카업체 끝 */

	/* 렌트카 시작 */
	/*private String c_code; // VARCHAR2(10) NOT NULL 차량코드
	private String c_kind; // VARCHAR2(20) NOT NULL 차종
	private String c_name; // VARCHAR2(20) NOT NULL 차량명
	private int c_sum; // NUMBER NOT NULL 금액(1일)
	private int c_charge; // NUMBER NOT NULL 추가요금(1시간당)
	private int c_reserve; // NUMBER NOT NULL 예약가능 차량수
	private String c_img; // VARCHAR2(255) 이미지
	private String c_cont; // VARCHAR2(4000)NOT NULL 내용

	public String getC_code() {
		return c_code;
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

	}*/
	/* 렌트카 끝 */

	/* 자유게시판 시작 */
	/* 자유게시판 끝 */

}
