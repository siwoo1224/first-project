package kr.co.samjo.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import kr.co.samjo.bbs.notice.noticeDTO;
import kr.co.samjo.board2.boardDTO;
import kr.co.samjo.member.MemberDTO;
import kr.co.samjo.product.sookso.SooksoDTO;
import kr.co.samjo.product.maszip.MaszipDTO;
import kr.co.samjo.product.packagetour.packagetourDTO;
import kr.co.samjo.product.packagetour.packagetourDTO;
import kr.co.samjo.product.rental.rentalDTO;
import kr.co.samjo.product.rentalcar.rentalcarDTO;
import kr.co.samjo.product.sookso.SooksoDTO;
import kr.co.samjo.tour.TourDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

public class adminDAO {
	private DBOpen dbopen = null;
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private StringBuilder sql = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd. a HH:mm:ss");

	public adminDAO() {
		dbopen = new DBOpen();
	}// end

	// 렌트카 업체 목록
	public ArrayList<rentalDTO> Upchelist(int start, int end) {
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

	// 회원 갯수
	public int UpchetotalRowCount() {
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

	// 관광지 목록
	public ArrayList<TourDTO> Tourlist(int start, int end, String word) {
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
			sql.append(" 			   WHERE t_dividecn = 1 ");

			if (word.length() != 0) {
				String search = "";
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
	public int TourtotalRowCount() {
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
	}// TourtotalRowCount() end

	// 문화행사 목록
	public ArrayList<TourDTO> Feslist(int start, int end, String word) {
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

			if (word.length() != 0) {
				String search = "";
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
			System.out.println("문화행사 전체 목록 실패: " + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return list;
	}// Feslist() end

	// 문화행사 글 갯수
	public int FestotalRowCount() {
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
	}// FestotalRowCount() end

	// 공지사항 목록
	public ArrayList<noticeDTO> Noticelist(int start, int end, String col, String word) {
		ArrayList<noticeDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();

			word = word.trim();

			sql.append(" SELECT AA.* ");
			sql.append(" FROM ( ");
			sql.append("        SELECT ROWNUM as RNUM, BB.* ");
			sql.append("        FROM ( ");
			sql.append("               SELECT board_no, board_title, board_content, board_date, board_readcnt ");
			sql.append("               FROM tb_board ");

			if (word.length() != 0) {
				String search = "";
				if (col.equals("subject")) {
					search += " WHERE board_title LIKE '%" + word + "%' ";
				} else if (col.equals("content")) {
					search += " WHERE board_content LIKE '%" + word + "%' ";
				} else if (col.equals("subject_content")) {
					search += " WHERE board_title LIKE '%" + word + "%' ";
					search += " OR board_content LIKE '%" + word + "%' ";
				} // if end
				sql.append(search);
			} // if end

			sql.append("               ORDER BY board_no DESC ");
			sql.append("             )BB ");
			sql.append("      ) AA ");
			sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<noticeDTO>();
				do {
					noticeDTO dto = new noticeDTO();
					dto.setBoard_no(rs.getInt("board_no"));
					dto.setBoard_title(rs.getString("board_title"));
					dto.setBoard_content(rs.getString("board_content"));
					dto.setBoard_date(rs.getString("board_date"));
					dto.setBoard_readcnt(rs.getInt("board_readcnt"));
					list.add(dto);
				} while (rs.next());
			} // if end

		} catch (Exception e) {
			System.out.println("공지사항 페이징 목록 실패: " + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return list;
	}// Noticelist() end

	// 공지사항 글 갯수
	public int NoticetotalRowCount() {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT COUNT(*) FROM tb_board ");
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
	}// NoticetotalRowCount() end

	// 자유게시판 목록
	public ArrayList<boardDTO> Boardlist(int start, int end, String col, String word) {
		ArrayList<boardDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();

			word = word.trim();

			sql.append(" SELECT AA.* ");
			sql.append(" FROM ( ");
			sql.append("        SELECT ROWNUM as RNUM, BB.* ");
			sql.append("        FROM ( ");
			sql.append("               SELECT bbs_idx, bbs_img, bbs_id, bbs_title, bbs_content, bbs_count, bbs_date ");
			sql.append("               FROM tb_bbs2 ");

			if (word.length() != 0) {
				String search = "";
				if (col.equals("subject")) {
					search += " WHERE bbs_title LIKE '%" + word + "%' ";
				} else if (col.equals("content")) {
					search += " WHERE bbs_content LIKE '%" + word + "%' ";
				} else if (col.equals("wname")) {
					search += " WHERE bbs_id LIKE '%" + word + "%' ";
				} else if (col.equals("subject_content")) {
					search += " WHERE bbs_title LIKE '%" + word + "%' ";
					search += " OR bbs_content LIKE '%" + word + "%' ";
				} // if end
				sql.append(search);
			}
			sql.append("               ORDER BY bbs_date DESC ");
			sql.append("             )BB ");
			sql.append("      ) AA ");
			sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<boardDTO>();
				do {
					boardDTO dto = new boardDTO();
					dto.setBbs_idx(rs.getInt("bbs_idx"));
					dto.setBbs_img(rs.getString("bbs_img"));
					dto.setBbs_id(rs.getString("bbs_id"));
					dto.setBbs_title(rs.getString("bbs_title"));
					dto.setBbs_content(rs.getString("bbs_content"));
					dto.setBbs_count(rs.getInt("bbs_count"));
					dto.setBbs_date(rs.getString("bbs_date"));
					list.add(dto);
				} while (rs.next());
			} // if end

		} catch (Exception e) {
			System.out.println("게시판 페이징 목록 실패: " + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return list;
	}// Boardlist() end

	// 자유게시판 글 갯수
	public int BoardtotalRowCount() {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT COUNT(*) FROM tb_bbs2 ");
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
	}// BoardtotalRowCount() end

	public ArrayList<SooksoDTO> Sooksolist(int start, int end, String word) {
		ArrayList<SooksoDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();

			word = word.trim();

			sql.append(" SELECT AA.* ");
			sql.append(" FROM ( ");
			sql.append("        SELECT ROWNUM as RNUM, BB.* ");
			sql.append("        FROM ( ");
			sql.append("               SELECT s_cn, s_name, s_addr, s_addr2, s_tel, s_link, s_cont, s_img ");
			sql.append("               FROM tb_sookso ");

			if (word.length() != 0) {
				String search = "";
				search += " where s_name LIKE '%" + word + "%' ";
				sql.append(search);
			}

			sql.append("               ORDER BY s_cn DESC ");
			sql.append("             )BB ");
			sql.append("      ) AA ");
			sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<SooksoDTO>();
				do {
					SooksoDTO dto = new SooksoDTO();
					dto.setS_cn(rs.getString("s_cn"));
					dto.setS_name(rs.getString("s_name"));
					dto.setS_addr(rs.getString("s_addr"));
					dto.setS_addr2(rs.getString("s_addr2"));
					dto.setS_tel(rs.getString("s_tel"));
					dto.setS_link(rs.getString("s_link"));
					dto.setS_cont(rs.getString("s_cont"));
					dto.setS_img(rs.getString("s_img"));
					list.add(dto);
				} while (rs.next());
			} // if end

		} catch (Exception e) {
			System.out.println("숙소 페이징 목록 실패: " + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return list;
	}// Sooksolist() end

	public int SooksototalRowCount() {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT COUNT(*) FROM tb_sookso ");
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
	}// SooksototalRowCount() end

	// 숙소 상세보기
	public SooksoDTO Sooksoread(String s_cn) {
		SooksoDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" select * ");
			sql.append(" from tb_sookso left join tb_room ");
			sql.append(" on tb_sookso.s_cn = tb_room.s_cn ");
			sql.append(" left join tb_review ");
			sql.append(" on tb_sookso.s_cn = tb_review.review_code ");
			sql.append(" WHERE tb_sookso.s_cn = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, s_cn);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new SooksoDTO();
				dto.setS_cn(rs.getString("s_cn"));
				dto.setS_name(rs.getString("s_name"));
				dto.setS_addr(rs.getString("s_addr"));
				dto.setS_addr2(rs.getString("s_addr2"));
				dto.setS_tel(rs.getString("s_tel"));
				dto.setS_link(rs.getString("s_link"));
				dto.setS_cont(rs.getString("s_cont"));
				dto.setS_img(rs.getString("s_img"));
				dto.setRoom_cn(rs.getString("room_cn"));
				dto.setRoom_name(rs.getString("room_name"));
				dto.setRoom_img(rs.getString("room_img"));
				dto.setRoom_mp(rs.getInt("room_mp"));
				dto.setRoom_dp(rs.getInt("room_dp"));
				dto.setRoom_ep(rs.getInt("room_ep"));
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

	// 방 목록
	public ArrayList<SooksoDTO> Roomlist(int start, int end, String s_cn) {
		ArrayList<SooksoDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();

			sql.append(" SELECT AA.* ");
			sql.append(" FROM ( ");
			sql.append("        SELECT ROWNUM as RNUM, BB.* ");
			sql.append("        FROM ( ");
			sql.append("               SELECT room_cn, s_cn ,room_name, room_mp, room_dp, room_ep, room_img ");
			sql.append("               FROM tb_room ");
			sql.append("               where s_cn = ? ");
			sql.append("               ORDER BY room_cn DESC ");
			sql.append("             )BB ");
			sql.append("      ) AA ");
			sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, s_cn);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<SooksoDTO>();
				do {
					SooksoDTO dto = new SooksoDTO();
					dto.setRoom_cn(rs.getString("room_cn"));
					dto.setS_cn(rs.getString("s_cn"));
					dto.setRoom_name(rs.getString("room_name"));
					dto.setRoom_mp(rs.getInt("room_mp"));
					dto.setRoom_dp(rs.getInt("room_dp"));
					dto.setRoom_ep(rs.getInt("room_ep"));
					dto.setRoom_img(rs.getString("room_img"));
					list.add(dto);
				} while (rs.next());
			} // if end

		} catch (Exception e) {
			System.out.println("방 페이징 목록 실패: " + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return list;
	}// Roomlist() end

	// 맛집 목록
	public ArrayList<MaszipDTO> Masziplist(int start, int end, String word) {
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

			if (word.length() != 0) {
				String search = "";
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
	}// Masziplist() end

	// 맛집 글 갯수
	public int MasziptotalRowCount() {
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
	}// MasziptotalRowCount() end

	// 패키지 목록
	public ArrayList<packagetourDTO> Packagelist(int start, int end, String word) {
		ArrayList<packagetourDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();

			word = word.trim();

			sql.append(" SELECT AA.* ");
			sql.append(" FROM ( ");
			sql.append("        SELECT ROWNUM as RNUM, BB.* ");
			sql.append("        FROM ( ");
			sql.append(
					"               SELECT pack_no, pack_name, pack_cose, pack_plan_start, pack_plan_end, pack_price, pack_people, pack_cont, pack_img ");
			sql.append("               FROM tb_package ");

			if (word.length() != 0) {
				String search = "";
				search += " WHERE pack_name LIKE '%" + word + "%' ";
				sql.append(search);
			}

			sql.append("               ORDER BY pack_no DESC ");
			sql.append("             )BB ");
			sql.append("      ) AA ");
			sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<packagetourDTO>();
				do {
					packagetourDTO dto = new packagetourDTO();
					dto.setPack_no(rs.getString("pack_no"));
					dto.setPack_name(rs.getString("pack_name"));
					dto.setPack_cose(rs.getString("pack_cose"));
					dto.setPack_plan_start(sdf.parse(rs.getString("pack_plan_start")));
					dto.setPack_plan_end(sdf.parse(rs.getString("pack_plan_end")));
					dto.setPack_price(rs.getInt("pack_price"));
					dto.setPack_people(rs.getInt("pack_people"));
					dto.setPack_cont(rs.getString("pack_cont"));
					dto.setPack_img(rs.getString("pack_img"));
					list.add(dto);
				} while (rs.next());
			} // if end

		} catch (Exception e) {
			System.out.println("패키지 여행 페이징 목록 실패: " + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return list;
	}// Packagelist() end

	// 패키지 글 갯수
	public int PackagetotalRowCount() {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT COUNT(*) FROM tb_package ");
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
	}// PackagetotalRowCount() end

	// 회원 목록
	public ArrayList<MemberDTO> Memberlist(int start, int end, String word) {
		ArrayList<MemberDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();

			word = word.trim();

			sql.append(" SELECT AA.* ");
			sql.append(" FROM ( ");
			sql.append("        SELECT ROWNUM as RNUM, BB.* ");
			sql.append("        FROM ( ");
			sql.append(
					"               SELECT user_idx, user_id, user_pw, user_name, user_phone, user_email, user_zipcode, user_addr1, user_addr2, user_job, user_level, user_date ");
			sql.append("               FROM tb_user ");

			if (word.length() != 0) {
				String search = "";
				search += " WHERE user_name LIKE '%" + word + "%' ";
				sql.append(search);
			}

			sql.append("               ORDER BY user_idx DESC ");
			sql.append("             )BB ");
			sql.append("      ) AA ");
			sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<MemberDTO>();
				do {
					MemberDTO dto = new MemberDTO();
					dto.setUser_idx(rs.getInt("user_idx"));
					dto.setUser_id(rs.getString("user_id"));
					dto.setUser_pw(rs.getString("user_pw"));
					dto.setUser_name(rs.getString("user_name"));
					dto.setUser_phone(rs.getString("user_phone"));
					dto.setUser_email(rs.getString("user_email"));
					dto.setUser_zipcode(rs.getString("user_zipcode"));
					dto.setUser_addr1(rs.getString("user_addr1"));
					dto.setUser_addr2(rs.getString("user_addr2"));
					dto.setUser_job(rs.getString("user_job"));
					dto.setUser_level(rs.getString("user_level"));
					dto.setUser_date(rs.getString("user_date"));
					list.add(dto);
				} while (rs.next());
			} // if end

		} catch (Exception e) {
			System.out.println("멤버 페이징 목록 실패: " + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return list;
	}// list() end

	// 회원 갯수
	public int MembertotalRowCount() {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT COUNT(*) FROM tb_user ");
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

	// 렌트카 줄개수 rentalcartotalRowCount
	public int rentalcartotalRowCount() {
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
	}// rentalcartotalRowCount() end

	//렌트카 목록
	public ArrayList<rentalcarDTO> rentalcarlist(int start, int end) {
		ArrayList<rentalcarDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();

			sql.append(" SELECT AA.* ");
			sql.append(" FROM ( ");
			sql.append("        SELECT ROWNUM as RNUM, BB.* ");
			sql.append("        FROM ( ");
			sql.append(
					"               SELECT c_code, u_code, c_kind, c_name, c_sum, c_charge, c_reserve, c_img, c_cont ");
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

}