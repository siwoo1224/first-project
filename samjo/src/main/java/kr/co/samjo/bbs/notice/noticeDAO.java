package kr.co.samjo.bbs.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.utility.DBClose;
import net.utility.DBOpen;

public class noticeDAO {//데이터베이스 관련 작업
    
    private DBOpen dbopen=null;
    private Connection con=null;
    private PreparedStatement pstmt=null;
    private ResultSet rs=null;
    private StringBuilder sql=null;
    
    public noticeDAO() {
        dbopen=new DBOpen();
    }//end

    
//create 
    public int create(noticeDTO dto) {
        int cnt=0;
        try {
            con=dbopen.getConnection();
            
            sql=new StringBuilder();

            sql.append(" INSERT INTO tb_board(board_no, board_title, board_content, board_date, board_readcnt) ");
            sql.append(" VALUES (board_seq.nextval, ?, ?, sysdate, (SELECT NVL(MAX(board_no), 0)+1 FROM tb_board) ) ");

            pstmt=con.prepareStatement(sql.toString());
            pstmt.setString(1, dto.getBoard_title());
            pstmt.setString(2, dto.getBoard_content());
            
            cnt=pstmt.executeUpdate();
            
        }catch (Exception e) {
            System.out.println("공지사항 등록 실패:"+e);
        }finally {
            DBClose.close(con, pstmt);
        }//end
        return cnt;
    }//create() end
    
    
//list
    public ArrayList<noticeDTO> list(int start, int end, String col, String word){
        ArrayList<noticeDTO> list=null;
        try {
            con=dbopen.getConnection();
            sql=new StringBuilder();
            
            word = word.trim();
           
            sql.append(" SELECT AA.* ");
            sql.append(" FROM ( ");
            sql.append("        SELECT ROWNUM as RNUM, BB.* ");
            sql.append("        FROM ( ");
            sql.append("               SELECT board_no, board_title, board_content, board_date, board_readcnt ");
            sql.append("               FROM tb_board ");
            

			
            if(word.length()!=0) {
            	String search="";
	            if(col.equals("subject")) {
	                search += " WHERE board_title LIKE '%" + word + "%' ";
	            }else if(col.equals("content")) {
	                search += " WHERE board_content LIKE '%" + word + "%' ";
	            }else if(col.equals("subject_content")) {
	                search += " WHERE board_title LIKE '%" + word + "%' ";
	                search += " OR board_content LIKE '%" + word + "%' ";
	            }//if end
	            sql.append(search);
			}//if end
            
             
            
            sql.append("               ORDER BY board_no DESC ");
            sql.append("             )BB ");
            sql.append("      ) AA ");
            sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");           
           
            pstmt=con.prepareStatement(sql.toString());
            pstmt.setInt(1, start);
            pstmt.setInt(2, end);
           
            rs=pstmt.executeQuery();
            if(rs.next()) {
                list=new ArrayList<noticeDTO>();
                do{
                	noticeDTO dto=new noticeDTO();
                    dto.setBoard_no(rs.getInt("board_no"));
                    dto.setBoard_title(rs.getString("board_title"));
                    dto.setBoard_content(rs.getString("board_content"));
                    dto.setBoard_date(rs.getString("board_date"));
                    dto.setBoard_readcnt(rs.getInt("board_readcnt"));
                    list.add(dto);
                }while(rs.next());
            }//if end
           
        }catch(Exception e) {
            System.out.println("공지사항 페이징 목록 실패: "+e);
        }finally{
            DBClose.close(con, pstmt, rs);
        }//end   
        return list;
    }//list() end
    
   
    
    
//read  
    public noticeDTO read(int board_no) {
    	noticeDTO dto=null;
        try {
        	
        	incrementCnt(board_no);
            con=dbopen.getConnection();
            
            sql=new StringBuilder();
            sql.append(" SELECT board_no, board_title, board_content, board_date, board_readcnt ");
            sql.append(" FROM tb_board ");
            sql.append(" WHERE board_no=? ");
            
            pstmt=con.prepareStatement(sql.toString());
            pstmt.setInt(1, board_no);
            
            rs=pstmt.executeQuery();
            if(rs.next()) {
                dto=new noticeDTO();
                dto.setBoard_no(rs.getInt("board_no"));
                dto.setBoard_title(rs.getString("board_title"));
                dto.setBoard_content(rs.getString("board_content"));
                dto.setBoard_date(rs.getString("board_date"));
                dto.setBoard_readcnt(rs.getInt("board_readcnt"));
            }//end
            
        }catch (Exception e) {
            System.out.println("상세보기실패:"+e);
        }finally {
            DBClose.close(con, pstmt, rs);
        }//end
        return dto;
    }//read() end
    
    
    
//delete
    public int delete(int board_no) {
    	int cnt=0;
        try {
            con=dbopen.getConnection();
            sql=new StringBuilder();
            sql.append(" DELETE FROM tb_board ");
            sql.append(" WHERE board_no=? "); //관리자 권한 넣기
            
            pstmt=con.prepareStatement(sql.toString());
            pstmt.setInt(1, board_no);
            cnt=pstmt.executeUpdate();
            
        }catch (Exception e) {
            System.out.println("삭제 실패:"+e);
        }finally {
            DBClose.close(con, pstmt);
        }//end
        return cnt;
    }//delete() end
    
    
    
//incrementCnt(조회수증가)
    public void incrementCnt(int board_no) {
        try {
            con=dbopen.getConnection();
            
            sql=new StringBuilder();
            sql.append(" UPDATE tb_board ");
            sql.append(" SET board_readcnt = board_readcnt+1 "); 
            sql.append(" WHERE board_no=? ");
            
            pstmt=con.prepareStatement(sql.toString());
            pstmt.setInt(1, board_no);
            pstmt.executeUpdate();
            
        }catch (Exception e) {
            System.out.println("조회수 증가 실패:"+e);
        }finally {
            DBClose.close(con, pstmt);
        }//end
    }//incrementCnt() end

    		
    
//totalRowCount 
    public int totalRowCount() {
        int cnt=0;
        try {
            con=dbopen.getConnection();
            sql=new StringBuilder();
            sql.append(" SELECT COUNT(*) FROM tb_board ");
            pstmt=con.prepareStatement(sql.toString());
            rs=pstmt.executeQuery();
            if(rs.next()){
                cnt=rs.getInt(1);            
            }//if end          
        }catch(Exception e){
            System.out.println("전체 행 갯수:" + e);
        }finally{
            DBClose.close(con, pstmt);
        }
        return cnt;
    }//totalRowCount() end

	public int bbsInsProc(noticeDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}
	
//Update
	public int update(noticeDTO dto) {
        int cnt = 0;
        try {
          con = dbopen.getConnection();
          sql = new StringBuilder();
          sql.append(" UPDATE tb_board ");
          sql.append(" SET board_title=?, board_content=? ");
          sql.append(" WHERE board_no=? "); 
          pstmt = con.prepareStatement(sql.toString());
          pstmt.setString(1, dto.getBoard_title());
          pstmt.setString(2, dto.getBoard_content());
          pstmt.setInt(3, dto.getBoard_no());
          cnt = pstmt.executeUpdate();
          
        } catch (Exception e) {
           System.out.println("수정실패"+e);
        } finally {
            DBClose.close(con, pstmt);
        }//end
        return cnt;
    }//update end
}//class end   
