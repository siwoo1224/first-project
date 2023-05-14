<%@page import="kr.co.samjo.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<jsp:useBean id="dao" class="kr.co.samjo.member.MemberDAO" scope="page"></jsp:useBean>
<jsp:useBean id="dto" class="kr.co.samjo.member.MemberDTO" scope="page"></jsp:useBean>
<%
//MemberCont.java의 loginProc()함수 참조  -> loginProc.jsp+ssi.jsp는 필요하지 않은 페이지 입니다
//session.setAttribute("s_id", user_id);
//session.setAttribute("s_passwd", user_pw);
//session.setAttribute("s_mlevel", user_level);	

//세션영역에 로그인 정보중에서 하나라도 null이면 로그인 하지 않았다고 판단
if (session.getAttribute("s_id")==null ||session.getAttribute("s_passwd")==null ||session.getAttribute("s_mlevel")==null) {

	//아이디저장 쿠키 확인------------------------
	//사용자PC에 저장된 모든 쿠키값 가져오기
	Cookie[] cookies = request.getCookies();
	String c_id = "";
	if (cookies != null) {
		for (int i = 0; i < cookies.length; i++) { //모든 쿠키값 가져오기
			Cookie cookie = cookies[i]; //쿠키 하나씩 가져오기
			if (cookie.getName().equals("c_id") == true) {
				c_id = cookie.getValue();//쿠키변수값 가져오기
			} //if end
		} //for end
	} //if end
}
		//--------------------------------------
%>
		<!-- 본문시작 loginForm.jsp -->
		
		<aside id="fh5co-hero" class="js-fullheight">
			<div class="flexslider js-fullheight">
				<div class="container">
					<div class="slider-text-inner desc">
						<h2 style="margin-top: 300px; text-align: center; font-weight: bold;" class="heading-section">회원탈퇴</h2>
						<%
							String id = (String)session.getAttribute("s_id");
						
							
							boolean check = dao.memberWithdraw(id);
							
							if(check) {		
								session.invalidate();
						%>
								<script>
									alert("회원 탈퇴가 정상처리되었습니다.");
									location.href='/index.do';
								</script>
						<%
							}else {
						%>
								<script>
									alert("회원 탈퇴에 실패했습니다.");
									location.href='/member/memberWithdraw.do';
								</script>
						<%
							}
						%>
					</div>
				</div>
			</div>
	</aside>


<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>