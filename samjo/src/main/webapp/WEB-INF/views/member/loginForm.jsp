<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
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
		//--------------------------------------
%>
		<!-- 본문시작 loginForm.jsp -->
		
		<aside id="fh5co-hero" class="js-fullheight">
			<div class="flexslider js-fullheight">
				<div class="container">
					<div class="slider-text-inner desc">
						<h2 style="margin-top: 300px; text-align: center; font-weight: bold;" class="heading-section">로그인</h2>
						<form name="loginfrm" id="loginfrm" method="post" action="loginProc.do" onsubmit="return loginCheck()"><!-- myscript.js에서 함수 작성 -->
							<table class="table" >
								<tr>
									<td><input type="text" name="user_id" id="user_id" value="<%=c_id%>" placeholder="아이디" maxlength="10" required></td>
									<td rowspan="2">
										<!-- type=image의 기본속성이 submit --> <input type="image" src="../images/bt_login.gif">
									</td>
								</tr>
								<tr>
									<td><input type="password" name="user_pw" id="user_pw" placeholder="비밀번호" maxlength="10" required></td>
								</tr>
								<tr>
									<td colspan="2">
										&nbsp;&nbsp;&nbsp; <a href="agreement.do">회원가입</a>
										&nbsp;&nbsp;&nbsp; <a href="findID.do">아이디/비밀번호찾기</a></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
	</aside>
<%
	} else {
%>

<aside id="fh5co-hero" class="js-fullheight" style="text-align: center;">
	<div class="flexslider js-fullheight">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2 style="margin-top: 300px; text-align: center; font-weight: bold;" class="heading-section">로그인 정보</h2>
<%
			//로그인 성공했다면
			out.println("<strong>" + s_id + "</strong> 님");
			out.println("<br><br>");
			out.println("<a href='logout.do'>[로그아웃]</a>");
			out.println("&nbsp;&nbsp;");
			out.println("<a href='memberWithdraw_re.do'>[회원탈퇴]</a>");
	} //if end
%>
</div>
</div>
</div>
</aside>

<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>