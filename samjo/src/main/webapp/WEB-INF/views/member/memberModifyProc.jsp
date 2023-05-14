<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="ssi.jsp" %>
<%@ include file="../header.jsp" %>
<!-- 본문시작 memberModifyProc.jsp -->
<div class="container">
	<h3>* 회/원/정/보/수/정 결과 *</h3>
<%
	//memberProc.jsp 참조 
	String user_pw      =request.getParameter("user_pw").trim();
	String user_name    =request.getParameter("user_name").trim();
	String user_email   =request.getParameter("user_email").trim();
	String user_phone   =request.getParameter("user_phone").trim();
	String user_zipcode =request.getParameter("user_zipcode").trim();
	String user_addr1   =request.getParameter("user_addr1").trim();
	String user_addr2   =request.getParameter("user_addr2").trim();
	String user_job     =request.getParameter("user_job").trim();
	
	dto.setUser_id((String)session.getAttribute("s_id"));
	dto.setUser_pw(user_pw);
	dto.setUser_name(user_name);
	dto.setUser_email(user_email);
	dto.setUser_phone(user_phone);
	dto.setUser_zipcode(user_zipcode);
	dto.setUser_addr1(user_addr1);
	dto.setUser_addr2(user_addr2);
	dto.setUser_job(user_job);
	
	int cnt=dao.modifyProc(dto);
  	if(cnt==0){
      	out.println("<p>회원정보 수정 실패했습니다</p>");
      	out.println("<p><a href='javascript:history.back()'>[다시시도]</a></p>");
  	}else{
      	out.println("<script>");
      	out.println("    alert('회원 정보 수정 되었습니다~');");
      	out.println("    location.href='loginForm.do'");
      	out.println("</script>");
  	}//if end	
	
	
	
%>
</div>
<!-- 본문끝 -->
<%@ include file="../footer.jsp" %>