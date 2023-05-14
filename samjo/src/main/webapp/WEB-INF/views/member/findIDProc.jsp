<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="ssi.jsp" %>
<%@ include file="../header.jsp" %>
<!-- 본문시작 findIDProc.jsp -->
<aside id="fh5co-hero" class="js-fullheight">
	<div class="flexslider js-fullheight">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2 style="margin-top: 300px; text-align: center; font-weight: bold;" class="heading-section">아이디/비번찾기</h2>
				<%
				String user_name  =request.getParameter("user_name").trim();
				String user_email =request.getParameter("user_email").trim();//여기 수정했음
				dto.setUser_name(user_name);
				dto.setUser_email(user_email);
				
				boolean flag=dao.findID(dto);
				if(flag==false){
					out.println("<p>이름/이메일을 다시 한번 확인해주세요!!</p>");
					out.println("<p><a href='javascript:history.back()'>[다시시도]</a></p>");
				}else{
					String message="";
					message += "아이디/임시 비밀번호가 이메일로 전송되었습니다\\n";
					message += "임시 비밀번호는 로그인 후 회원정보수정에서 수정하시기 바랍니다";
					out.println("<script>");
					out.println("	alert('" + message + "');");
					out.println("	location.href='loginForm.do'");
					out.println("</script>");
				}//if end
				
				
				%>
			</div>
		</div>
	</div>
</aside>


<!-- 본문끝 -->
<%@ include file="../footer.jsp" %>