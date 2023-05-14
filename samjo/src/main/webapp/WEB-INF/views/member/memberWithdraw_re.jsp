<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<!-- 본문 시작 memberForm.jsp-->
<aside id="fh5co-hero" class="js-fullheight">
	<div class="flexslider js-fullheight">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2 style="margin-top: 300px; text-align: center; font-weight: bold;" class="heading-section">회원 탈퇴 페이지</h2>
				<form action="/member/memberWithdraw.do" method="post">
						<p><strong>비밀번호를 입력하세요.</strong>
						<input type="password" id="user_pw" name="user_pw">
						<input type="submit" value="확인">
						</form>
			</div>
		</div>
	</div>
</aside>

<!-- 본문 끝 -->           
<%@ include file="../footer.jsp"%>