package kr.co.samjo.board2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kr.co.samjo.product.sookso.SooksoDTO;
import kr.co.samjo.board2.CmtDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

public class boardDAO {

   private DBOpen dbopen = null;
   private Connection con = null;
   private PreparedStatement pstmt = null;
   private ResultSet rs = null;
   private StringBuilder sql = null;

   public boardDAO() {
      dbopen = new DBOpen();
   }// end

   public int create(boardDTO dto) {
      int cnt = 0;
      try {
         con = dbopen.getConnection(); // DB연결

         sql = new StringBuilder();
         sql.append(" INSERT INTO tb_bbs2(bbs_idx, bbs_img, bbs_id, bbs_title, bbs_content, bbs_count, bbs_date) ");
         sql.append(" VALUES(bbs_seq2.nextval, ?, ?, ?, ?, ?, sysdate) ");

         pstmt = con.prepareStatement(sql.toString());
         pstmt.setString(1, dto.getBbs_img());
         pstmt.setString(2, dto.getBbs_id());
         pstmt.setString(3, dto.getBbs_title());
         pstmt.setString(4, dto.getBbs_content());
         pstmt.setInt(5, dto.getBbs_count());
         cnt = pstmt.executeUpdate();

      } catch (Exception e) {
         System.out.println("게시판 등록 실패: " + e);
      } finally {
         DBClose.close(con, pstmt);
      } // end
      return cnt;
   }// create() end

   public ArrayList<boardDTO> list(int start, int end, String col, String word) {
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
   }// list() end

   public int totalRowCount() {
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
   }// totalRowCount() end

   // 조회수 증가
   public int increment(int bbs_idx) {
      int cnt = 0;
      try {
         con = dbopen.getConnection();
         sql = new StringBuilder();
         sql.append(" UPDATE tb_bbs2 ");
         sql.append(" SET bbs_count=bbs_count+1 ");
         sql.append(" WHERE bbs_idx=? ");
         pstmt = con.prepareStatement(sql.toString());
         pstmt.setInt(1, bbs_idx);
         pstmt.executeUpdate();
      } catch (Exception e) {
         System.out.println(e);
      } finally {
         DBClose.close(con, pstmt);
      }
      return cnt;
   }// increment() end

   public boardDTO read(int bbs_idx) {
      boardDTO dto = null;
      CmtDTO cdto = new CmtDTO();

      try {
         increment(bbs_idx);
         con = dbopen.getConnection();
         sql = new StringBuilder();
         sql.append(" select * ");
         sql.append(" from tb_bbs2 ");
         sql.append(" left join tb_cmt ");
         sql.append(" on tb_bbs2.bbs_idx = tb_cmt.cmt_bbs_idx ");
         sql.append(" where tb_bbs2.bbs_idx = ? ");
         pstmt = con.prepareStatement(sql.toString());
         pstmt.setInt(1, bbs_idx);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            dto = new boardDTO();
            dto.setBbs_idx(rs.getInt("bbs_idx"));
            dto.setBbs_img(rs.getString("bbs_img"));
            dto.setBbs_id(rs.getString("bbs_id"));
            dto.setBbs_title(rs.getString("bbs_title"));
            dto.setBbs_content(rs.getString("bbs_content"));
            dto.setBbs_count(rs.getInt("bbs_count"));
            dto.setBbs_date(rs.getString("bbs_date"));
            cdto.setCmt_idx(rs.getInt("cmt_idx"));
            cdto.setCmt_id(rs.getString("cmt_id"));
            cdto.setCmt_content(rs.getString("cmt_content"));
            cdto.setCmt_bbs_idx(rs.getInt("cmt_bbs_idx"));
            cdto.setCmt_ref(rs.getInt("cmt_ref"));
            cdto.setCmt_re_setp(rs.getInt("cmt_re_setp"));
            cdto.setCmt_level(rs.getInt("cmt_level"));
            cdto.setCmt_date(rs.getString("cmt_date"));
         } // if end

      } catch (Exception e) {
         System.out.println("상세보기실패" + e);
      } finally {
         DBClose.close(con, pstmt, rs);
      } // end
      return dto;
   }// read() end

   public CmtDTO cread(int bbs_idx) {
      boardDTO dto = null;
      CmtDTO cdto = new CmtDTO();

      try {
         increment(bbs_idx);
         con = dbopen.getConnection();
         sql = new StringBuilder();
         sql.append(" select * ");
         sql.append(" from tb_bbs2 ");
         sql.append(" left join tb_cmt ");
         sql.append(" on tb_bbs2.bbs_idx = tb_cmt.cmt_bbs_idx ");
         sql.append(" where tb_bbs2.bbs_idx = ? ");
         pstmt = con.prepareStatement(sql.toString());
         pstmt.setInt(1, bbs_idx);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            dto = new boardDTO();
            dto.setBbs_idx(rs.getInt("bbs_idx"));
            dto.setBbs_img(rs.getString("bbs_img"));
            dto.setBbs_id(rs.getString("bbs_id"));
            dto.setBbs_title(rs.getString("bbs_title"));
            dto.setBbs_content(rs.getString("bbs_content"));
            dto.setBbs_count(rs.getInt("bbs_count"));
            dto.setBbs_date(rs.getString("bbs_date"));
            cdto.setCmt_idx(rs.getInt("cmt_idx"));
            cdto.setCmt_id(rs.getString("cmt_id"));
            cdto.setCmt_content(rs.getString("cmt_content"));
            cdto.setCmt_bbs_idx(rs.getInt("cmt_bbs_idx"));
            cdto.setCmt_ref(rs.getInt("cmt_ref"));
            cdto.setCmt_re_setp(rs.getInt("cmt_re_setp"));
            cdto.setCmt_level(rs.getInt("cmt_level"));
            cdto.setCmt_date(rs.getString("cmt_date"));
         } // if end

      } catch (Exception e) {
         System.out.println("상세보기실패" + e);
      } finally {
         DBClose.close(con, pstmt, rs);
      } // end
      return cdto;
   }// read() end
   
   public CmtDTO sread(int cmt_bbs_idx) {
	      boardDTO dto = null;
	      CmtDTO cdto = new CmtDTO();

	      try {
	         increment(cmt_bbs_idx);
	         con = dbopen.getConnection();
	         sql = new StringBuilder();
	         sql.append(" select * ");
	         sql.append(" from tb_bbs2 ");
	         sql.append(" left join tb_cmt ");
	         sql.append(" on tb_bbs2.bbs_idx = tb_cmt.cmt_bbs_idx ");
	         sql.append(" where tb_cmt.cmt_bbs_idx = ? ");
	         pstmt = con.prepareStatement(sql.toString());
	         pstmt.setInt(1, cmt_bbs_idx);
	         rs = pstmt.executeQuery();
	         if (rs.next()) {
	            dto = new boardDTO();
	            dto.setBbs_idx(rs.getInt("bbs_idx"));
	            dto.setBbs_img(rs.getString("bbs_img"));
	            dto.setBbs_id(rs.getString("bbs_id"));
	            dto.setBbs_title(rs.getString("bbs_title"));
	            dto.setBbs_content(rs.getString("bbs_content"));
	            dto.setBbs_count(rs.getInt("bbs_count"));
	            dto.setBbs_date(rs.getString("bbs_date"));
	            cdto.setCmt_idx(rs.getInt("cmt_idx"));
	            cdto.setCmt_id(rs.getString("cmt_id"));
	            cdto.setCmt_content(rs.getString("cmt_content"));
	            cdto.setCmt_bbs_idx(rs.getInt("cmt_bbs_idx"));
	            cdto.setCmt_ref(rs.getInt("cmt_ref"));
	            cdto.setCmt_re_setp(rs.getInt("cmt_re_setp"));
	            cdto.setCmt_level(rs.getInt("cmt_level"));
	            cdto.setCmt_date(rs.getString("cmt_date"));
	         } // if end

	      } catch (Exception e) {
	         System.out.println("상세보기실패" + e);
	      } finally {
	         DBClose.close(con, pstmt, rs);
	      } // end
	      return cdto;
	   }// read() end

   public int update(boardDTO dto) {
      int cnt = 0;
      try {
         con = dbopen.getConnection();
         sql = new StringBuilder();
         sql.append(" UPDATE tb_bbs2 ");
         sql.append(" SET bbs_img=?, bbs_title=?, bbs_content=?, bbs_date=sysdate ");
         sql.append(" WHERE bbs_idx=? ");

         pstmt = con.prepareStatement(sql.toString());
         pstmt.setString(1, dto.getBbs_img());
         pstmt.setString(2, dto.getBbs_title());
         pstmt.setString(3, dto.getBbs_content());
         pstmt.setInt(4, dto.getBbs_idx());
         cnt = pstmt.executeUpdate();
      } catch (Exception e) {
         System.out.println("게시판 수정 실패" + e);
      } finally {
         DBClose.close(con, pstmt);
      } // end
      return cnt;
   }// update() end

   public int delete(int bbs_idx) {
      int cnt = 0;
      try {
         con = dbopen.getConnection();
         sql = new StringBuilder();
         sql.append(" DELETE FROM tb_bbs2");
         sql.append(" WHERE bbs_idx=? ");
         pstmt = con.prepareStatement(sql.toString());
         pstmt.setInt(1, bbs_idx);
         cnt = pstmt.executeUpdate();
      } catch (Exception e) {
         System.out.println("삭제 실패" + e);
      } finally {
         DBClose.close(con, pstmt);
      } // end
      return cnt;
   }// delete() end

   public ArrayList<CmtDTO> cmtList(int bbs_idx) {
      ArrayList<CmtDTO> cmtlist = null;
      try {
         con = dbopen.getConnection();
         sql = new StringBuilder();

         sql.append(" SELECT cmt_idx, cmt_id, cmt_content, cmt_bbs_idx, cmt_ref, cmt_re_setp, cmt_level, cmt_date ");
         sql.append(" FROM tb_cmt ");
         sql.append(" WHERE cmt_bbs_idx = ? ");
         sql.append(" order by cmt_ref DESC, cmt_level ASC ");

         pstmt = con.prepareStatement(sql.toString());
         pstmt.setInt(1, bbs_idx);

         rs = pstmt.executeQuery();
         if (rs.next()) {
            cmtlist = new ArrayList<CmtDTO>();
            do {
               CmtDTO dto = new CmtDTO();
               dto.setCmt_idx(rs.getInt("cmt_idx"));
               dto.setCmt_id(rs.getString("cmt_id"));
               dto.setCmt_content(rs.getString("cmt_content"));
               dto.setCmt_bbs_idx(rs.getInt("cmt_bbs_idx"));
               dto.setCmt_ref(rs.getInt("cmt_ref"));
               dto.setCmt_re_setp(rs.getInt("cmt_re_setp"));
               dto.setCmt_level(rs.getInt("cmt_level"));
               dto.setCmt_date(rs.getString("cmt_date"));
               cmtlist.add(dto);
            } while (rs.next());
         } // if end

      } catch (Exception e) {
         System.out.println("댓글 전체 목록 실패: " + e);
      } finally {
         DBClose.close(con, pstmt, rs);
      } // end
      return cmtlist;
   }// reviewList() end

   public int create2(CmtDTO dto) {
      int cnt = 0;
      try {
         con = dbopen.getConnection(); // DB연결

         sql = new StringBuilder();
         sql.append(" INSERT INTO tb_cmt(cmt_idx, cmt_id, cmt_content, cmt_bbs_idx, cmt_ref, cmt_date) ");
         sql.append(
               " VALUES((SELECT NVL(MAX(cmt_idx),0)+1 FROM tb_cmt), ?, ?, ?,(SELECT NVL(MAX(cmt_idx),0)+1 FROM tb_cmt),  sysdate) ");

         pstmt = con.prepareStatement(sql.toString());
         pstmt.setString(1, dto.getCmt_id());
         pstmt.setString(2, dto.getCmt_content());
         pstmt.setInt(3, dto.getCmt_bbs_idx());
         cnt = pstmt.executeUpdate();

      } catch (Exception e) {
         System.out.println("게시판 등록 실패: " + e);
      } finally {
         DBClose.close(con, pstmt);
      } // end
      return cnt;
   }// create() end

   public int delete2(int cmt_idx) {
      int cnt = 0;
      try {
         con = dbopen.getConnection();
         sql = new StringBuilder();
         sql.append(" DELETE FROM tb_cmt");
         sql.append(" WHERE cmt_idx=? ");
         pstmt = con.prepareStatement(sql.toString());
         pstmt.setInt(1, cmt_idx);
         cnt = pstmt.executeUpdate();
      } catch (Exception e) {
         System.out.println("삭제 실패" + e);
      } finally {
         DBClose.close(con, pstmt);
      } // end
      return cnt;
   }// delete() end

   public int update2(CmtDTO dto) {
      int cnt = 0;
      try {
         con = dbopen.getConnection();
         sql = new StringBuilder();
         sql.append(" UPDATE tb_cmt ");
         sql.append(" SET cmt_content=? ");
         sql.append(" WHERE cmt_idx=? ");

         pstmt = con.prepareStatement(sql.toString());
         pstmt.setString(1, dto.getCmt_content());
         pstmt.setInt(2, dto.getCmt_idx());
         cnt = pstmt.executeUpdate();
      } catch (Exception e) {
         System.out.println("댓글 수정 실패" + e);
      } finally {
         DBClose.close(con, pstmt);
      } // end
      return cnt;
   }// update() end

   public int replyproc(CmtDTO dto) {
      int cnt = 0;
      try {

         con = dbopen.getConnection();
         sql = new StringBuilder();

         // 1)부모글 정보를 가져와서 그룹번호,들여쓰기,글순서 생성
         int cmt_ref = 0, cmt_re_setp = 0, cmt_level = 0;
         // int grpno=0,indent=0,ansnum=0;
         sql.append(" SELECT cmt_ref,cmt_re_setp,cmt_level FROM tb_cmt");
         sql.append(" WHERE cmt_idx=?");
         pstmt = con.prepareStatement(sql.toString());
         pstmt.setInt(1, dto.getCmt_idx());
         rs = pstmt.executeQuery();
         if (rs.next()) {// 부모글이 존재하는 경우
            cmt_ref = rs.getInt("cmt_ref"); // 부모글 그룹번호
            cmt_re_setp = rs.getInt("cmt_re_setp") + 1; // 부모글 들여쓰기+1
            cmt_level = rs.getInt("cmt_level") + 1; // 부모글 글순서+1
         }

         // 2)글순서 다시 조정하기
         // 부모글 번호와 같은 글중에서 글순서가 같으면서 큰 값
         sql.delete(0, sql.length()); // 기존 쿼리문 삭제
         sql.append(" UPDATE tb_cmt ");
         sql.append(" SET cmt_level=cmt_level+1 ");
         sql.append(" WHERE cmt_ref=? AND cmt_level>=?");
         pstmt = con.prepareStatement(sql.toString());
         pstmt.setInt(1, cmt_ref);
         pstmt.setInt(2, cmt_level);
         pstmt.executeUpdate();

         // 3)답변글 추가
         sql.delete(0, sql.length());
         sql.append(" INSERT INTO tb_cmt ");
         sql.append(" (cmt_idx, cmt_id, cmt_content, cmt_bbs_idx, cmt_ref, cmt_re_setp, cmt_level, cmt_date) ");
         sql.append(" VALUES((SELECT NVL(MAX(cmt_idx),0)+1 FROM tb_cmt),?,?,?,?,?,?,sysdate)");
         pstmt = con.prepareStatement(sql.toString());
         pstmt.setString(1, dto.getCmt_id());
         pstmt.setString(2, dto.getCmt_content());
         pstmt.setInt(3, dto.getCmt_bbs_idx());
         pstmt.setInt(4, cmt_ref);
         pstmt.setInt(5, cmt_re_setp);
         pstmt.setInt(6, cmt_level);
         cnt = pstmt.executeUpdate();

      } catch (Exception e) {
         System.out.println(e);
      } finally {
         DBClose.close(con, pstmt, rs);
      }
      return cnt;
   }// reply() end

}