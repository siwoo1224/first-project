package kr.co.samjo.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.utility.DBClose;
import net.utility.DBOpen;

public class reviewDAO {
	private DBOpen dbopen = null;
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private StringBuilder sql = null;
	
	public reviewDAO() {
		dbopen = new DBOpen();
	}//end
	
	public int create(reviewDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection(); // DB연결

			sql = new StringBuilder();
			sql.append(" INSERT INTO tb_review(review_no, review_user_id, review_res_no, review_code, review_asterion, review_date, review_content) ");
			sql.append(" VALUES(review_no.nextval, ?, ?, ?, ?, sysdate, ?) ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getReview_user_id());
			pstmt.setString(2, dto.getReview_res_no());
			pstmt.setString(3, dto.getReview_code());
			pstmt.setInt(4, dto.getReview_asterion());
			pstmt.setString(5, dto.getReview_content());
			cnt = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("리뷰 등록 실패: " + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;
	}// create() end
	
}
