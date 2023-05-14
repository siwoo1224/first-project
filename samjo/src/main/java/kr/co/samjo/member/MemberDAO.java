package kr.co.samjo.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.co.samjo.utility.DBClose;
import kr.co.samjo.utility.DBOpen;

public class MemberDAO {
	private DBOpen dbopen=null;
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private StringBuilder sql=null;
	
	public MemberDAO() {
		dbopen=new DBOpen();
	}//end	

	public String loginProc(MemberDTO dto) {
		String user_level=null;
		try {
			con=dbopen.getConnection();
			
			sql=new StringBuilder();
			sql.append(" SELECT user_level ");
			sql.append(" FROM tb_user ");
			sql.append(" WHERE user_id=? and user_pw=? ");
			sql.append(" AND user_level in ('1', '2') ");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getUser_id()); 
			pstmt.setString(2, dto.getUser_pw()); 
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				user_level=rs.getString("user_level");

			}//if end
			
		}catch (Exception e) {
			System.out.println("로그인실패:" + e);
		}finally {
			DBClose.close(con, pstmt, rs);
		}//end
		return user_level;
	}//loginProc() end
	
	
	public int duplecateID(String user_id) {
		int cnt=0;
		try {
			con=dbopen.getConnection();
			
			sql=new StringBuilder();
			sql.append(" SELECT COUNT(user_id) as cnt ");
			sql.append(" FROM tb_user ");
			sql.append(" WHERE user_id=? ");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, user_id); 
			
			rs= pstmt.executeQuery();
			if(rs.next()) {
				cnt=rs.getInt("cnt");
			}//if end
			
		}catch (Exception e) {
			System.out.println("아이디 중복 확인 실패:" + e);
		}finally {
			DBClose.close(con, pstmt, rs);
		}//end
		return cnt;
		
	}//duplecateID() end
			

	public int create(MemberDTO dto) {
		int cnt=0;
		try {
			con=dbopen.getConnection();
			
			sql=new StringBuilder();
			sql.append(" INSERT INTO tb_user(user_idx, user_id, user_pw, user_name, user_phone, user_email, user_zipcode, user_addr1, user_addr2, user_job, user_level, user_date) ");
			sql.append(" VALUES(user_seq.nextval,?,?,?,?,?,?,?,?,?,2, sysdate) ");

			
			pstmt=con.prepareStatement(sql.toString());
			//pstmt.setString(1, dto.getId()); 
			//pstmt.setString(2, dto.getPasswd());
			//pstmt.setString(3, dto.getMname());
			//pstmt.setString(4, dto.getTel());
			//pstmt.setString(5, dto.getEmail());
			//pstmt.setString(6, dto.getAddress1());
			//pstmt.setString(7, dto.getAddress2());
			//pstmt.setString(8, dto.getJob());

			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getUser_id()); 
			pstmt.setString(2, dto.getUser_pw());
			pstmt.setString(3, dto.getUser_name());
			pstmt.setString(4, dto.getUser_phone());
			pstmt.setString(5, dto.getUser_email());
			pstmt.setString(6, dto.getUser_zipcode());
			pstmt.setString(7, dto.getUser_addr1());
			pstmt.setString(8, dto.getUser_addr2());
			pstmt.setString(9, dto.getUser_job());

			
			cnt= pstmt.executeUpdate();
			
		}catch (Exception e) {
			System.out.println("회원 가입 실패 :" + e);
		}finally {
			DBClose.close(con, pstmt);
		}//end
		return cnt;
		
	}//create() end
	
	
	public boolean findID(MemberDTO dto) {
		boolean flag=false;
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			
			//이름과 이메일이 일치하는 id 가져오기
			sql.append(" SELECT user_id ");
			sql.append(" FROM tb_user ");
			sql.append(" WHERE user_name=? AND user_email=? ");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getUser_name());
			pstmt.setString(2, dto.getUser_email());
			rs=pstmt.executeQuery();
			if(rs.next()) { //이름과 이메일 일치되었다면
				String id=rs.getString("user_id"); //1)아이디
				
				//[임시 비밀번호 발급] - 대문자, 소문자, 숫자를 이용해서 랜덤하게 10글자를 만들기
				String[] ch= {
						"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
                        "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
                        "0","1","2","3","4","5","6","7","8","9"
				};
				
				//ch배열에서 랜덤하게 10글자 발생
				StringBuilder imsiPW=new StringBuilder(); //2)임시 비밀번호
				for(int i=0; i<10; i++) {
					int num=(int)(Math.random()*ch.length); //ch[0]~ch[61]까지 존재
					imsiPW.append(ch[num]);
				}//for end
				
				
				//임시비밀번호로 업데이트 하기
				sql=new StringBuilder();
				sql.append(" UPDATE tb_user ");
				sql.append(" SET user_pw=? ");
				sql.append(" WHERE user_name=? AND user_email=? ");

				pstmt=con.prepareStatement(sql.toString());
				pstmt.setString(1, imsiPW.toString());
				pstmt.setString(2, dto.getUser_name());
				pstmt.setString(3, dto.getUser_email());//여기 수정했어요
				
				int cnt=pstmt.executeUpdate();
				if(cnt==1) { //임시 비밀번호로 업데이트 되었다면
					
					String content="";
					content+="<hr>";
					content+="<table border='1'>";
					content+="<tr>";
					content+="	<th>아이디</th>";
					content+="	<th>" + id + "</th>";
					content+="</tr>";
					content+="<tr>";
					content+="	<th>임시비밀번호</th>";
					content+="	<th>" + imsiPW.toString() + "</th>";
					content+="</tr>";
					content+="</table>";
					
					
					
					flag=true;//최종적으로 성공
					
				}else {
					flag=false;
				}//if end
			}//if end
			
		}catch (Exception e) {
			System.out.println("아이디/비번 찾기 실패 :" + e);
		}finally {
			DBClose.close(con, pstmt);
		}//end
		return flag;
		
	}//findID() end
	
	
	public MemberDTO read(String id) {
		MemberDTO dto=null;
		try {
			con=dbopen.getConnection();
			
			sql=new StringBuilder();
			sql.append(" SELECT user_pw, user_name, user_phone, user_email, user_zipcode, user_addr1, user_addr2, user_job ");
			sql.append(" FROM tb_user ");
			sql.append(" WHERE user_id=? ");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, id); // 1 -> 첫번째 ?
			
			//pstmt.executeUpdate() -> insert, update, delete문 실행할 때
			rs=pstmt.executeQuery();  //->select문 실행할 때
			if(rs.next()) { //행이 존재 하나요?
				dto=new MemberDTO();
				dto.setUser_pw(rs.getString("user_pw"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setUser_phone(rs.getString("user_phone"));
				dto.setUser_email(rs.getString("user_email"));
				dto.setUser_zipcode(rs.getString("user_zipcode"));
				dto.setUser_addr1(rs.getString("user_addr1"));
				dto.setUser_addr2(rs.getString("user_addr2"));
				dto.setUser_job(rs.getString("user_job"));
			}//if end
			
		}catch (Exception e) {
			System.out.println("회원 정보 가져오기 실패 :" + e);
		}finally {
			DBClose.close(con, pstmt, rs);
		}//end
		return dto;
		
	}//read() end
	
	
	
	public int modifyProc(MemberDTO dto) {
		int cnt=0;//SQL문을 실행한 행의 갯수
		try {
			con=dbopen.getConnection();
			
			sql=new StringBuilder();
			sql.append(" UPDATE tb_user ");
			sql.append(" SET user_pw=?, user_name=?, user_phone=?, user_email=?, user_zipcode=?, user_addr1=?, user_addr2=?, user_job=? ");
			sql.append(" WHERE user_id=? ");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getUser_pw());
			pstmt.setString(2, dto.getUser_name());
			pstmt.setString(3, dto.getUser_phone());
			pstmt.setString(4, dto.getUser_email());
			pstmt.setString(5, dto.getUser_zipcode());
			pstmt.setString(6, dto.getUser_addr1());
			pstmt.setString(7, dto.getUser_addr2());
			pstmt.setString(8, dto.getUser_job());
			pstmt.setString(9, dto.getUser_id());
			
			cnt=pstmt.executeUpdate(); //insert, update, delete문 실행
			
		}catch (Exception e) {
			System.out.println("회원 정보 수정 실패 :" + e);
		}finally {
			DBClose.close(con, pstmt);
		}//end
		return cnt;
		
	}//modifyProc() end
	
	public boolean memberWithdraw(String id) {

		boolean flag = false;

		String sql = "DELETE FROM tb_user WHERE user_id=?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = dbopen.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);

			int i = pstmt.executeUpdate();

			if(i == 1) {
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstmt);;
		}
		return flag;
	}
	
	public int delete(String id) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" DELETE FROM tb_user");
			sql.append(" WHERE user_id=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("삭제 실패" + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;
	}// delete() end
	
}//class end
