<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="ssi.jsp" %>

<%@ include file="../header.jsp" %>

<!-- 본문시작 loginProc.jsp -->
<aside id="fh5co-hero" class="js-fullheight">
			<div class="flexslider js-fullheight">
				<div class="container">
					<div class="slider-text-inner desc">
						<h2 style="margin-top: 300px; text-align: center; font-weight: bold;" class="heading-section">로그인결과</h2>
						<%
							String user_id	 =request.getParameter("user_id").trim();
							String user_pw   =request.getParameter("user_pw").trim();
							dto.setUser_id(user_id);
							dto.setUser_pw(user_pw);
							
							String user_level=dao.loginProc(dto); 
							if(user_level==null){
								out.println("<p>아이디/비밀번호 다시 한번 확인해주세요!!</p>");
								out.println("<p><a href='javascript:history.back()'>[다시시도]</p>");
							}else{
								//로그인 성공
								//out.print("로그인성공~~");
								//out.print("회원등급:" + mlevel);
								
								//다른페이지에서 로그인 상태정보를 공유할 수 있도록(session)
								session.setAttribute("s_id", user_id);
								session.setAttribute("s_passwd", user_pw);
								session.setAttribute("s_mlevel", user_level);
								
								
								
								//첫페이지로 이동
								//http://localhost:9090/myweb/index.jsp

								String root=Utility.getRoot(); //  /myweb반환

								response.sendRedirect("./loginForm.do");
								
							}//if end
							
						%>
				</div>
			</div>
		</div>
</aside>

<!-- 본문끝 -->
<%@ include file="../footer.jsp" %>