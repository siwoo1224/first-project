package kr.co.samjo.product.rental;

import kr.co.samjo.product.rentalcar.rentalcarDTO;
import kr.co.samjo.product.rental.rentalDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kr.co.samjo.product.packagetour.packagetourDTO;
import kr.co.samjo.product.rental.rentalDTO;
import net.utility.DBClose;
import net.utility.DBOpen;
import kr.co.samjo.product.rentalcar.*;
public class rentalDAO {
	private DBOpen dbopen = null;
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private StringBuilder sql = null;

	public rentalDAO() {
		dbopen = new DBOpen();
	}// end

//create
	public int create(rentalDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection(); // DB연결

			sql = new StringBuilder();
			sql.append(" INSERT INTO tb_upche(u_code, u_name, u_phone, u_office, u_img, u_cont) ");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?) ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getU_code());
			pstmt.setString(2, dto.getU_name());
			pstmt.setString(3, dto.getU_phone());
			pstmt.setString(4, dto.getU_office());
			pstmt.setString(5, dto.getU_img());
			pstmt.setString(6, dto.getU_cont());
			cnt = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("렌트카 업체 등록 실패: " + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;
	}// create() end

//list	
	public ArrayList<rentalDTO> list(int start, int end) {
		ArrayList<rentalDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();

			sql.append(" SELECT AA.* ");
			sql.append(" FROM ( ");
			sql.append("        SELECT ROWNUM as RNUM, BB.* ");
			sql.append("        FROM ( ");
			sql.append("               SELECT u_code, u_name, u_phone, u_office, u_img, u_cont ");
			sql.append("               FROM tb_upche ");
			sql.append("               ORDER BY u_code DESC ");
			sql.append("             )BB ");
			sql.append("      ) AA ");
			sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<rentalDTO>();
				do {
					rentalDTO dto = new rentalDTO();
					dto.setU_code(rs.getString("u_code"));
					dto.setU_name(rs.getString("u_name"));
					dto.setU_phone(rs.getString("u_phone"));
					dto.setU_office(rs.getString("u_office"));
					dto.setU_img(rs.getString("u_img"));
					dto.setU_cont(rs.getString("u_cont"));
					list.add(dto);
				} while (rs.next());
			} // if end

		} catch (Exception e) {
			System.out.println("렌트카 업체 페이징 목록 실패: " + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return list;
	}// list() end


	
// reviewtotalRowCount
	public int reviewtotalRowCount(String u_code) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT COUNT(*) FROM tb_review WHERE review_code = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, u_code);
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

	
	
//totalRowCount	
	public int totalRowCount() {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT COUNT(*) FROM tb_upche ");
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

//read	
	public rentalDTO read(String u_code) {
		rentalDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT u_code, u_name, u_phone, u_office, u_img, u_cont ");
			sql.append(" FROM tb_upche ");
			sql.append(" WHERE tb_upche.u_code = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, u_code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new rentalDTO();
				dto.setU_code(rs.getString("u_code"));
				dto.setU_name(rs.getString("u_name"));
				dto.setU_phone(rs.getString("u_phone"));
				dto.setU_office(rs.getString("u_office"));
				dto.setU_img(rs.getString("u_img"));
				dto.setU_cont(rs.getString("u_cont"));
			} // if end

		} catch (Exception e) {
			System.out.println("상세보기실패" + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return dto;
	}// read() end

	

// reviewList() end	
		public ArrayList<rentalDTO> reviewList(String u_code, int start, int end) {
			ArrayList<rentalDTO> list = null;
			try {
				con = dbopen.getConnection();
				sql = new StringBuilder();

				sql.append(" SELECT AA.* ");
				sql.append(" FROM ( ");
				sql.append("        SELECT ROWNUM as RNUM, BB.* ");
				sql.append("        FROM ( ");
				sql.append(
						"               SELECT u_code, u_name, u_phone, u_office, u_img, u_cont, review_no, review_user_id, review_content, review_date ");
				sql.append("               FROM tb_upche left outer join tb_review ");
				sql.append(" 			   ON tb_upche.u_code = tb_review.review_code ");
				sql.append("			   WHERE tb_upche.u_code = ? ");
				sql.append("             )BB ");
				sql.append("      ) AA ");
				sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");

				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, u_code);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);

				rs = pstmt.executeQuery();
				if (rs.next()) {
					list = new ArrayList<rentalDTO>();
					do {
						rentalDTO dto = new rentalDTO();
						dto.setU_code(rs.getString("u_code"));
						dto.setU_name(rs.getString("u_name"));
						dto.setU_phone(rs.getString("u_phone"));
						dto.setU_office(rs.getString("u_office"));
						dto.setU_img(rs.getString("u_img"));
						dto.setU_cont(rs.getString("u_cont"));
						dto.setReview_user_id(rs.getString("review_user_id"));
						dto.setReview_content(rs.getString("review_content"));
						dto.setReview_date(rs.getString("review_date"));
						list.add(dto);
					} while (rs.next());
				} // if end

			} catch (Exception e) {
				System.out.println("렌트카 업체 리뷰 전체 목록 실패: " + e);
			} finally {
				DBClose.close(con, pstmt, rs);
			} // end
			return list;
		}// reviewList() end
	
	
	
	
//update	
	public int update(rentalDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE tb_upche ");
			sql.append(" SET u_code=?, u_name=?, u_phone=?, u_office=?, u_img=?, u_cont=? ");
			sql.append(" WHERE u_code=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getU_code());
			pstmt.setString(2, dto.getU_name());
			pstmt.setString(3, dto.getU_phone());
			pstmt.setString(4, dto.getU_office());
			pstmt.setString(5, dto.getU_img());
			pstmt.setString(6, dto.getU_cont());
			pstmt.setString(7, dto.getU_code());
			
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("렌트카 업체 수정 실패" + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;
	}// update() end

//delete	
	public int delete(String u_code) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" DELETE FROM tb_upche");
			sql.append(" WHERE u_code=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, u_code);
			
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("삭제 실패" + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;
	}// delete() end

}