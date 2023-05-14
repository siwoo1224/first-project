package kr.co.samjo.product.maszip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import kr.co.samjo.cart.cartDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

public class MaszipDAO {

	private DBOpen dbopen = null;
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private StringBuilder sql = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd. a HH:mm:ss");

	public MaszipDAO() {
		dbopen = new DBOpen();
	}// end

	// 여행지 추가
	public int create(MaszipDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection(); // DB연결

			sql = new StringBuilder();
			sql.append(
					" INSERT INTO tb_maszip(m_code, m_kind, m_name, m_addr, m_addr2, m_phone, m_img, m_content) ");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?) ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getM_code());
			pstmt.setString(2, dto.getM_kind());
			pstmt.setString(3, dto.getM_name());
			pstmt.setString(4, dto.getM_addr());
			pstmt.setString(5, dto.getM_addr2());
			pstmt.setString(6, dto.getM_phone());
			pstmt.setString(7, dto.getM_img());
			pstmt.setString(8, dto.getM_content());
			cnt = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("음식집 등록 실패: " + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;
	}// create() end

	
	// 맛집 목록
	public ArrayList<MaszipDTO> list(int start, int end, String word) {
		ArrayList<MaszipDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			
			word = word.trim();
 
			sql.append(" SELECT AA.* ");
			sql.append(" FROM ( ");
			sql.append("        SELECT ROWNUM as RNUM, BB.* ");
			sql.append("        FROM ( ");
			sql.append("               SELECT m_code, m_kind, m_name, m_addr, m_addr2, m_phone, m_content, m_img ");
			sql.append("               FROM tb_maszip ");
			
			if(word.length()!=0) {
				String search="";
	            search += " WHERE m_name LIKE '%" + word + "%' ";
	            sql.append(search);   
			}
			
			sql.append("               ORDER BY m_code DESC ");
			sql.append("             )BB ");
			sql.append("      ) AA ");
			sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");
	        
	          
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			

			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<MaszipDTO>();
				do {
					MaszipDTO dto = new MaszipDTO();
					dto.setM_code(rs.getString("m_code"));
					dto.setM_kind(rs.getString("m_kind"));
					dto.setM_name(rs.getString("m_name"));
					dto.setM_addr(rs.getString("m_addr"));
					dto.setM_addr2(rs.getString("m_addr2"));
					dto.setM_phone(rs.getString("m_phone"));
					dto.setM_content(rs.getString("m_content"));
					dto.setM_img(rs.getString("m_img"));
					list.add(dto);
				} while (rs.next());
			} // if end

		} catch (Exception e) {
			System.out.println("맛집 페이징 목록 실패: " + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return list;
	}// list() end



	// 맛집 글 갯수
	public int totalRowCount() {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT COUNT(*) FROM tb_maszip ");
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


	//맛집 상세보기
	public MaszipDTO read(String m_code) {
		MaszipDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();

			sql.append(
					" SELECT m_code, m_kind, m_name, m_addr, m_phone, m_addr2, m_content, m_img, review_user_id, review_content, review_date ");
			sql.append(" FROM tb_maszip left outer join tb_review ");
			sql.append(" ON tb_maszip.m_code = tb_review.review_code ");
			sql.append(" WHERE tb_maszip.m_code = ? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, m_code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new MaszipDTO();
				dto.setM_code(rs.getString("m_code"));
				dto.setM_kind(rs.getString("m_kind"));
				dto.setM_name(rs.getString("m_name"));
				dto.setM_addr(rs.getString("m_addr"));
				dto.setM_addr2(rs.getString("m_addr2"));
				dto.setM_phone(rs.getString("m_phone"));
				dto.setM_content(rs.getString("m_content"));
				dto.setM_img(rs.getString("m_img"));
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

	public ArrayList<MaszipDTO> reviewList(String m_code, int start, int end) {
		ArrayList<MaszipDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();

			sql.append(" SELECT AA.* ");
			sql.append(" FROM ( ");
			sql.append("        SELECT ROWNUM as RNUM, BB.* ");
			sql.append("        FROM ( ");
			sql.append(
					"               SELECT m_code, m_kind, m_name, m_addr, m_addr2, m_phone, m_content, m_img, review_no, review_user_id, review_content, review_date ");
			sql.append("               FROM tb_maszip left outer join tb_review ");
			sql.append(" 			   ON tb_maszip.m_code = tb_review.review_code ");
			sql.append("			   WHERE tb_maszip.m_code = ? ");
			sql.append("             )BB ");
			sql.append("      ) AA ");
			sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, m_code);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<MaszipDTO>();
				do {
					MaszipDTO dto = new MaszipDTO();
					dto.setM_code(rs.getString("m_code"));
					dto.setM_kind(rs.getString("m_kind"));
					dto.setM_name(rs.getString("m_name"));
					dto.setM_addr(rs.getString("m_addr"));
					dto.setM_addr2(rs.getString("m_addr2"));
					dto.setM_phone(rs.getString("m_phone"));
					dto.setM_content(rs.getString("m_content"));
					dto.setM_img(rs.getString("m_img"));
					dto.setReview_user_id(rs.getString("review_user_id"));
					dto.setReview_content(rs.getString("review_content"));
					dto.setReview_date(rs.getString("review_date"));
					list.add(dto);
				} while (rs.next());
			} // if end

		} catch (Exception e) {
			System.out.println("맛집 리뷰 전체 목록 실패: " + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return list;
	}// reviewList() end

	public int reviewtotalRowCount(String m_code) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT COUNT(*) FROM tb_review WHERE review_code = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, m_code);
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
	public int update(MaszipDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE tb_maszip ");
			sql.append(
					" SET m_kind=?, m_name=?, m_addr=?, m_addr2=?, m_phone=?, m_content=?, m_img=? ");
			sql.append(" WHERE m_code=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getM_kind());
			pstmt.setString(2, dto.getM_name());
			pstmt.setString(3, dto.getM_addr());
			pstmt.setString(4, dto.getM_addr2());
			pstmt.setString(5, dto.getM_phone());
			pstmt.setString(6, dto.getM_content());
			pstmt.setString(7, dto.getM_img());
			pstmt.setString(8, dto.getM_code());
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("맛집 수정 실패" + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;
	}// update() end

	// 여행지 삭제
	public int delete(String m_code) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" DELETE FROM tb_maszip");
			sql.append(" WHERE m_code=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, m_code);
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("삭제 실패" + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;
	}// delete() end
	
	public int create(cartDTO dto) {
		int cnt=0;
		try {
			con=dbopen.getConnection(); //DB연결
			
			sql=new StringBuilder();

			sql.append(" INSERT INTO tb_cart(c_no, user_id, s_code, cnt, p_cnt, sdate, fdate) ");
			sql.append(" VALUES( cart_seq.nextval, ?, ?, ?, ?, ?, ?) ");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getUser_id());
			pstmt.setString(2, dto.getS_code());
			pstmt.setInt(3, dto.getCnt());
			pstmt.setInt(4, dto.getP_cnt());
			pstmt.setString(5, dto.getSdate());
			pstmt.setString(6, dto.getFdate());
			
			cnt=pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("장바구니등록실패" + e);
		}finally {
			DBClose.close(con,pstmt);
		}
		return cnt;
	}

}
