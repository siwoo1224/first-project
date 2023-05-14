package kr.co.samjo.tour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.utility.DBClose;
import net.utility.DBOpen;

public class TourDAO {

	private DBOpen dbopen = null;
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private StringBuilder sql = null;

	public TourDAO() {
		dbopen = new DBOpen();
	}// end

	// 여행지 추가
	public int create(TourDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection(); // DB연결

			sql = new StringBuilder();
			sql.append(
					" INSERT INTO tb_tour(t_cn, t_name, t_addr, t_dividecn, t_tel, t_link, t_sche, t_car, t_img, t_cont, t_rdate) ");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate) ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getT_cn());
			pstmt.setString(2, dto.getT_name());
			pstmt.setString(3, dto.getT_addr());
			pstmt.setInt(4, dto.getT_dividecn());
			pstmt.setString(5, dto.getT_tel());
			pstmt.setString(6, dto.getT_link());
			pstmt.setString(7, dto.getT_sche());
			pstmt.setString(8, dto.getT_car());
			pstmt.setString(9, dto.getT_img());
			pstmt.setString(10, dto.getT_cont());
			cnt = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("여행지 등록 실패: " + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;
	}// create() end

	
	// 관광지 목록
	public ArrayList<TourDTO> list(int start, int end, String word) {
		ArrayList<TourDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			
			word = word.trim();
			
			/*
			if(word.length()==0) {
				//검색을 하지 않는 경우
	        	sql.append(" SELECT AA.* ");
	  			sql.append(" FROM ( ");
	  			sql.append("        SELECT ROWNUM as RNUM, BB.* ");
	  			sql.append("        FROM ( ");
	  			sql.append("               SELECT t_cn, t_name, t_addr, t_dividecn, t_tel, t_link, t_sche, t_car, t_img, t_cont, t_rdate ");
	  			sql.append("               FROM tb_tour ");
	  			sql.append(" 			   WHERE t_dividecn = 1 ");
	  			sql.append("               ORDER BY t_cn DESC ");
	  			sql.append("             )BB ");
	  			sql.append("      ) AA ");
	  			sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");
	  			
			} else {
	            //검색을 하는 경우
	        	sql.append(" SELECT AA.* ");
		  		sql.append(" FROM ( ");
		  		sql.append("        SELECT ROWNUM as RNUM, BB.* ");
		  		sql.append("        FROM ( ");
		  		sql.append("               SELECT t_cn, t_name, t_addr, t_dividecn, t_tel, t_link, t_sche, t_car, t_img, t_cont, t_rdate ");
		  		sql.append("               FROM tb_tour ");
		  		sql.append(" 			   WHERE t_dividecn = 1 ");
	            
	            String search="";
	            search += " AND t_name LIKE '%" + word + "%' ";
	            sql.append(search);        
	            
	            sql.append("               ORDER BY t_cn DESC ");
	  			sql.append("             )BB ");
	  			sql.append("      ) AA ");
	  			sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");
	          }//if end
*/

	        
			sql.append(" SELECT AA.* ");
			sql.append(" FROM ( ");
			sql.append("        SELECT ROWNUM as RNUM, BB.* ");
			sql.append("        FROM ( ");
			sql.append("               SELECT t_cn, t_name, t_addr, t_dividecn, t_tel, t_link, t_sche, t_car, t_img, t_cont, t_rdate ");
			sql.append("               FROM tb_tour ");
			sql.append(" 			   WHERE t_dividecn = 1 ");
			
			if(word.length()!=0) {
				String search="";
	            search += " AND t_name LIKE '%" + word + "%' ";
	            sql.append(search);   
			}
			
			sql.append("               ORDER BY t_cn DESC ");
			sql.append("             )BB ");
			sql.append("      ) AA ");
			sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");
	        
	          
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			

			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<TourDTO>();
				do {
					TourDTO dto = new TourDTO();
					dto.setT_cn(rs.getString("t_cn"));
					dto.setT_name(rs.getString("t_name"));
					dto.setT_addr(rs.getString("t_addr"));
					dto.setT_dividecn(rs.getInt("t_dividecn"));
					dto.setT_tel(rs.getString("t_tel"));
					dto.setT_link(rs.getString("t_link"));
					dto.setT_sche(rs.getString("t_sche"));
					dto.setT_car(rs.getString("t_car"));
					dto.setT_img(rs.getString("t_img"));
					dto.setT_cont(rs.getString("t_cont"));
					dto.setT_rdate(rs.getString("t_rdate"));
					list.add(dto);
				} while (rs.next());
			} // if end

		} catch (Exception e) {
			System.out.println("여행지 페이징 목록 실패: " + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return list;
	}// list() end



	// 관광지 글 갯수
	public int totalRowCount() {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT COUNT(*) FROM tb_tour WHERE t_dividecn = 1 ");
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			} // if end
		} catch (Exception e) {
			System.out.println("전체 행 갯수:" + e);
		} finally {
			DBClose.close(con, pstmt);
		}
		return cnt;
	}// totalRowCount() end

	// 문화행사 목록
	public ArrayList<TourDTO> list2(int start, int end, String word) {
		ArrayList<TourDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			
			word = word.trim();
			
			sql.append(" SELECT AA.* ");
			sql.append(" FROM ( ");
			sql.append("        SELECT ROWNUM as RNUM, BB.* ");
			sql.append("        FROM ( ");
			sql.append(
					"               SELECT t_cn, t_name, t_addr, t_dividecn, t_tel, t_link, t_sche, t_car, t_img, t_cont, t_rdate ");
			sql.append("               FROM tb_tour ");
			sql.append(" 			   WHERE t_dividecn = 2 ");
			
			if(word.length()!=0) {
				String search="";
	            search += " AND t_name LIKE '%" + word + "%' ";
	            sql.append(search);   
			}
			
			sql.append("               ORDER BY t_cn DESC ");
			sql.append("             )BB ");
			sql.append("      ) AA ");
			sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");
			
			/*
			sql.append(" SELECT AA.* ");
			sql.append(" FROM ( ");
			sql.append("        SELECT ROWNUM as RNUM, BB.* ");
			sql.append("        FROM ( ");
			sql.append(
					"               SELECT t_cn, t_name, t_addr, t_dividecn, t_tel, t_link, t_sche, t_car, t_img, t_cont, t_rdate ");
			sql.append("               FROM tb_tour ");
			sql.append(" 			   WHERE t_dividecn = 2 ");
			sql.append("               ORDER BY t_cn DESC ");
			sql.append("             )BB ");
			sql.append("      ) AA ");
			sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");
			*/

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<TourDTO>();
				do {
					TourDTO dto = new TourDTO();
					dto.setT_cn(rs.getString("t_cn"));
					dto.setT_name(rs.getString("t_name"));
					dto.setT_addr(rs.getString("t_addr"));
					dto.setT_dividecn(rs.getInt("t_dividecn"));
					dto.setT_tel(rs.getString("t_tel"));
					dto.setT_link(rs.getString("t_link"));
					dto.setT_sche(rs.getString("t_sche"));
					dto.setT_car(rs.getString("t_car"));
					dto.setT_img(rs.getString("t_img"));
					dto.setT_cont(rs.getString("t_cont"));
					dto.setT_rdate(rs.getString("t_rdate"));
					list.add(dto);
				} while (rs.next());
			} // if end

		} catch (Exception e) {
			System.out.println("문화행사 전체 목록 실패: " + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return list;
	}// list2() end

	// 문화행사 글 갯수
	public int totalRowCount2() {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT COUNT(*) FROM tb_tour WHERE t_dividecn = 2 ");
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			} // if end
		} catch (Exception e) {
			System.out.println("전체 행 갯수:" + e);
		} finally {
			DBClose.close(con, pstmt);
		}
		return cnt;
	}// totalRowCount2() end

	// 여행지 상세보기
	public TourDTO read(String t_cn) {
		TourDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();

			sql.append(
					" SELECT t_cn, t_name, t_addr, t_dividecn, t_tel, t_link, t_sche, t_car, t_img, t_cont, t_rdate, review_user_id, review_content, review_date ");
			sql.append(" FROM tb_tour left outer join tb_review ");
			sql.append(" ON tb_tour.t_cn = tb_review.review_code ");
			sql.append(" WHERE tb_tour.t_cn = ? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, t_cn);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new TourDTO();
				dto.setT_cn(rs.getString("t_cn"));
				dto.setT_name(rs.getString("t_name"));
				dto.setT_addr(rs.getString("t_addr"));
				dto.setT_dividecn(rs.getInt("t_dividecn"));
				dto.setT_tel(rs.getString("t_tel"));
				dto.setT_link(rs.getString("t_link"));
				dto.setT_sche(rs.getString("t_sche"));
				dto.setT_car(rs.getString("t_car"));
				dto.setT_img(rs.getString("t_img"));
				dto.setT_cont(rs.getString("t_cont"));
				dto.setT_rdate(rs.getString("t_rdate"));
				dto.setReview_user_id(rs.getString("review_user_id"));
				dto.setReview_content(rs.getString("review_content"));
				dto.setReview_date(rs.getString("review_date"));
			} // if end

		} catch (Exception e) {
			System.out.println("상세보기실패" + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return dto;
	}// read() end

	public ArrayList<TourDTO> reviewList(String t_cn, int start, int end) {
		ArrayList<TourDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();

			sql.append(" SELECT AA.* ");
			sql.append(" FROM ( ");
			sql.append("        SELECT ROWNUM as RNUM, BB.* ");
			sql.append("        FROM ( ");
			sql.append(
					"               SELECT t_cn, t_name, t_addr, t_dividecn, t_tel, t_link, t_sche, t_car, t_img, t_cont, t_rdate, review_no, review_user_id, review_content, review_date ");
			sql.append("               FROM tb_tour left outer join tb_review ");
			sql.append(" 			   ON tb_tour.t_cn = tb_review.review_code ");
			sql.append("			   WHERE tb_tour.t_cn = ? ");
			sql.append("             )BB ");
			sql.append("      ) AA ");
			sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, t_cn);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<TourDTO>();
				do {
					TourDTO dto = new TourDTO();
					dto.setT_cn(rs.getString("t_cn"));
					dto.setT_name(rs.getString("t_name"));
					dto.setT_addr(rs.getString("t_addr"));
					dto.setT_dividecn(rs.getInt("t_dividecn"));
					dto.setT_tel(rs.getString("t_tel"));
					dto.setT_link(rs.getString("t_link"));
					dto.setT_sche(rs.getString("t_sche"));
					dto.setT_car(rs.getString("t_car"));
					dto.setT_img(rs.getString("t_img"));
					dto.setT_cont(rs.getString("t_cont"));
					dto.setT_rdate(rs.getString("t_rdate"));
					dto.setReview_user_id(rs.getString("review_user_id"));
					dto.setReview_content(rs.getString("review_content"));
					dto.setReview_date(rs.getString("review_date"));
					list.add(dto);
				} while (rs.next());
			} // if end

		} catch (Exception e) {
			System.out.println("여행지 리뷰 전체 목록 실패: " + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return list;
	}// reviewList() end

	// 문화행사 글 갯수
	public int reviewtotalRowCount(String t_cn) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT COUNT(*) FROM tb_review WHERE review_code = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, t_cn);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			} // if end
		} catch (Exception e) {
			System.out.println("전체 행 갯수:" + e);
		} finally {
			DBClose.close(con, pstmt);
		}
		return cnt;
	}// reviewtotalRowCount() end

	// 여행지 수정
	public int update(TourDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE tb_tour ");
			sql.append(
					" SET t_name=?, t_addr=?, t_dividecn=?, t_tel=?, t_link=?, t_sche=?, t_car=?, t_img=?, t_cont=?, t_rdate=sysdate ");
			sql.append(" WHERE t_cn=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getT_name());
			pstmt.setString(2, dto.getT_addr());
			pstmt.setInt(3, dto.getT_dividecn());
			pstmt.setString(4, dto.getT_tel());
			pstmt.setString(5, dto.getT_link());
			pstmt.setString(6, dto.getT_sche());
			pstmt.setString(7, dto.getT_car());
			pstmt.setString(8, dto.getT_img());
			pstmt.setString(9, dto.getT_cont());
			pstmt.setString(10, dto.getT_cn());
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("여행지 수정 실패" + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;
	}// update() end

	// 여행지 삭제
	public int delete(String t_cn) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" DELETE FROM tb_tour");
			sql.append(" WHERE t_cn=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, t_cn);
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("삭제 실패" + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;
	}// delete() end

}
