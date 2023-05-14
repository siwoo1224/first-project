package kr.co.samjo.product.rentalcar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import kr.co.samjo.product.rentalcar.rentalcarDTO;
import kr.co.samjo.product.rental.rentalDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

public class rentalcarDAO {
	private DBOpen dbopen = null;
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private StringBuilder sql = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd. a HH:mm:ss");

	public rentalcarDAO() {
		dbopen = new DBOpen();
	}// end

//create
	public int create(rentalcarDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection(); // DB연결

			sql = new StringBuilder();
			sql.append(" INSERT INTO tb_car(c_code, u_code, c_kind, c_name, c_sum, c_charge, c_reserve, c_img, c_cont ) ");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ? ) ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getC_code());
			pstmt.setString(2, dto.getU_code());
			pstmt.setString(3, dto.getC_kind());
			pstmt.setString(4, dto.getC_name());
			pstmt.setInt(5, dto.getC_sum());
			pstmt.setInt(6, dto.getC_charge());
			pstmt.setInt(7, dto.getC_reserve());
			pstmt.setString(8, dto.getC_img());
			pstmt.setString(9, dto.getC_cont());
			cnt = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("렌트카 등록 실패: " + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;
	}// create() end

//list	
	public ArrayList<rentalcarDTO> list(int start, int end) {
		ArrayList<rentalcarDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();

			sql.append(" SELECT AA.* ");
			sql.append(" FROM ( ");
			sql.append("        SELECT ROWNUM as RNUM, BB.* ");
			sql.append("        FROM ( ");
			sql.append("               SELECT c_code, u_code, c_kind, c_name, c_sum, c_charge, c_reserve, c_img, c_cont ");
			sql.append("               FROM tb_car ");
			sql.append("               ORDER BY c_code DESC ");
			sql.append("             )BB ");
			sql.append("      ) AA ");
			sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<rentalcarDTO>();
				do {
					rentalcarDTO dto = new rentalcarDTO();
					dto.setC_code(rs.getString("c_code"));
					dto.setU_code(rs.getString("u_code"));
					dto.setC_kind(rs.getString("c_kind"));
					dto.setC_name(rs.getString("c_name"));
					dto.setC_sum(rs.getInt("c_sum"));
					dto.setC_charge(rs.getInt("c_charge"));
					dto.setC_reserve(rs.getInt("c_reserve"));
					dto.setC_img(rs.getString("c_img"));
					dto.setC_cont(rs.getString("c_cont"));
					list.add(dto);
				} while (rs.next());
			} // if end

		} catch (Exception e) {
			System.out.println("렌트카 페이징 목록 실패: " + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return list;
	}// list() end

// reviewtotalRowCount
	public int reviewtotalRowCount(String c_code) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT COUNT(*) FROM tb_review WHERE review_code = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, c_code);
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
			sql.append(" SELECT COUNT(*) FROM tb_car ");
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
	public rentalcarDTO read(String c_code) {
		rentalcarDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT c_code, u_code, c_kind, c_name, c_sum, c_charge, c_reserve, c_img, c_cont ");
			sql.append(" FROM tb_car ");
			sql.append(" WHERE tb_car.c_code = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, c_code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new rentalcarDTO();
				dto.setC_code(rs.getString("c_code"));
				dto.setU_code(rs.getString("u_code"));
				dto.setC_kind(rs.getString("c_kind"));
				dto.setC_name(rs.getString("c_name"));
				dto.setC_sum(rs.getInt("c_sum"));
				dto.setC_charge(rs.getInt("c_charge"));
				dto.setC_reserve(rs.getInt("c_reserve"));
				dto.setC_img(rs.getString("c_img"));
				dto.setC_cont(rs.getString("c_cont"));
			} // if end

		} catch (Exception e) {
			System.out.println("상세보기실패" + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return dto;
	}// read() end

	
	
	// reviewList() end
	public ArrayList<rentalcarDTO> reviewList(String c_code, int start, int end) {
		ArrayList<rentalcarDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();

			sql.append(" SELECT AA.* ");
			sql.append(" FROM ( ");
			sql.append("        SELECT ROWNUM as RNUM, BB.* ");
			sql.append("        FROM ( ");
			sql.append(
					"               SELECT c_code, c_kind, c_name, c_sum, c_charge, c_reserve, c_img, c_cont, review_no, review_user_id, review_content, review_date ");
			sql.append("               FROM tb_car left outer join tb_review ");
			sql.append(" 			   ON tb_car.c_code = tb_review.review_code ");
			sql.append("			   WHERE tb_car.c_code = ? ");
			sql.append("             )BB ");
			sql.append("      ) AA ");
			sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, c_code);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<rentalcarDTO>();
				do {
					rentalcarDTO dto = new rentalcarDTO();
					dto.setC_code(rs.getString("c_code"));
					dto.setU_code(rs.getString("u_code"));
					dto.setC_kind(rs.getString("c_kind"));
					dto.setC_name(rs.getString("c_name"));
					dto.setC_sum(rs.getInt("c_sum"));
					dto.setC_charge(rs.getInt("c_charge"));
					dto.setC_reserve(rs.getInt("c_reserve"));
					dto.setC_img(rs.getString("c_img"));
					dto.setC_cont(rs.getString("c_cont"));
					dto.setReview_user_id(rs.getString("review_user_id"));
					dto.setReview_content(rs.getString("review_content"));
					dto.setReview_date(rs.getString("review_date"));
					list.add(dto);
				} while (rs.next());
			} // if end

		} catch (Exception e) {
			System.out.println("렌트카 리뷰 전체 목록 실패: " + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return list;
	}// reviewList() end

	
	
//update	
	public int update(rentalcarDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE tb_car ");
			sql.append(" SET c_code=?, c_kind=?, c_name=?, c_sum=?, c_charge=?, c_reserve=?, c_img=?, c_cont=? ");
			sql.append(" WHERE c_code=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getC_code());
			pstmt.setString(2, dto.getC_kind());
			pstmt.setString(3, dto.getC_name());
			pstmt.setInt(4, dto.getC_sum());
			pstmt.setInt(5, dto.getC_charge());
			pstmt.setInt(6, dto.getC_reserve());
			pstmt.setString(7, dto.getC_img());
			pstmt.setString(8, dto.getC_cont());
			pstmt.setString(11, dto.getC_code());
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("렌트카 수정 실패" + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;
	}// update() end

//delete	
	public int delete(String c_code) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" DELETE FROM tb_car");
			sql.append(" WHERE c_code=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, c_code);
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("삭제 실패" + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;
	}// delete() end

}
