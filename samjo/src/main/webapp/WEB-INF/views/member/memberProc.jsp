<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="ssi.jsp"%>
<%@ include file="../header.jsp"%>
<!-- 본문 시작 memberProc.jsp-->
<aside id="fh5co-hero" class="js-fullheight">
	<div class="flexslider js-fullheight">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2 style="margin-top: 300px; text-align: center; font-weight: bold;" class="heading-section">회원가입</h2>			
				<%
					//1)사용자가 입력 요청한 값 가져오기
					String user_id      =request.getParameter("user_id").trim();
					String user_pw      =request.getParameter("user_pw").trim();
					String user_name    =request.getParameter("user_name").trim();
					String user_email   =request.getParameter("user_email").trim();
					String user_phone   =request.getParameter("user_phone").trim();
					String user_zipcode =request.getParameter("user_zipcode").trim();
					String user_addr1   =request.getParameter("user_addr1").trim();
					String user_addr2   =request.getParameter("user_addr2").trim();
					String user_job     =request.getParameter("user_job").trim();
				
					//2)dto객체 담기
					dto.setUser_id(user_id);
					dto.setUser_pw(user_pw);
					dto.setUser_name(user_name);
					dto.setUser_email(user_email);
					dto.setUser_phone(user_phone);
					dto.setUser_zipcode(user_zipcode);
					dto.setUser_addr1(user_addr1);
					dto.setUser_addr2(user_addr2);
					dto.setUser_job(user_job);
					
					//3)member테이블에 추가하기
					int cnt=dao.create(dto);
				  	if(cnt==0){
				      	out.println("<p>회원가입 실패했습니다</p>");
				      	out.println("<p><a href='javascript:history.back()'>[다시시도]</a></p>");
				  	}else{
				      	out.println("<script>");
				      	out.println("    alert('회원 가입 되었습니다~');");
				      	out.println("    location.href='loginForm.do'");
				      	out.println("</script>");
				  	}//if end	
					
				%>
			</div>
		</div>
	</div>
</aside>
<!-- 본문 끝 -->           
<%@ include file="../footer.jsp"%>