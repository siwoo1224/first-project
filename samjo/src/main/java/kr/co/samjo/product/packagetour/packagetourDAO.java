package kr.co.samjo.product.packagetour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import net.utility.DBClose;
import net.utility.DBOpen;

public class packagetourDAO {
	private DBOpen dbopen=null;
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private StringBuilder sql=null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd. a HH:mm:ss");
	
	public packagetourDAO() {
		dbopen=new DBOpen();
	}//end
	

//create
	public int create(packagetourDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection(); // DB연결

			sql = new StringBuilder();
			sql.append(" INSERT INTO tb_package(pack_no, pack_name, pack_cose, pack_plan_start, pack_plan_end, pack_price, pack_people, pack_cont, pack_img) ");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getPack_no());
			pstmt.setString(2, dto.getPack_name());
			pstmt.setString(3, dto.getPack_cose());		
			pstmt.setString(4, dto.getPack_plan_start().toLocaleString());		
			pstmt.setString(5, dto.getPack_plan_end().toLocaleString());	
			pstmt.setInt(6, dto.getPack_price());
			pstmt.setInt(7, dto.getPack_people());
			pstmt.setString(8, dto.getPack_cont());
			pstmt.setString(9, dto.getPack_img());
			cnt = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("패키지 여행 등록 실패: " + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;
	}//create() end
	
	
//list	
	public ArrayList<packagetourDTO> list(int start, int end){
        ArrayList<packagetourDTO> list=null;
        try {
            con=dbopen.getConnection();
            sql=new StringBuilder();
           
            sql.append(" SELECT AA.* ");
            sql.append(" FROM ( ");
            sql.append("        SELECT ROWNUM as RNUM, BB.* ");
            sql.append("        FROM ( ");
            sql.append("               SELECT pack_no, pack_name, pack_cose, pack_plan_start, pack_plan_end, pack_price, pack_people, pack_cont, pack_img ");
            sql.append("               FROM tb_package ");
            sql.append("               ORDER BY pack_no DESC ");
            sql.append("             )BB ");
            sql.append("      ) AA ");
            sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");           
           
            pstmt=con.prepareStatement(sql.toString());
            pstmt.setInt(1, start);
            pstmt.setInt(2, end);
           
            rs=pstmt.executeQuery();
            if(rs.next()) {
                list=new ArrayList<packagetourDTO>();
                do{
                	packagetourDTO dto=new packagetourDTO();
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
                }while(rs.next());
            }//if end
           
        }catch(Exception e) {
            System.out.println("패키지 여행 페이징 목록 실패: "+e);
        }finally{
            DBClose.close(con, pstmt, rs);
        }//end   
        return list;
    }//list() end
	
	
	
	
// reviewtotalRowCount
			public int reviewtotalRowCount(String pack_no) {
				int cnt = 0;
				try {
					con = dbopen.getConnection();
					sql = new StringBuilder();
					sql.append(" SELECT COUNT(*) FROM tb_review WHERE review_code = ? ");
					pstmt = con.prepareStatement(sql.toString());
					pstmt.setString(1, pack_no);
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
        int cnt=0;
        try {
            con=dbopen.getConnection();
            sql=new StringBuilder();
            sql.append(" SELECT COUNT(*) FROM tb_package ");
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
	
	

//read	
	public packagetourDTO read(String pack_no) {
		packagetourDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT pack_no, pack_name, pack_cose, pack_plan_start, pack_plan_end, pack_price, pack_people, pack_cont, pack_img ");
			sql.append(" FROM tb_package ");
			sql.append(" WHERE tb_package.pack_no = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, pack_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new packagetourDTO();
				dto.setPack_no(rs.getString("pack_no"));
            	dto.setPack_name(rs.getString("pack_name"));
            	dto.setPack_cose(rs.getString("pack_cose"));
            	dto.setPack_plan_start(sdf.parse(rs.getString("pack_plan_start")));
            	dto.setPack_plan_end(sdf.parse(rs.getString("pack_plan_end")));
            	dto.setPack_price(rs.getInt("pack_price"));
            	dto.setPack_people(rs.getInt("pack_people"));
            	dto.setPack_cont(rs.getString("pack_cont"));
            	dto.setPack_img(rs.getString("pack_img"));
			} // if end

		} catch (Exception e) {
			System.out.println("상세보기실패" + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return dto;
	}// read() end
	
	
// reviewList() end	
	public ArrayList<packagetourDTO> reviewList(String pack_no, int start, int end) {
		ArrayList<packagetourDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();

			sql.append(" SELECT AA.* ");
			sql.append(" FROM ( ");
			sql.append("        SELECT ROWNUM as RNUM, BB.* ");
			sql.append("        FROM ( ");
			sql.append(
					"               SELECT pack_no, pack_name, pack_cose, pack_plan_start, pack_plan_end, pack_price, pack_people, pack_cont, pack_img, review_no, review_user_id, review_content, review_date ");
			sql.append("               FROM tb_package left outer join tb_review ");
			sql.append(" 			   ON tb_package.pack_no = tb_review.review_code ");
			sql.append("			   WHERE tb_package.pack_no = ? ");
			sql.append("             )BB ");
			sql.append("      ) AA ");
			sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, pack_no);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

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
					dto.setReview_user_id(rs.getString("review_user_id"));
					dto.setReview_content(rs.getString("review_content"));
					dto.setReview_date(rs.getString("review_date"));
					list.add(dto);
				} while (rs.next());
			} // if end

		} catch (Exception e) {
			System.out.println("패키지 여행 리뷰 전체 목록 실패: " + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return list;
	}// reviewList() end
	
	
	
//update	
	public int update(packagetourDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE tb_package ");
			sql.append(" SET pack_no=?, pack_name=?, pack_cose=?, pack_plan_start=?, pack_plan_end=?, pack_price=?, pack_people=?, pack_cont=?, pack_img=? ");
			sql.append(" WHERE pack_no=? ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getPack_no());
			pstmt.setString(2, dto.getPack_name());
			pstmt.setString(3, dto.getPack_cose());
			pstmt.setString(4, dto.getPack_plan_start().toLocaleString());
			pstmt.setString(5, dto.getPack_plan_end().toLocaleString());
			pstmt.setInt(6, dto.getPack_price());
			pstmt.setInt(7, dto.getPack_people());
			pstmt.setString(8, dto.getPack_cont());
			pstmt.setString(9, dto.getPack_img());
			pstmt.setString(10, dto.getPack_no());
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("패키지 여행 수정 실패" + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;
	}// update() end
	
	
	
//delete	
	public int delete(String pack_no) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" DELETE FROM tb_package");
			sql.append(" WHERE pack_no=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, pack_no);
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("삭제 실패" + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;
	}// delete() end
	
}
