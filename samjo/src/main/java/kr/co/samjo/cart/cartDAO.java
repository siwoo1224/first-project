package kr.co.samjo.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import kr.co.samjo.product.rentalcar.rentalcarDAO;
import kr.co.samjo.product.rentalcar.rentalcarDTO;
import kr.co.samjo.product.sookso.SooksoDAO;
import kr.co.samjo.product.sookso.SooksoDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

public class cartDAO {

	private DBOpen dbopen = null;
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private StringBuilder sql = null;

	public cartDAO() {
		dbopen = new DBOpen();
	}

	public int create(cartDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection(); // DB연결

			sql = new StringBuilder();

			sql.append(" INSERT INTO tb_cart(c_no, user_id, s_code, cnt, p_cnt, sdate, fdate) ");
			sql.append(" VALUES( cart_seq.nextval, ?, ?, ?, ?, ?, ?) ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getUser_id());
			pstmt.setString(2, dto.getS_code());
			pstmt.setInt(3, dto.getCnt());
			pstmt.setInt(4, dto.getP_cnt());
			pstmt.setString(5, dto.getSdate());
			pstmt.setString(6, dto.getFdate());

			cnt = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("장바구니등록실패" + e);
		} finally {
			DBClose.close(con, pstmt);
		}
		return cnt;
	}

	public int delete(int c_no) {
		int cnt = 0;
		try {
			con = dbopen.getConnection(); // DB연결
			sql = new StringBuilder();
			sql.append(" DELETE FROM tb_cart ");
			sql.append(" WHERE c_no=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, c_no);
			cnt = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("장바구니 목록 삭제 실패:" + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end

		return cnt;
	}

	public ArrayList<cartDTO> list(String user_id) {
		ArrayList<cartDTO> list = null;
		try {
			con = dbopen.getConnection();

			sql = new StringBuilder();
			sql.append(" SELECT c_no, user_id, s_code, cnt, p_cnt, sdate, fdate ");
			sql.append(" FROM TB_CART ");
			sql.append(" WHERE user_id=? ");
			sql.append(" ORDER BY c_no DESC ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, user_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<cartDTO>();
				do {

					cartDTO dto = new cartDTO(); // 한줄담기
					dto.setC_no(rs.getInt("c_no"));
					dto.setUser_id(rs.getString("user_id"));
					dto.setS_code(rs.getString("s_code"));
					dto.setCnt(rs.getInt("cnt"));
					dto.setP_cnt(rs.getInt("p_cnt"));
					dto.setSdate(rs.getString("sdate"));
					if (rs.getString("fdate") != null) {
						dto.setFdate(rs.getString("fdate"));
					}
					list.add(dto); // list에 모으기
				} while (rs.next());
			}

		} catch (Exception e) {
			System.out.println("카트 전체목록 실패:" + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return list;
	}// list() end

	public int delete(String user_id) {
		int cnt = 0;
		try {
			con = dbopen.getConnection(); // DB연결
			sql = new StringBuilder();
			sql.append(" DELETE FROM tb_cart ");
			sql.append(" WHERE user_id=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, user_id);
			cnt = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("예약 완료 목록 삭제 실패:" + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end

		return cnt;
	}

	public ArrayList<String> week(int c_no) {
		ArrayList<String> list = null;
		try {
			con = dbopen.getConnection();

			sql = new StringBuilder();
			sql.append("WITH DT_W AS ( ");
			sql.append("	    SELECT TO_CHAR(TO_DATE(ST_DT, 'YYYY-MM-DD') + LEVEL - 1, 'YYYY-MM-DD') AS DT ");
			sql.append("	    FROM ( SELECT  substr(sdate, 0, 10) AS ST_DT, substr(fdate, 0, 10) AS END_DT ");
			sql.append("	            from tb_cart where c_no = ? ) ");
			sql.append("	    CONNECT BY LEVEL <=  TO_DATE(END_DT, 'YYYY-MM-DD') - TO_DATE(ST_DT, 'YYYY-MM-DD') + 1 ");
			sql.append("	) ");
			sql.append("	SELECT CASE WHEN TO_CHAR(TO_DATE(D.DT),'D') IN ('1','7') ");
			sql.append("	            THEN '주말' ");
			sql.append(" 	            ELSE '평일' END AS WEEK_DAY ");
			sql.append(" 	FROM DT_W D ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, c_no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<String>();
				do {
					list.add(rs.getString("week_day")); // list에 모으기
				} while (rs.next());
			}

		} catch (Exception e) {
			System.out.println("평일 주말 구분 실패:" + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return list;
	}

	public long amount(ArrayList<cartDTO> list) {
		int sum = 0;
		String s_code = null;
		ArrayList<String> tmplist = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			tmplist = week(list.get(i).getC_no());
			s_code = list.get(i).getS_code();
			if (s_code.charAt(0) == 'S') {
				SooksoDAO sdao = new SooksoDAO();
				SooksoDTO sdto = new SooksoDTO();
				sdto = sdao.readroom(s_code);
				for (int j = 0; j < tmplist.size(); j++) {
					if (tmplist.get(j) == "주말") {
						sum += sdto.getRoom_ep();
					} else {
						sum += sdto.getRoom_dp();
					}
				}
			} else if (s_code.charAt(0) == 'C') {
				rentalcarDAO rcdao = new rentalcarDAO();
				rentalcarDTO rcdto = new rentalcarDTO();
				rcdto = rcdao.read(s_code);
				sum += rcdto.getC_sum();
				if (tmplist.size() > 1) {
					sum += (tmplist.size() - 1) * 24;
				}
			} else {
				sum += 0;
			}
		}
		return sum;
	}

}
